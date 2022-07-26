package com.example.summerassessment.util

import android.content.Context
import android.graphics.Matrix
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewTreeObserver
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.ViewPager
import kotlin.math.sqrt

class ZoomImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr), ViewTreeObserver.OnGlobalLayoutListener,
    ScaleGestureDetector.OnScaleGestureListener,
    View.OnTouchListener {
    private var mOnce = false

    //初始化时缩放的值
    private var mInitScale = 0f

    //双击放大值到达的值
    private var mMidScale = 0f

    //放大的最大值
    private var mMaxScale = 0f
    private val mScaleMatrix: Matrix = Matrix()

    //捕获用户多指触控时缩放的比例
    private val mScaleGestureDetector: ScaleGestureDetector

    //记录上一次多点触控的数量
    private var mLastPointerCount = 0
    private var mLastX = 0f
    private var mLastY = 0f
    private val mTouchSlop: Int
    private var isCanDrag = false
    private var isCheckLeftAndRight = false
    private var isCheckTopAndBottom = false

    /*********双击放大与缩小 */
    private val mGestureDetector: GestureDetector
    private var isAutoScale = false

    init {
        // init
        scaleType = ScaleType.MATRIX
        setOnTouchListener(this)
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
        mScaleGestureDetector = ScaleGestureDetector(context, this)
        mGestureDetector = GestureDetector(context,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onDoubleTap(e: MotionEvent): Boolean {
                    if (isAutoScale) {
                        return true
                    }
                    val x: Float = e.x
                    val y: Float = e.y
                    isAutoScale = if (scale < mMidScale) {
                        postDelayed(AutoScaleRunnable(mMidScale, x, y), 16)
                        true
                    } else {
                        postDelayed(AutoScaleRunnable(mInitScale, x, y), 16)
                        true
                    }
                    return true
                }
            })
    }

    /**
     * 自动放大与缩小
     */
    private inner class AutoScaleRunnable(
        /**
         * 缩放的目标值
         */
        private val mTargetScale: Float, // 缩放的中心点
        private var x: Float, y: Float
    ) : Runnable {
        private val BIGGER = 1.07f
        private val SMALL = 0.93f
        private var tmpScale = 0f

        init {
            if (scale < mTargetScale) {
                tmpScale = BIGGER
            }
            if (scale > mTargetScale) {
                tmpScale = SMALL
            }
        }

        override fun run() {
            //进行缩放
            mScaleMatrix.postScale(tmpScale, tmpScale, x, y)
            checkBorderAndCenterWhenScale()
            imageMatrix = mScaleMatrix
            val currentScale: Float = scale
            if ((tmpScale > 1.0f && currentScale < mTargetScale || tmpScale < 1.0f) && currentScale > mTargetScale) {
                //这个方法是重新调用run()方法
                postDelayed(this, 16)
            } else {
                //设置为我们的目标值
                val scale = mTargetScale / currentScale
                mScaleMatrix.postScale(scale, scale, x, y)
                checkBorderAndCenterWhenScale()
                imageMatrix = mScaleMatrix
                isAutoScale = false
            }
        }
    }

    /**
     * 获取ImageView加载完成的图片
     */
    override fun onGlobalLayout() {
        if (!mOnce) {
            // 得到控件的宽和高
            val width: Int = width
            val height: Int = height

            // 得到我们的图片，以及宽和高
            val drawable: Drawable = drawable ?: return
            val dh: Int = drawable.intrinsicHeight
            val dw: Int = drawable.intrinsicWidth
            var scale = 1.0f

            // 图片的宽度大于控件的宽度，图片的高度小于空间的高度，我们将其缩小
            if (dw > width && dh < height) {
                scale = width * 1.0f / dw
            }

            // 图片的宽度小于控件的宽度，图片的高度大于空间的高度，我们将其缩小
            if (dh > height && dw < width) {
                scale = height * 1.0f / dh
            }

            // 缩小值
            if (dw > width && dh > height) {
                scale = (width * 1.0f / dw).coerceAtMost(height * 1.0f / dh)
            }

            // 放大值
            if (dw < width && dh < height) {
                scale = (width * 1.0f / dw).coerceAtMost(height * 1.0f / dh)
            }
            /**
             * 得到了初始化时缩放的比例
             */
            mInitScale = scale
            mMaxScale = mInitScale * 4
            mMidScale = mInitScale * 2

            // 将图片移动至控件的中间
            val dx: Int = getWidth() / 2 - dw / 2
            val dy: Int = getHeight() / 2 - dh / 2
            mScaleMatrix.postTranslate(dx.toFloat(), dy.toFloat())
            mScaleMatrix.postScale(
                mInitScale, mInitScale, (width / 2).toFloat(),
                (height / 2).toFloat()
            )
            imageMatrix = mScaleMatrix
            mOnce = true
        }
    }

    /**
     * 注册OnGlobalLayoutListener这个接口
     */
    protected override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    /**
     * 取消OnGlobalLayoutListener这个接口
     */
    protected override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewTreeObserver.removeGlobalOnLayoutListener(this)
    }

    /**
     * 获取当前图片的缩放值
     *
     * @return
     */
    val scale: Float
        get() {
            val values = FloatArray(9)
            mScaleMatrix.getValues(values)
            return values[Matrix.MSCALE_X]
        }

    // 缩放区间时initScale maxScale
    override fun onScale(detector: ScaleGestureDetector): Boolean {
        var scale = scale
        var scaleFactor: Float = detector.scaleFactor
        if (drawable == null) {
            return true
        }

        // 缩放范围的控制
        if ((scale < mMaxScale && scaleFactor > 1.0f || scale > mInitScale) && scaleFactor < 1.0f) {
            if (scale * scaleFactor < mInitScale) {
                scaleFactor = mInitScale / scale
            }
            if (scale * scaleFactor > mMaxScale) {
                scale = mMaxScale / scale
            }

            // 缩放
            mScaleMatrix.postScale(
                scaleFactor, scaleFactor,
                detector.focusX, detector.focusY
            )
            checkBorderAndCenterWhenScale()
            imageMatrix = mScaleMatrix
        }
        return true
    }

    /**
     * 获得图片放大缩小以后的宽和高，以及left，right，top，bottom
     *
     * @return
     */
    private val matrixRectF: RectF
        private get() {
            val matrix: Matrix = mScaleMatrix
            val rectF = RectF()
            val d: Drawable = drawable
            if (d != null) {
                rectF.set(0F, 0F, d.intrinsicWidth.toFloat(), d.intrinsicHeight.toFloat())
                matrix.mapRect(rectF)
            }
            return rectF
        }

    /**
     * 在缩放的时候进行边界以及我们的位置的控制
     */
    private fun checkBorderAndCenterWhenScale() {
        val rectF: RectF = matrixRectF
        var deltaX = 0f
        var deltaY = 0f
        val width: Int = width
        val height: Int = height

        // 缩放时进行边界检测，防止出现白边
        if (rectF.width() >= width) {
            if (rectF.left > 0) {
                deltaX = -rectF.left
            }
            if (rectF.right < width) {
                deltaX = width - rectF.right
            }
        }
        if (rectF.height() >= height) {
            if (rectF.top > 0) {
                deltaY = -rectF.top
            }
            if (rectF.bottom < height) {
                deltaY = height - rectF.bottom
            }
        }
        /**
         * 如果宽度或高度小于空间的宽或者高，则让其居中
         */
        if (rectF.width() < width) {
            deltaX = width / 2f - rectF.right + rectF.width() / 2f
        }
        if (rectF.height() < height) {
            deltaY = height / 2f - rectF.bottom + rectF.height() / 2f
        }
        mScaleMatrix.postTranslate(deltaX, deltaY)
    }

    override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
        return true
    }

    override fun onScaleEnd(detector: ScaleGestureDetector?) {}
    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (mGestureDetector.onTouchEvent(event)) {
            return true
        }
        mScaleGestureDetector.onTouchEvent(event)
        var x = 0f
        var y = 0f
        // 拿到多点触控的数量
        val pointerCount: Int = event.pointerCount
        for (i in 0 until pointerCount) {
            x += event.getX(i)
            y += event.getY(i)
        }
        x /= pointerCount.toFloat()
        y /= pointerCount.toFloat()
        if (mLastPointerCount != pointerCount) {
            isCanDrag = false
            mLastX = x
            mLastY = y
        }
        mLastPointerCount = pointerCount
        val rectF: RectF = matrixRectF
        when (event.action) {
            MotionEvent.ACTION_DOWN -> if (rectF.width() > width + 0.01 || rectF.height() > height + 0.01) {
                if (parent is ViewPager) parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                if (rectF.width() > width + 0.01 || rectF.height() > height + 0.01) {
                    if (parent is ViewPager) parent.requestDisallowInterceptTouchEvent(true)
                }
                var dx = x - mLastX
                var dy = y - mLastY
                if (!isCanDrag) {
                    isCanDrag = isMoveAction(dx, dy)
                }
                if (isCanDrag) {
                    if (drawable != null) {
                        isCheckTopAndBottom = true
                        isCheckLeftAndRight = isCheckTopAndBottom
                        // 如果宽度小于控件宽度，不允许横向移动
                        if (rectF.width() < width) {
                            isCheckLeftAndRight = false
                            dx = 0f
                        }
                        // 如果高度小于控件高度，不允许纵向移动
                        if (rectF.height() < height) {
                            isCheckTopAndBottom = false
                            dy = 0f
                        }
                        mScaleMatrix.postTranslate(dx, dy)
                        checkBorderWhenTranslate()
                        imageMatrix = mScaleMatrix
                    }
                }
                mLastX = x
                mLastY = y
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> mLastPointerCount = 0
            else -> {}
        }
        return true
    }

    /**
     * 当移动时进行边界检查
     */
    private fun checkBorderWhenTranslate() {
        val rectF: RectF = matrixRectF
        var deltaX = 0f
        var deltaY = 0f
        val width: Int = width
        val height: Int = height
        if (rectF.top > 0 && isCheckTopAndBottom) {
            deltaY = -rectF.top
        }
        if (rectF.bottom < height && isCheckTopAndBottom) {
            deltaY = height - rectF.bottom
        }
        if (rectF.left > 0 && isCheckLeftAndRight) {
            deltaX = -rectF.left
        }
        if (rectF.right < width && isCheckLeftAndRight) {
            deltaX = width - rectF.right
        }
        mScaleMatrix.postTranslate(deltaX, deltaY)
    }

    /**
     * 判断是否是move
     *
     * @param dx
     * @param dy
     * @return
     */
    private fun isMoveAction(dx: Float, dy: Float): Boolean {
        return sqrt((dx * dx + dy * dy).toDouble()) > mTouchSlop
    }
}
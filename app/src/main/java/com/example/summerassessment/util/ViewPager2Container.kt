package com.example.summerassessment.util

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

/**
 *   description:套在vp2外层，解决vp2嵌套的滑动冲突
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */

class ViewPager2Container @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private var mViewPager2: ViewPager2? = null
    private val disallowParentInterceptDownEvent = true
    private var startX = 0
    private var startY = 0

    override fun onFinishInflate() {
        super.onFinishInflate()
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            if (childView is ViewPager2) {
                mViewPager2 = childView
                break
            }
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val doNotNeedIntercept = (!mViewPager2!!.isUserInputEnabled
                || (mViewPager2?.adapter != null
                && mViewPager2?.adapter!!.itemCount <= 1))
        if (doNotNeedIntercept) {
            return super.onInterceptTouchEvent(ev)
        }
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x.toInt()
                startY = ev.y.toInt()
                parent.requestDisallowInterceptTouchEvent(!disallowParentInterceptDownEvent)
            }
            MotionEvent.ACTION_MOVE -> {
                val endX = ev.x.toInt()
                val endY = ev.y.toInt()
                val disX = abs(endX - startX)
                val disY = abs(endY - startY)
                if (mViewPager2!!.orientation == ViewPager2.ORIENTATION_VERTICAL) {
                    onVerticalActionMove(endY, disX, disY)
                } else if (mViewPager2!!.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                    onHorizontalActionMove(endX, disX, disY)
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> parent.requestDisallowInterceptTouchEvent(false)
        }
        return super.onInterceptTouchEvent(ev)
    }

    private fun onHorizontalActionMove(endX: Int, disX: Int, disY: Int) {
        if (mViewPager2?.adapter == null) {
            return
        }

        Log.d("8888888888888888888888", "onHorizontalActionMove: -------------------->${disX-disY}")

        if (disX > disY) {
            val currentItem = mViewPager2?.currentItem
            val itemCount = mViewPager2?.adapter!!.itemCount
            if (currentItem == 0 && endX - startX > 0) {
                parent.requestDisallowInterceptTouchEvent(false)
            } else {
                parent.requestDisallowInterceptTouchEvent(currentItem != itemCount - 1
                        || endX - startX >= 0)
            }
        } else if (disY > disX) {
            parent.requestDisallowInterceptTouchEvent(false)
        }
    }

    private fun onVerticalActionMove(endY: Int, disX: Int, disY: Int) {
        if (mViewPager2?.adapter == null) {
            return
        }
        val currentItem = mViewPager2?.currentItem
        val itemCount = mViewPager2?.adapter!!.itemCount
        if (disY > disX) {
            if (currentItem == 0 && endY - startY > 0) {
                parent.requestDisallowInterceptTouchEvent(false)
            } else {
                parent.requestDisallowInterceptTouchEvent(currentItem != itemCount - 1
                        || endY - startY >= 0)
            }
        } else if (disX > disY) {
            parent.requestDisallowInterceptTouchEvent(false)
        }
    }

}
package com.example.summerassessment.util

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.example.summerassessment.R
import com.example.summerassessment.databinding.MainActivityBottomViewBinding
import com.example.summerassessment.ui.main.MainActivity
import com.example.summerassessment.ui.main.viewmodel.MainActivityViewModel
import com.example.summerassessment.ui.publish.PostArticlesActivity

/**
 *   description:底部导航栏
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */

class BottomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private val imageList: ArrayList<ImageView> = ArrayList(4)
    private val textList: ArrayList<TextView> = ArrayList(4)
    private val unClickImg: ArrayList<Int> = ArrayList(4)
    private val clickImg: ArrayList<Int> = ArrayList(4)

    private val whiteImg: ArrayList<Int> = ArrayList(4)

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(getContext() as MainActivity).get(
            MainActivityViewModel::class.java
        )
    }

    private var previousIndex: Int = 0
    private val view: ConstraintLayout
    private val binding: MainActivityBottomViewBinding

    init {


        clipChildren = false

        view = LayoutInflater.from(context)
            .inflate(R.layout.main_activity_bottom_view, this, true) as ConstraintLayout
        binding = MainActivityBottomViewBinding.bind(view)

        imageList.run {
            add(binding.bottomButtonHomeLayoutImage)
            add(binding.bottomButtonPlayLayoutImage)
            add(binding.bottomButtonNewLayoutImage)
            add(binding.bottomButtonMyLayoutImage)
        }
        textList.run {
            add(binding.bottomButtonHomeLayoutText)
            add(binding.bottomButtonPlayLayoutText)
            add(binding.bottomButtonNewLayoutText)
            add(binding.bottomButtonMyLayoutText)
        }
        unClickImg.run {
            add(R.drawable.home)
            add(R.drawable.draw)
            add(R.drawable.news)
            add(R.drawable.my)
        }
        clickImg.run {
            add(R.drawable.home_click)
            add(R.drawable.draw_click)
            add(R.drawable.news_click)
            add(R.drawable.my_click)
        }

        whiteImg.run {
            add(R.drawable.home_click_2)
            add(R.drawable.draw_click_2)
            add(R.drawable.new_click_2)
            add(R.drawable.my_click_2)
        }

        imageList[previousIndex].setImageResource(clickImg[previousIndex])
        textList[previousIndex].setTextColor(Color.parseColor("#f4ea2a"))

        binding.bottomButtonMyLayout.setOnClickListener(this)
        binding.bottomButtonNewLayout.setOnClickListener(this)
        binding.bottomButtonHomeLayout.setOnClickListener(this)
        binding.bottomButtonPlayLayout.setOnClickListener(this)
        binding.bottomButtonPlusLayout.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bottom_button_home_layout -> {
                setViewColor(0)
                viewModel.setPageNum(0)
            }
            R.id.bottom_button_play_layout -> {
                setViewColor(1)
                viewModel.setPageNum(1)
            }
            R.id.bottom_button_new_layout -> {
                setViewColor(2)
                viewModel.setPageNum(2)
            }
            R.id.bottom_button_my_layout -> {
                setViewColor(3)
                viewModel.setPageNum(3)
            }
            R.id.bottom_button_plus_layout -> {
                context.startActivity(Intent(context,PostArticlesActivity::class.java))
            }
        }
    }


    private fun setViewColor(index: Int) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setBackgroundColor(resources.getColor(R.color.white, context.theme))
            binding.bottomButtonPlusLayoutImage.background =
                ResourcesCompat.getDrawable(resources, R.drawable.shape_round_white, null)
            binding.bottomButtonMyLayout.setBackgroundColor(
                resources.getColor(
                    R.color.white,
                    context.theme
                )
            )
            binding.bottomButtonPlusLayout.background =
                ResourcesCompat.getDrawable(resources, R.drawable.shape_round, null)
            binding.bottomButtonPlayLayout.setBackgroundColor(
                resources.getColor(
                    R.color.white,
                    context.theme
                )
            )
            binding.bottomButtonHomeLayout.setBackgroundColor(
                resources.getColor(
                    R.color.white,
                    context.theme
                )
            )
            binding.bottomButtonNewLayout.setBackgroundColor(
                resources.getColor(
                    R.color.white,
                    context.theme
                )
            )
            binding.bottomParent.background =
                ResourcesCompat.getDrawable(resources, R.drawable.shape_shadow, null)


            for (a in 0 until 4) {
                textList[a].setTextColor(resources.getColor(R.color.black, context.theme))
                textList[a].setBackgroundColor(resources.getColor(R.color.white, context.theme))
                imageList[a].setImageResource(unClickImg[a])
            }
        } else {
            binding.bottomButtonPlayLayout.setBackgroundColor(resources.getColor(R.color.white))
            view.setBackgroundColor(resources.getColor(R.color.white))
            binding.bottomButtonPlusLayoutImage.background =
                ResourcesCompat.getDrawable(resources, R.drawable.shape_round_white, null)
            binding.bottomButtonMyLayout.setBackgroundColor(resources.getColor(R.color.white))
            binding.bottomButtonPlusLayout.background =
                ResourcesCompat.getDrawable(resources, R.drawable.shape_round, null)
            binding.bottomButtonHomeLayout.setBackgroundColor(resources.getColor(R.color.white))
            binding.bottomButtonNewLayout.setBackgroundColor(resources.getColor(R.color.white))

            for (a in 0 until 4) {
                textList[a].setTextColor(resources.getColor(R.color.black))
                imageList[a].setImageResource(unClickImg[a])
            }
        }


        if (index == 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.setBackgroundColor(resources.getColor(R.color.black, context.theme))
                binding.bottomButtonMyLayout.setBackgroundColor(
                    resources.getColor(
                        R.color.black,
                        context.theme
                    )
                )
                binding.bottomButtonPlusLayout.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.shape_round_black, null)
                binding.bottomButtonPlayLayout.setBackgroundColor(
                    resources.getColor(
                        R.color.black,
                        context.theme
                    )
                )
                binding.bottomButtonHomeLayout.setBackgroundColor(
                    resources.getColor(
                        R.color.black,
                        context.theme
                    )
                )
                binding.bottomButtonNewLayout.setBackgroundColor(
                    resources.getColor(
                        R.color.black,
                        context.theme
                    )
                )
                binding.bottomButtonPlusLayoutImage.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.shape_round_white_balck, null)
                for (a in 0 until 4) {
                    textList[a].setTextColor(resources.getColor(R.color.white, context.theme))
                    textList[a].setBackgroundColor(resources.getColor(R.color.black, context.theme))
                    imageList[a].setImageResource(whiteImg[a])
                }
            } else {
                view.setBackgroundColor(resources.getColor(R.color.black))
                binding.bottomButtonMyLayout.setBackgroundColor(resources.getColor(R.color.black))
                binding.bottomButtonPlusLayout.setBackgroundColor(resources.getColor(R.color.black))
                binding.bottomButtonPlayLayout.setBackgroundColor(resources.getColor(R.color.black))
                binding.bottomButtonHomeLayout.setBackgroundColor(resources.getColor(R.color.black))
                binding.bottomButtonNewLayout.setBackgroundColor(resources.getColor(R.color.black))
                binding.bottomParent.setBackgroundColor(resources.getColor(R.color.black))

                for (a in 0 until 4) {
                    textList[a].setTextColor(resources.getColor(R.color.white))
                    imageList[a].setImageResource(whiteImg[a])
                }
            }
            return
        }



        if (index == 4) {
            return
        }
        imageList[previousIndex].setImageResource(unClickImg[previousIndex])
        textList[previousIndex].setTextColor(Color.parseColor("#2c2c2c"))

        imageList[index].setImageResource(clickImg[index])
        textList[index].setTextColor(Color.parseColor("#f4ea2a"))

        previousIndex = index
    }

}


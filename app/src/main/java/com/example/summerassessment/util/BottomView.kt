package com.example.summerassessment.util

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.summerassessment.R
import com.example.summerassessment.databinding.MainActivityBottomViewBinding
import com.example.summerassessment.ui.mainactivity.MainActivity
import com.example.summerassessment.ui.mainactivity.MainActivityViewModel

class BottomView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context,attrs,defStyleAttr), View.OnClickListener  {

    private val imageList:ArrayList<ImageView> =ArrayList(4)
    private val textList:ArrayList<TextView> = ArrayList(4)
    private val unClickImg:ArrayList<Int> = ArrayList(4)
    private val clickImg:ArrayList<Int> =ArrayList(4)

    private val viewModel:MainActivityViewModel by lazy { ViewModelProvider(getContext() as MainActivity).get(MainActivityViewModel::class.java) }

    private var previousIndex:Int=0


    init {


        clipChildren=false

        val view=LayoutInflater.from(context).inflate(R.layout.main_activity_bottom_view,this,true)
        val binding:MainActivityBottomViewBinding=MainActivityBottomViewBinding.bind(view)

        imageList.run{
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

        imageList[previousIndex].setImageResource(clickImg[previousIndex])
        textList[previousIndex].setTextColor(Color.parseColor("#f4ea2a"))

        binding.bottomButtonMyLayout.setOnClickListener(this)
        binding.bottomButtonNewLayout.setOnClickListener(this)
        binding.bottomButtonHomeLayout.setOnClickListener(this)
        binding.bottomButtonPlayLayout.setOnClickListener(this)
        binding.bottomButtonPlusLayout.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.bottom_button_home_layout->{
                setViewColor(0)
                viewModel.setPageNum(0)
            }
            R.id.bottom_button_play_layout->{
                setViewColor(1)
                viewModel.setPageNum(1)
            }
            R.id.bottom_button_new_layout->{
                setViewColor(2)
                viewModel.setPageNum(2)
            }
            R.id.bottom_button_my_layout->{
                setViewColor(3)
                viewModel.setPageNum(3)
            }
            R.id.bottom_button_plus_layout->{
                setViewColor(4)
            }
        }
    }


    private fun setViewColor(index:Int){
        if(previousIndex==index){
            return
        }

        if(index==4){

            return
        }
        imageList[previousIndex].setImageResource(unClickImg[previousIndex])
        textList[previousIndex].setTextColor(Color.parseColor("#2c2c2c"))

        imageList[index].setImageResource(clickImg[index])
        textList[index].setTextColor(Color.parseColor("#f4ea2a"))

        previousIndex=index
    }

}


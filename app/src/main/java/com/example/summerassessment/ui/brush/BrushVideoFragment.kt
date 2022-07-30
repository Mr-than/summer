package com.example.summerassessment.ui.brush

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.summerassessment.databinding.FragmentBrushVideoBinding
import com.example.summerassessment.ui.adapter.BrushVideoAdapter
import com.example.summerassessment.ui.brush.viewmodel.BrushVideoFragmentViewModel
import java.util.ArrayList

/**
 *   description:刷视频界面
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
class BrushVideoFragment: Fragment() {

    private lateinit var binding:FragmentBrushVideoBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(
        BrushVideoFragmentViewModel::class.java) }
    private var last=0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentBrushVideoBinding.inflate(inflater)


        val adapter=BrushVideoAdapter(ArrayList(),requireActivity()) {
            binding.fragmentBrushVideoVp2.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    if(position!=last){
                        it.release(last,binding.fragmentBrushVideoVp2)
                        last=position
                        it.play(position,binding.fragmentBrushVideoVp2)
                    }

                    if(position==it.getSize()-2){
                        viewModel.getVideoData()
                    }
                }
            })
        }
        binding.fragmentBrushVideoVp2.adapter=adapter

        viewModel.brushVideoLiveData.observe(viewLifecycleOwner){
            adapter.update(it,binding.fragmentBrushVideoVp2)
        }

        viewModel.getVideoData()

        return binding.root
    }

}
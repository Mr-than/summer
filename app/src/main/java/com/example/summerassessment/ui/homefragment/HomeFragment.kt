package com.example.summerassessment.ui.homefragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.summerassessment.R
import com.example.summerassessment.databinding.MainFragmentHomePageBinding
import com.example.summerassessment.repository.HomeRecommendRepository
import com.example.summerassessment.ui.adapter.HomePageVpAdapter
import com.example.summerassessment.ui.adapter.HomeRecommendAdapter
import com.example.summerassessment.ui.mainactivity.MainActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.ArrayList
import kotlin.concurrent.thread

class HomeFragment: Fragment() {

    private lateinit var binding:MainFragmentHomePageBinding
    private val viewModel:HomeFragmentViewModel by lazy { ViewModelProvider(this).get(HomeFragmentViewModel::class.java) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=MainFragmentHomePageBinding.inflate(inflater)

        (activity as MainActivity).setSupportActionBar(binding.fragmentHomeToolbar)

        init()





        return binding.root
    }

    private fun init(){
        val adapter=HomeRecommendAdapter(requireActivity())
        setHasOptionsMenu(true);
        binding.fragmentHomeVp.adapter=HomePageVpAdapter(requireActivity(),adapter)


        binding.fragmentHomeTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        TabLayoutMediator(binding.fragmentHomeTabLayout,binding.fragmentHomeVp){
            tab,p->
            when(p){
                0-> tab.text = "关注"
                1->tab.text = "推荐"
                2->tab.text = "新鲜"
                3->tab.text = "纯文"
                4->tab.text = "群图"
            }
        }.attach()

        binding.fragmentHomeVp

        viewModel.recommendLiveData.observe(requireActivity()){
            p->
            lifecycleScope.launch {
                adapter.submitData(p)
            }
        }

        viewModel.getData()

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_bottom_navigation_view_menu,menu)
    }
}
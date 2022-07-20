package com.example.summerassessment.ui.homefragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.summerassessment.R
import com.example.summerassessment.databinding.MainFragmentHomePageBinding
import com.example.summerassessment.model.Data
import com.example.summerassessment.ui.adapter.FollowPageAdapter
import com.example.summerassessment.ui.adapter.HomePageVpAdapter
import com.example.summerassessment.ui.adapter.HomeAdapter
import com.example.summerassessment.ui.mainactivity.MainActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import java.util.ArrayList

class HomeFragment:Fragment() {



    private lateinit var binding: MainFragmentHomePageBinding
    private val viewModel: HomeFragmentViewModel by lazy {
        ViewModelProvider(this).get(
            HomeFragmentViewModel::class.java
        )
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentHomePageBinding.inflate(inflater)

        (activity as MainActivity).setSupportActionBar(binding.fragmentHomeToolbar)

        init()


        return binding.root
    }

    private fun init() {

        val followPageAdapter=FollowPageAdapter(requireActivity(),viewModel, ArrayList())
        val homeAdapter1=HomeAdapter(requireActivity(), ArrayList(),1,viewModel)
        val homeAdapter2=HomeAdapter(requireActivity(), ArrayList(),2,viewModel)
        val homeAdapter3=HomeAdapter(requireActivity(), ArrayList(),3,viewModel)
        val homeAdapter4=HomeAdapter(requireActivity(), ArrayList(),4,viewModel)

        val adapters = listOf(
            followPageAdapter,
            homeAdapter1,
            homeAdapter2,
            homeAdapter3,
            homeAdapter4
        )

        setHasOptionsMenu(true)

        binding.fragmentHomeVp.adapter = HomePageVpAdapter(requireActivity(),adapters)

        binding.fragmentHomeTabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        TabLayoutMediator(binding.fragmentHomeTabLayout, binding.fragmentHomeVp) { tab, p ->
            when (p) {
                0 -> tab.text = "关注"
                1 -> tab.text = "推荐"
                2 -> tab.text = "新鲜"
                3 -> tab.text = "纯文"
                4 -> tab.text = "趣图"
            }
        }.attach()




        viewModel.recommendLiveData.observe(requireActivity()) { p ->
            homeAdapter1.update(p)
        }

        viewModel.newLiveData.observe(requireActivity()){ p->
            homeAdapter2.update(p)
        }

        viewModel.textLiveData.observe(requireActivity()){ p->
            Log.d("8888888888888888", "init:----------------->${p[0].joke}")
            homeAdapter3.update(p)
        }

        viewModel.picLiveData.observe(requireActivity()){ p->
            homeAdapter4.update(p)
        }


        viewModel.getRecommendData()
        viewModel.getNewData()
        viewModel.getText()
        viewModel.getPic()

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_bottom_navigation_view_menu, menu)
    }

}
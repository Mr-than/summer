package com.example.summerassessment.ui.homefragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.summerassessment.R
import com.example.summerassessment.databinding.MainFragmentHomePageBinding
import com.example.summerassessment.model.Data
import com.example.summerassessment.ui.adapter.homeadapter.FollowPageAdapter
import com.example.summerassessment.ui.adapter.homeadapter.HomePageVpAdapter
import com.example.summerassessment.ui.adapter.homeadapter.HomeAdapter
import com.example.summerassessment.ui.mainactivity.MainActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {


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

        val followPageAdapter = FollowPageAdapter(requireActivity(), viewModel, ArrayList())
        val homeAdapter1 = HomeAdapter(requireActivity(), 1, viewModel)
        val homeAdapter2 = HomeAdapter(requireActivity(), 2, viewModel)
        val homeAdapter3 = HomeAdapter(requireActivity(), 3, viewModel)
        val homeAdapter4 = HomeAdapter(requireActivity(), 4, viewModel)

        val adapters = listOf(
            followPageAdapter,
            homeAdapter1,
            homeAdapter2,
            homeAdapter3,
            homeAdapter4
        )

        setHasOptionsMenu(true)

        binding.fragmentHomeVp.adapter = HomePageVpAdapter(requireActivity(), adapters)



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
            homeAdapter1.submitList(p)
        }

        viewModel.newLiveData.observe(requireActivity()) { p ->
            homeAdapter2.submitList(p)
        }

        viewModel.textLiveData.observe(requireActivity()) { p ->
            homeAdapter3.submitList(p)
        }

        viewModel.picLiveData.observe(requireActivity()) { p ->
            homeAdapter4.submitList(p)
        }

        viewModel.followLiveData.observe(requireActivity()) {
            followPageAdapter.submitList(it)
        }

        viewModel.getRecommendData(false)
        viewModel.getNewData(false)
        viewModel.getText(false)
        viewModel.getPic(false)
        viewModel.getFollowData(false)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_bottom_navigation_view_menu, menu)
    }

}
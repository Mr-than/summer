package com.example.summerassessment.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.summerassessment.R
import com.example.summerassessment.databinding.MainFragmentHomePageBinding
import com.example.summerassessment.ui.search.SearchActivity
import com.example.summerassessment.ui.adapter.home.FollowPageAdapter
import com.example.summerassessment.ui.adapter.home.HomeAdapter
import com.example.summerassessment.ui.adapter.home.HomePageVpAdapter
import com.example.summerassessment.ui.home.viewmodel.HomeFragmentViewModel
import com.example.summerassessment.ui.main.MainActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.concurrent.thread


/**
 *   description:首页
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */
class HomeFragment : Fragment() {


    private lateinit var binding: MainFragmentHomePageBinding
    private val viewModel: HomeFragmentViewModel by lazy {
        ViewModelProvider(requireActivity()).get(
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
        val homeAdapter1 = HomeAdapter(requireActivity(), 1,viewModel)
        val homeAdapter2 = HomeAdapter(requireActivity(), 2,viewModel)
        val homeAdapter3 = HomeAdapter(requireActivity(), 3,viewModel)
        val homeAdapter4 = HomeAdapter(requireActivity(), 4,viewModel)

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



        viewModel.recommendLiveData.observe(viewLifecycleOwner) { p ->
            homeAdapter1.submitList(p)
        }

        viewModel.newLiveData.observe(viewLifecycleOwner) { p ->
            homeAdapter2.submitList(p)
        }

        viewModel.textLiveData.observe(viewLifecycleOwner) { p ->
            homeAdapter3.submitList(p)
        }

        viewModel.picLiveData.observe(viewLifecycleOwner) { p ->
            homeAdapter4.submitList(p)
        }

        viewModel.followLiveData.observe(viewLifecycleOwner) {
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

    override fun onOptionsItemSelected(paramMenuItem: MenuItem): Boolean {
        return when (paramMenuItem.itemId) {
            R.id.home_search->{
                requireActivity().startActivity(Intent(requireActivity(), SearchActivity::class.java))
                return false
            }
            else-> super.onOptionsItemSelected(paramMenuItem)
        }
    }

    override fun onStart() {
        super.onStart()
        thread {
            Thread.sleep(500)
            viewModel.getFollowData(true)
        }

    }
}
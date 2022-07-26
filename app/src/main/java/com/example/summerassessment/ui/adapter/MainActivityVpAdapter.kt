package com.example.summerassessment.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


/**
 *   description:主页vp的adapter
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
class MainActivityVpAdapter(
    fragmentActivity: FragmentActivity,
    val getFragment: (Int) -> Fragment
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 4


    override fun createFragment(position: Int): Fragment = getFragment(position)
}
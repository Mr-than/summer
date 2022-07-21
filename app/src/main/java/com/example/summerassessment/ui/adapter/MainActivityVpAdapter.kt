package com.example.summerassessment.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainActivityVpAdapter(fragmentActivity: FragmentActivity,val getFragment:(Int)->Fragment):FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int =4


    override fun createFragment(position: Int): Fragment =getFragment(position)
}
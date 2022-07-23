package com.example.summerassessment.ui.messagefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.summerassessment.databinding.FragmentMessagePageBinding

class MessageFragment: Fragment() {

    private lateinit var binding:FragmentMessagePageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentMessagePageBinding.inflate(inflater)




        return binding.root
    }
}
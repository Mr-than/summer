package com.example.summerassessment.ui.searchpage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.summerassessment.R
import com.example.summerassessment.databinding.ActivitySearchBinding


class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private var keyWord: String? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchActivityEditText.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(binding.searchActivityEditText.text.toString().isNotEmpty()){
                    replaceFragment(ResultFragment(),binding.searchActivityEditText.text.toString())
                }else{
                    replaceFragment(HistoryFragment(),"")
                }
            }
        })

    }

    private fun replaceFragment(fragment: Fragment,value:String){
        if(fragment is ResultFragment){
            val bundle = Bundle()
            bundle.putString("keyWords", value) //这里的values就是我们要传的值
            fragment.setArguments(bundle)
        }

        val fragmentManager =supportFragmentManager
        val transaction=fragmentManager.beginTransaction()
        transaction.replace(R.id.activity_search_fragment,fragment)
        transaction.commit()
    }

}
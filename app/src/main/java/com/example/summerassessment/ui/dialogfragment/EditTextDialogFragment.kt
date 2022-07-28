package com.example.summerassessment.ui.dialogfragment

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.summerassessment.R
import com.example.summerassessment.databinding.DialogFragmentCommentCommentRvItemBinding
import com.example.summerassessment.databinding.DialogFragmentCommentEditLayoutBinding
import com.example.summerassessment.ui.dialogfragment.viewmodel.CommentPageViewModel
import kotlin.concurrent.thread

/**
 *   description:编辑发送评论页
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */
class EditTextDialogFragment : DialogFragment() {
    private lateinit var binding:DialogFragmentCommentEditLayoutBinding

    private lateinit var comment: String
    private val viewModel: CommentPageViewModel by lazy {
        ViewModelProvider(requireActivity()).get(
            CommentPageViewModel::class.java
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireActivity(), R.style.commend_edit_dialog)
        dialog.setContentView(R.layout.dialog_fragment_comment_edit_layout)
        //外部点击可以关闭弹窗

        dialog.setCanceledOnTouchOutside(true)
        val window = dialog.window

        //弹窗背景设置为透明颜色显示圆角

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = window.attributes
        lp.windowAnimations = R.style.commend_dialog

        //设置弹窗位置和高度、宽度

        lp.gravity = Gravity.BOTTOM
        lp.width = WindowManager.LayoutParams.MATCH_PARENT

        val resources: Resources = this.resources
        val dm: DisplayMetrics = resources.displayMetrics
        //val screenWidth = dm.widthPixels
        val screenHeight = dm.heightPixels

        lp.height = (screenHeight / 14)

        window.attributes = lp



        return dialog
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DialogFragmentCommentEditLayoutBinding.inflate(inflater)



        binding.dialogCommendTextViewCommendEdit.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.dialogCommendTextViewCommendEdit.text.toString().isNotEmpty()) {
                    binding.dialogFragmentTextViewSend.setImageResource(R.drawable.send_able)
                    binding.dialogFragmentTextViewSend.isClickable = true
                } else {
                    binding.dialogFragmentTextViewSend.setImageResource(R.drawable.send_disable)
                    binding.dialogFragmentTextViewSend.isClickable = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.dialogCommendTextViewCommendEdit.text.toString().isNotEmpty()) {
                    comment = binding.dialogCommendTextViewCommendEdit.text.toString()
                }
            }
        })

        thread {
            Thread.sleep(200)
            requireActivity().runOnUiThread {
                binding.dialogCommendTextViewCommendEdit.requestFocus()
                binding.dialogCommendTextViewCommendEdit.isFocusableInTouchMode = true
                val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(binding.dialogCommendTextViewCommendEdit, 0)
            }
        }


        viewModel.postIdLiveData.observe(viewLifecycleOwner){
            if(binding.dialogCommendTextViewCommendEdit.text.toString().isNotEmpty()) {
                val content = binding.dialogCommendTextViewCommendEdit.text.toString()
                viewModel.commentJoke(content, it)
                binding.dialogCommendTextViewCommendEdit.setText("")
            }
        }


        viewModel.postRespondLiveData.observe(viewLifecycleOwner){
            viewModel.refreshComment(it)
        }

        binding.dialogFragmentTextViewSend.setOnClickListener {

            if(binding.dialogCommendTextViewCommendEdit.text.toString().isNotEmpty()) {
                viewModel.getId()

            }else{
                Toast.makeText(requireActivity(),"请输入内容",Toast.LENGTH_SHORT).show()
            }

        }



        return binding.root
    }
}
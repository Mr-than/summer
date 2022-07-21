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
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.summerassessment.R
import kotlin.concurrent.thread


class EditTextDialogFragment: DialogFragment() {
    private lateinit var mEditText:EditText
    private lateinit var sendCommentImg:ImageView
    private lateinit var comment:String

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
        mEditText=dialog.findViewById(R.id.dialog_commend_text_view_commend_edit)
        sendCommentImg=dialog.findViewById(R.id.dialog_fragment_text_view_send)

        mEditText.addTextChangedListener(object : TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(mEditText.text.toString().isNotEmpty()){
                    sendCommentImg.setImageResource(R.drawable.send_able)
                    sendCommentImg.isClickable=true
                }else{
                    sendCommentImg.setImageResource(R.drawable.send_disable)
                    sendCommentImg.isClickable=false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                    if(mEditText.text.toString().isNotEmpty()){
                        comment=mEditText.text.toString()
                    }
            }
        })

        thread {
            Thread.sleep(200)
            requireActivity().runOnUiThread {
                mEditText.requestFocus()
                mEditText.isFocusableInTouchMode = true
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(mEditText, 0)
            }
        }

        sendCommentImg.setOnClickListener{
            mEditText.setText("")
        }
        
        return dialog
    }

}
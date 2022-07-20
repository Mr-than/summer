package com.example.summerassessment.ui.dialogfragment

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.summerassessment.R


class CommentPage : DialogFragment() {
    private lateinit var commendNum:TextView
    private lateinit var commendRv:RecyclerView


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireActivity(), R.style.commend_dialog)
        dialog.setContentView(R.layout.dialog_fragment_comment_layout)
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
        val dm: DisplayMetrics = resources.getDisplayMetrics()
        //val screenWidth = dm.widthPixels
        val screenHeight = dm.heightPixels

        lp.height = (screenHeight/1.4).toInt()


        window.attributes = lp

        commendNum=dialog.findViewById(R.id.dialog_fragment_commend_num)
        commendRv=dialog.findViewById(R.id.dialog_fragment_commend_commend)



        return dialog
    }
}
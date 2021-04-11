package com.hawkerlabs.tadah.presentation.list.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.hawkerlabs.tadah.R
import com.hawkerlabs.tadah.databinding.DialogCreateListBinding

class CreateListDialog(context: Context) : Dialog(context) {
    private lateinit var binding: DialogCreateListBinding
    init {
       requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_create_list, null, false)
        setCancelable(true)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
    }

    override fun onCreate(savedInstance: Bundle){
        super.onCreate(savedInstance)
    }
}
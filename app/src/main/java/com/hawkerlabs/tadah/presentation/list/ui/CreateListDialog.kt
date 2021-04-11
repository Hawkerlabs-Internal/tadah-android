package com.hawkerlabs.tadah.presentation.list.ui

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import com.hawkerlabs.tadah.R

class CreateListDialog(context: Context) : Dialog(context) {

    init {
       requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_create_list)
        setCancelable(true)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
    }
}
package com.example.android_tv_frontend.ui.presenter

import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.leanback.widget.Presenter
import com.example.android_tv_frontend.R

class StringPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val textView = TextView(parent.context)
        textView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        textView.isFocusable = false
        textView.isFocusableInTouchMode = false
        textView.setPadding(32, 8, 32, 8)
        textView.setTextColor(ContextCompat.getColor(parent.context, R.color.text_primary))
        textView.textSize = 16f
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        (viewHolder.view as TextView).text = item as String
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        // no-op
    }
}

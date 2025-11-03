package com.example.android_tv_frontend.ui.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.Presenter
import com.example.android_tv_frontend.R
import com.example.android_tv_frontend.model.Recipe

class DetailsDescriptionPresenter : Presenter() {

    class ViewHolder(view: View) : Presenter.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.details_title)
        val subtitle: TextView = view.findViewById(R.id.details_subtitle)
        val body: TextView = view.findViewById(R.id.details_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.vh_details, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
        val recipe = item as Recipe
        val vh = viewHolder as ViewHolder
        vh.title.text = recipe.title
        vh.subtitle.text = "Duration: ${recipe.duration} | Difficulty: ${recipe.difficulty}"
        vh.body.text = recipe.description
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
        // no-op
    }
}

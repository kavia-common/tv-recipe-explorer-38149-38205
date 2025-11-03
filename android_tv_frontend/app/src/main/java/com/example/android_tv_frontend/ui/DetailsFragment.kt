package com.example.android_tv_frontend.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.android_tv_frontend.R
import com.example.android_tv_frontend.model.Recipe
import com.example.android_tv_frontend.ui.presenter.DetailsDescriptionPresenter
import com.example.android_tv_frontend.ui.presenter.StringPresenter

/**
 * A fragment for displaying the details of a selected recipe.
 * This fragment uses a DetailsOverviewRow to show the recipe's image, title, and description,
 * and additional ListRows for ingredients and steps.
 */
class DetailsFragment : DetailsSupportFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recipe = activity?.intent?.getSerializableExtra(DetailsActivity.RECIPE) as? Recipe ?: return

        val presenterSelector = ClassPresenterSelector()
        val detailsPresenter = FullWidthDetailsOverviewRowPresenter(DetailsDescriptionPresenter()).apply {
            backgroundColor = ContextCompat.getColor(requireContext(), R.color.surface)
            initialState = FullWidthDetailsOverviewRowPresenter.STATE_HALF
        }
        presenterSelector.addClassPresenter(DetailsOverviewRow::class.java, detailsPresenter)
        presenterSelector.addClassPresenter(ListRow::class.java, ListRowPresenter())

        val rowsAdapter = ArrayObjectAdapter(presenterSelector)
        val detailsOverview = DetailsOverviewRow(recipe)

        // Load the image
        Glide.with(this)
            .asBitmap()
            .load(recipe.imageUrl)
            .centerCrop()
            .error(R.drawable.app_banner)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    detailsOverview.setImageBitmap(requireContext(), resource)
                }
                override fun onLoadCleared(placeholder: Drawable?) {}
            })

        rowsAdapter.add(detailsOverview)

        // Add ingredients
        val ingredientsAdapter = ArrayObjectAdapter(StringPresenter())
        ingredientsAdapter.addAll(0, recipe.ingredients)
        val ingredientsHeader = HeaderItem("Ingredients")
        rowsAdapter.add(ListRow(ingredientsHeader, ingredientsAdapter))

        // Add steps
        val stepsAdapter = ArrayObjectAdapter(StringPresenter())
        stepsAdapter.addAll(0, recipe.steps.mapIndexed { index, step -> "${index + 1}. $step" })
        val stepsHeader = HeaderItem("Steps")
        rowsAdapter.add(ListRow(stepsHeader, stepsAdapter))

        adapter = rowsAdapter
    }
}

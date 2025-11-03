package com.example.android_tv_frontend.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.example.android_tv_frontend.data.RecipeData
import com.example.android_tv_frontend.model.Recipe
import com.example.android_tv_frontend.R
import com.example.android_tv_frontend.ui.presenter.CardPresenter

/**
 * The main fragment for the TV application.
 * It displays a grid of recipes, organized by category, and handles user navigation.
 */
class MainFragment : BrowseSupportFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUIElements()
        loadRecipes()
        setupEventListeners()
    }

    private fun setupUIElements() {
        title = "Recipe Explorer"
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = resources.getColor(R.color.fastlane_background)
        searchAffordanceColor = resources.getColor(R.color.search_opaque)
    }

    private fun loadRecipes() {
        val recipes = RecipeData.loadRecipes(requireContext())
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter()

        val categories = recipes.flatMap { it.tags }.distinct()

        for ((index, category) in categories.withIndex()) {
            val listRowAdapter = ArrayObjectAdapter(cardPresenter)
            recipes.filter { it.tags.contains(category) }.forEach { listRowAdapter.add(it) }
            val header = HeaderItem(index.toLong(), category)
            rowsAdapter.add(ListRow(header, listRowAdapter))
        }

        adapter = rowsAdapter
    }

    private fun setupEventListeners() {
        onItemViewClickedListener = OnItemViewClickedListener { _, item, _, _ ->
            if (item is Recipe) {
                val intent = Intent(activity, DetailsActivity::class.java)
                intent.putExtra(DetailsActivity.RECIPE, item)
                startActivity(intent)
            }
        }
        setOnSearchClickedListener {
            val intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)
        }
    }
}

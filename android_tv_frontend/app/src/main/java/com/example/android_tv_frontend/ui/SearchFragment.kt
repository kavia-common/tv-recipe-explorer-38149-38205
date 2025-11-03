package com.example.android_tv_frontend.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.leanback.app.SearchSupportFragment
import androidx.leanback.widget.*
import com.example.android_tv_frontend.data.RecipeData
import com.example.android_tv_frontend.model.Recipe
import com.example.android_tv_frontend.ui.presenter.CardPresenter

/**
 * A fragment for searching recipes.
 * It provides a search bar and displays results in a grid.
 */
class SearchFragment : SearchSupportFragment(), SearchSupportFragment.SearchResultProvider {

    private lateinit var rowsAdapter: ArrayObjectAdapter
    private val cardPresenter = CardPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        setSearchResultProvider(this)
        setOnItemViewClickedListener { _, item, _, _ ->
            if (item is Recipe) {
                val intent = Intent(activity, DetailsActivity::class.java)
                intent.putExtra(DetailsActivity.RECIPE, item)
                startActivity(intent)
            }
        }
    }

    override fun getResultsAdapter(): ObjectAdapter {
        return rowsAdapter
    }

    override fun onQueryTextChange(newQuery: String): Boolean {
        Log.i("SearchFragment", "Search query: $newQuery")
        loadQuery(newQuery)
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        Log.i("SearchFragment", "Search query submitted: $query")
        loadQuery(query)
        return true
    }

    private fun loadQuery(query: String) {
        rowsAdapter.clear()
        val recipes = RecipeData.loadRecipes(requireContext())
        val listRowAdapter = ArrayObjectAdapter(cardPresenter)

        val queryLower = query.lowercase()
        recipes.filter {
            it.title.lowercase().contains(queryLower) ||
            it.tags.any { tag -> tag.lowercase().contains(queryLower) }
        }.forEach {
            listRowAdapter.add(it)
        }

        val header = HeaderItem("Search Results")
        rowsAdapter.add(ListRow(header, listRowAdapter))
    }
}

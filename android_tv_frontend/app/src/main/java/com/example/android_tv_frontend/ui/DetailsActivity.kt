package com.example.android_tv_frontend.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.android_tv_frontend.R

/**
 * Activity that displays the details of a selected recipe.
 * It hosts a DetailsFragment to show the recipe information.
 */
class DetailsActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

    companion object {
        const val RECIPE = "Recipe"
    }
}

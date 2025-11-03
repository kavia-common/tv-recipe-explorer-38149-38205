package com.example.android_tv_frontend.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.android_tv_frontend.R

/**
 * Activity for searching recipes.
 * This activity hosts the SearchFragment to provide a search interface.
 */
class SearchActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }
}

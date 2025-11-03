package com.example.android_tv_frontend.data

import android.content.Context
import com.example.android_tv_frontend.R
import com.example.android_tv_frontend.model.Recipe
import com.example.android_tv_frontend.model.RecipeList
import com.google.gson.Gson
import java.io.InputStreamReader

/**
 * A singleton object to load and hold the recipe data from a local JSON file.
 * This ensures that the data is loaded only once and is accessible throughout the app.
 */
object RecipeData {

    private var recipeList: List<Recipe>? = null

    /**
     * Loads the recipes from the `recipes.json` file in the raw resources.
     * This function parses the JSON data and populates the recipe list.
     */
    fun loadRecipes(context: Context): List<Recipe> {
        if (recipeList == null) {
            val inputStream = context.resources.openRawResource(R.raw.recipes)
            val reader = InputStreamReader(inputStream, "UTF-8")
            recipeList = Gson().fromJson(reader, RecipeList::class.java).recipes
        }
        return recipeList!!
    }
}

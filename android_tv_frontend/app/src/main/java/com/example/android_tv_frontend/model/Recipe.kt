package com.example.android_tv_frontend.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Represents a single recipe with all its details.
 * This class is serializable to allow passing it between components.
 */
data class Recipe(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("ingredients") val ingredients: List<String>,
    @SerializedName("steps") val steps: List<String>,
    @SerializedName("duration") val duration: String,
    @SerializedName("difficulty") val difficulty: String
) : Serializable

/**
 * Represents a list of recipes, used for parsing the JSON data.
 */
data class RecipeList(
    @SerializedName("recipes") val recipes: List<Recipe>
)

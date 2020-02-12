package com.example.foodmatesapicallwithmvi.models


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: String,
    @SerializedName("recipe")
    val recipe: List<Recipe>,
    @SerializedName("title")
    val title: String
)
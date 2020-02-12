package com.example.foodmatesapicallwithmvi.models


import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("favourite")
    val favourite: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nutrients")
    val nutrients: List<Nutrient>,
    @SerializedName("ratings")
    val ratings: Int,
    @SerializedName("thumbnail")
    val thumbnail: String
)
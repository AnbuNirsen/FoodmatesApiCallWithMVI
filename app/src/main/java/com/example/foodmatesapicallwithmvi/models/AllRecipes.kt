package com.example.foodmatesapicallwithmvi.models


import com.google.gson.annotations.SerializedName

data class AllRecipes(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("statusCode")
    val statusCode: Int
)
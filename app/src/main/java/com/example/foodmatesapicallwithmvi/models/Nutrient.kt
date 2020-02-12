package com.example.foodmatesapicallwithmvi.models


import com.google.gson.annotations.SerializedName

data class Nutrient(
    @SerializedName("daily_value")
    val dailyValue: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("qty")
    val qty: Int,
    @SerializedName("unit")
    val unit: String
)
package com.example.foodmatesapicallwithmvi.network

import com.example.foodmatesapicallwithmvi.models.AllRecipes
import io.reactivex.Observable
import retrofit2.http.GET

interface RecipesApi {
    @GET("all-recipes/")
    fun getAllRecipes():Observable<AllRecipes>
}
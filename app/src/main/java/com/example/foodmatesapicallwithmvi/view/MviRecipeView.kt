package com.example.foodmatesapicallwithmvi.view

import com.example.foodmatesapicallwithmvi.models.mvi.RecipeStates
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface MviRecipeView:MvpView {
    fun defaultLoadOfRecipes(): Observable<Boolean>
    fun onSwipeLoadRecipes():Observable<Unit>
    fun renderToView(recipeStates: RecipeStates)
}
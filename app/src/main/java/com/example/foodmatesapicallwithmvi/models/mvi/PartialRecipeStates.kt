package com.example.foodmatesapicallwithmvi.models.mvi

import com.example.foodmatesapicallwithmvi.models.AllRecipes
import com.example.foodmatesapicallwithmvi.models.Recipe

sealed class PartialRecipeStates {
    object ShowProgress:PartialRecipeStates()
    object ShowSwipeRefresh:PartialRecipeStates()
    data class RecipeList(val allRecipes: AllRecipes):PartialRecipeStates()
    data class Error(val error:Throwable?):PartialRecipeStates()
}
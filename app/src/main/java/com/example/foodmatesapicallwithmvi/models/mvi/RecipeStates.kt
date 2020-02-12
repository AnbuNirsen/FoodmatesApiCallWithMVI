package com.example.foodmatesapicallwithmvi.models.mvi

import com.example.foodmatesapicallwithmvi.models.AllRecipes
import com.example.foodmatesapicallwithmvi.models.Recipe

data class RecipeStates(val showProgress:Boolean = false,val showSwipeRefresh:Boolean = false,val recipe: List<Recipe>?=null,val error:Throwable? = null)
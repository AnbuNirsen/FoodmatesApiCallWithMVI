package com.example.foodmatesapicallwithmvi.presenter

import android.util.Log
import com.example.foodmatesapicallwithmvi.models.Recipe
import com.example.foodmatesapicallwithmvi.models.mvi.PartialRecipeStates
import com.example.foodmatesapicallwithmvi.models.mvi.RecipeStates
import com.example.foodmatesapicallwithmvi.usecases.RecipeUseCases
import com.example.foodmatesapicallwithmvi.view.MviRecipeView
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecipePresenter:MviBasePresenter<MviRecipeView,RecipeStates>() {
    override fun bindIntents() {
        val defaultLoadRecipe = intent(MviRecipeView::defaultLoadOfRecipes)
            .flatMap {
                RecipeUseCases.getDefaultRecipe()
            }
            .onErrorReturn {
                PartialRecipeStates.Error(it)
            }
            .startWith(PartialRecipeStates.ShowProgress)
            .doOnNext { Log.d("==> load rec presenter",it.toString()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val swipeLoadRecipeResponse = intent(MviRecipeView::onSwipeLoadRecipes)
            .flatMap {
                RecipeUseCases.getSwipeRefreshedData()
            }
            .onErrorReturn {
                PartialRecipeStates.Error(it)
            }
            .startWith(PartialRecipeStates.ShowProgress)
            .doOnNext{Log.d("==> load swi presenter",it.toString())}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val finalResponse = Observable.merge(defaultLoadRecipe,swipeLoadRecipeResponse)
        val initialState = RecipeStates()
        val resultState = finalResponse.scan(initialState, {
            previousState:RecipeStates,currentState:PartialRecipeStates ->
                when(currentState){
                    is PartialRecipeStates.ShowProgress ->
                        RecipeStates(
                            showProgress = true,
                            showSwipeRefresh = false,
                            recipe = null,
                            error = null
                        )
                    is PartialRecipeStates.ShowSwipeRefresh ->
                        RecipeStates(
                            showProgress = false,
                            showSwipeRefresh = true,
                            recipe = null,
                            error = null
                        )
                    is PartialRecipeStates.RecipeList ->{
                        val recipeList = ArrayList<Recipe>()
                        val dataList = currentState.allRecipes.data

                        for(recipe:Recipe in dataList.get(0).recipe)
                            recipeList.add(recipe)

                            RecipeStates(
                                showProgress = false,
                                showSwipeRefresh = false,
                                recipe = recipeList,
                                error = null
                                )
                    }
                    is PartialRecipeStates.Error ->
                        RecipeStates(
                            showProgress = false,
                            showSwipeRefresh = false,
                            recipe = null,
                            error = currentState.error
                        )

                }
        })
        subscribeViewState(resultState,MviRecipeView::renderToView)
    }
}
package com.example.foodmatesapicallwithmvi.usecases

import android.util.Log
import com.example.foodmatesapicallwithmvi.models.mvi.PartialRecipeStates
import com.example.foodmatesapicallwithmvi.network.RecipesApi
import com.example.foodmatesapicallwithmvi.network.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RecipeUseCases {
    val endPoint = RetrofitClient.getRetrofit().create(RecipesApi::class.java)
    fun getDefaultRecipe():Observable<PartialRecipeStates>{
        return endPoint.getAllRecipes()
            .map<PartialRecipeStates>{
                PartialRecipeStates.RecipeList(it)
            }
            .startWith(PartialRecipeStates.ShowProgress)
            .onErrorReturn {
                PartialRecipeStates.Error(it)
            }
            .doOnNext {
                Log.d("==>default usecase",it.toString())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSwipeRefreshedData():Observable<PartialRecipeStates>{
        return endPoint.getAllRecipes()
            .map<PartialRecipeStates>{
                PartialRecipeStates.RecipeList(it)
            }
            .startWith(PartialRecipeStates.ShowSwipeRefresh)
            .onErrorReturn {
                PartialRecipeStates.Error(it)
            }
            .doOnNext { Log.d("==> swipe usecase",it.toString()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
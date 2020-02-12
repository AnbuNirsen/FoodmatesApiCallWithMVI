package com.example.foodmatesapicallwithmvi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.example.foodmatesapicallwithmvi.R
import com.example.foodmatesapicallwithmvi.models.Recipe
import com.example.foodmatesapicallwithmvi.models.mvi.RecipeStates
import com.example.foodmatesapicallwithmvi.presenter.RecipePresenter
import com.example.foodmatesapicallwithmvi.view.adapter.RecipeAdapter
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.support.v4.widget.refreshes
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MviActivity<MviRecipeView,RecipePresenter>(),MviRecipeView {

    private lateinit var adapter:RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = RecipeAdapter()
        recyclerView.adapter = adapter
    }

    override fun createPresenter(): RecipePresenter {
        return RecipePresenter()
    }

    override fun defaultLoadOfRecipes(): Observable<Boolean> = Observable.just(true).subscribeOn(Schedulers.io())

    override fun onSwipeLoadRecipes(): Observable<Unit> = swipeLayout.refreshes()
    override fun renderToView(recipeStates: RecipeStates) {
        if(recipeStates.showProgress){
            progress_circular.visibility = VISIBLE
            swipeLayout.isRefreshing = false
        }else if(recipeStates.showSwipeRefresh){
            progress_circular.visibility = GONE
            swipeLayout.isRefreshing = true
        }else if(recipeStates.recipe !=null){
            progress_circular.visibility = GONE
            swipeLayout.isRefreshing = false
            adapter.setRecipe(recipeStates.recipe)
            adapter.notifyDataSetChanged()
        }else if(recipeStates.error != null){
            progress_circular.visibility = GONE
            swipeLayout.isRefreshing = false
            Toast.makeText(this,recipeStates.error.message,Toast.LENGTH_SHORT).show()
        }
    }


}

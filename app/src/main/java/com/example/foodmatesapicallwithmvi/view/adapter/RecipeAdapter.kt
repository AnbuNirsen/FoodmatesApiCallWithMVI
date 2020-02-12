package com.example.foodmatesapicallwithmvi.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmatesapicallwithmvi.R
import com.example.foodmatesapicallwithmvi.models.Recipe
import com.example.foodmatesapicallwithmvi.view.viewHolder.RecipeViewHolder
import com.squareup.picasso.Picasso

class RecipeAdapter: RecyclerView.Adapter<RecipeViewHolder>() {
    private var recipeList = ArrayList<Recipe>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_item,parent,false))
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        Log.d("===>>>",position.toString())
        holder.textView.text = recipeList.get(position).name
        Picasso.get().load(recipeList.get(position).thumbnail).into(holder.imageView);
    }

    fun setRecipe(recipelist1:List<Recipe>){
        this.recipeList.clear()
        this.recipeList.addAll(recipelist1)
    }
}
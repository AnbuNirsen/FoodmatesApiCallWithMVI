package com.example.foodmatesapicallwithmvi.view.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmatesapicallwithmvi.R

class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
 val textView:TextView = itemView.findViewById(R.id.textView2)
 val imageView:ImageView = itemView.findViewById(R.id.imageView)
}
package com.example.onlineshopkotlinproject.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshopkotlinproject.Model.BrandModel
import com.example.onlineshopkotlinproject.R
import com.example.onlineshopkotlinproject.databinding.ActivityMainBinding
import com.example.onlineshopkotlinproject.databinding.ViewholderBrandBinding
import com.example.onlineshopkotlinproject.databinding.ViewholderColerBinding
import com.example.onlineshopkotlinproject.databinding.ViewholderRecommendedBinding
/**
 * ColorAdapter is a custom RecyclerView adapter for displaying a list of color options.
 * It allows users to select a color and provides visual feedback for the selected item.
 */
class ColorAdapter(val items :MutableList<String>) :RecyclerView.Adapter<ColorAdapter.Viewholder>(){

    private  var selectedPosition=-1 // Position of the currently selected color
    private  var lastSelectedPosition=-1// Position of the previously selected color
    private lateinit var context: Context// Context to be used for inflating layouts and accessing resources
    /**
     * ViewHolder class for holding and binding views for each color item.
     * @param binding Binding object for the color item layout
     */
    class Viewholder(val binding: ViewholderColerBinding):
        RecyclerView.ViewHolder(binding.root){

    }
    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     * Inflates the layout for the color item and returns a new ViewHolder.
     * @param parent The parent ViewGroup
     * @param viewType The type of view to create
     * @return A new ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorAdapter.Viewholder {
        context=parent.context
        val binding = ViewholderColerBinding.inflate(LayoutInflater.from(context),parent,false)
        return ColorAdapter.Viewholder(binding)
    }
    /**
     * Called by RecyclerView to display the data at the specified position.
     * Binds the color data to the ViewHolder.
     * @param holder The ViewHolder to bind data to
     * @param position The position of the item in the list
     */
    override fun onBindViewHolder(holder: ColorAdapter.Viewholder,  position: Int) {
        // Load the color image using Glide
        Glide.with(holder.itemView.context)
            .load(items[position])
            .into(holder.binding.pic)
        holder.binding.root.setOnClickListener{
            // Update the selected positions
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            // Notify the adapter to refresh the previously and currently selected items
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

        }
        // Update the visual appearance based on selection

        if(selectedPosition==position){

            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)


        }else{
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)

        }
    }
    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The number of color items
     */
    override fun getItemCount(): Int = items.size
}
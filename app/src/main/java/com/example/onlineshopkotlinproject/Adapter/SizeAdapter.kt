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
import com.example.onlineshopkotlinproject.databinding.ViewholderSizeBinding
/**
 * SizeAdapter is a custom RecyclerView Adapter used for displaying a list of sizes in a selectable format.
 * It handles view binding and selection logic for size items.
 *
 * @property items A mutable list of size strings to be displayed in the RecyclerView.
 */
class SizeAdapter(val items :MutableList<String>) :RecyclerView.Adapter<SizeAdapter.Viewholder>(){

    private  var selectedPosition=-1
    private  var lastSelectedPosition=-1
    private lateinit var context: Context

    /**
     * Viewholder class that holds the views for each item in the RecyclerView.
     * It uses data binding to access and manipulate the views.
     */
    class Viewholder(val binding: ViewholderSizeBinding):
        RecyclerView.ViewHolder(binding.root){

    }
    /**
    * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
    * Inflates the viewholder_size layout and returns a new Viewholder.
    */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeAdapter.Viewholder {
        context=parent.context
        val binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context),parent,false)
        return SizeAdapter.Viewholder(binding)
    }
    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method binds the data to the ViewHolder and sets up item click listeners.
     *
     * @param holder The ViewHolder which should be updated to represent the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: SizeAdapter.Viewholder, position: Int) {

        holder.binding.sizeTxt.text=items[position]

        /**
         * Store the previously selected item’s position in lastSelectedPosition.
         * Update the selectedPosition to the newly clicked item’s position.
         * Notify the RecyclerView to refresh both the previously selected item and the newly selected item to reflect the updated selection state.
         */
        holder.binding.root.setOnClickListener{
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

        }
// Update the appearance based on whether the item is selected or not
        if(selectedPosition==position){

            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.purple))


        }else{
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.black))
        }
    }
    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The size of the items list.
     */
    override fun getItemCount(): Int = items.size
}
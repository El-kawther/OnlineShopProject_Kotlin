package com.example.onlineshopkotlinproject.Adapter

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
import com.example.onlineshopkotlinproject.databinding.ViewholderRecommendedBinding
/**
 * BrandAdapter is a custom RecyclerView adapter for displaying a list of brands.
 * It manages the display and selection of brand items, with specific UI changes for the selected item.
 */
class BrandAdapter(val items :MutableList<BrandModel>) :RecyclerView.Adapter<BrandAdapter.Viewholder>(){

    private  var selectedPosition=-1// Tracks the position of the currently selected item.
    private  var lastSelectedPosition=-1// Tracks the position of the previous selected item.
    private lateinit var context: Context

    /**
     * ViewHolder class to hold the view for each brand item.
     */
    class Viewholder(val binding: ViewholderBrandBinding):
        RecyclerView.ViewHolder(binding.root){
    }
    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     * Inflates the slider_item_container layout and returns a new SliderViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.Viewholder {
        context=parent.context
        val binding = ViewholderBrandBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }
    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method binds the data to the ViewHolder and manages item selection.
     */
    override fun onBindViewHolder(holder: BrandAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.binding.title.text=item.title
          // Load the brand image using Glide.
        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)
        // Set an OnClickListener to handle item selection.
        holder.binding.root.setOnClickListener{
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

        }
        // Default UI settings for the title.
        holder.binding.title.setTextColor(context.resources.getColor(R.color.white))
        // Customize UI based on whether the item is selected or not.
        if(selectedPosition==position){
            holder.binding.pic.setBackgroundResource(0)// Remove background for the selected image.
            holder.binding.mainLayout.setBackgroundResource(R.drawable.purple_button_bg) // Set background for the selected layout.
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(R.color.white)))// Set image tint to white.

            holder.binding.title.visibility= View.VISIBLE// Make the title visible.

        }else{
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)// Set background for the unselected image.
            holder.binding.mainLayout.setBackgroundResource(0)// Remove background for the unselected layout.
            ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(context.getColor(R.color.black)))// Set image tint to black.

            holder.binding.title.visibility= View.GONE// Hide the title.
        }
    }
    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    override fun getItemCount(): Int = items.size
}
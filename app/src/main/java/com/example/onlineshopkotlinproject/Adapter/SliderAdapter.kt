package com.example.onlineshopkotlinproject.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.onlineshopkotlinproject.Model.SliderModel
import com.example.onlineshopkotlinproject.R

/**
 * SliderAdapter is a custom RecyclerView Adapter used for displaying a list of slider images
 * within a ViewPager2. It handles image loading and view recycling for smooth scrolling.
 */
//                    List of SliderModel objects representing the images. //  Reference to the ViewPager2 that will display the images.
class SliderAdapter (private var sliderItems:List<SliderModel>,private val viewPage2: ViewPager2):
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>(){

    // Context to be used for inflating layouts and loading images.
    private lateinit var context:Context

    /**
     * SliderViewHolder is a ViewHolder class for the SliderAdapter.
     * It holds the view for each slider item and sets the image using Glide.
     */

    class SliderViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView =itemView.findViewById(R.id.imageSlide)
        fun setImage(sliderItems: SliderModel,context: Context){
            val requestOption=RequestOptions().transform(CenterInside())
            Glide.with(context)
                .load(sliderItems.url)
                .apply(requestOption)
                .into(imageView)

        }
    }
    // Runnable task to reset and refresh the slider items when reaching the end of the list.
    private val runnable = Runnable {
        sliderItems=sliderItems  // Reassign the sliderItems list to itself.
        notifyDataSetChanged()  // Notify the adapter to refresh the data.
    }


    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     * Inflates the slider_item_container layout and returns a new SliderViewHolder.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderViewHolder {
       context=parent.context
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.slider_item_container,parent,false)
        return  SliderViewHolder(view)
    }
    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method binds the data to the ViewHolder.
     */
    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(sliderItems[position],context)
        if (position==sliderItems.lastIndex-1) {
            viewPage2.post(runnable)
        }
    }
    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    override fun getItemCount(): Int = sliderItems.size

}
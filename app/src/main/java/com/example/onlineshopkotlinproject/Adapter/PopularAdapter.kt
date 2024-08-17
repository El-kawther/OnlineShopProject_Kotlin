package com.example.onlineshopkotlinproject.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.onlineshopkotlinproject.Model.ItemsModel
import com.example.onlineshopkotlinproject.activity.DetailActivity
import com.example.onlineshopkotlinproject.databinding.ViewholderRecommendedBinding
/**
 * Adapter for displaying a list of popular items in a RecyclerView.
 * Each item includes an image, title, price, and rating. Clicking on an item navigates to a detailed view.
 */
class PopularAdapter (val items:MutableList<ItemsModel>):RecyclerView.Adapter<PopularAdapter.ViewHolder>(){
   private var context:Context?=null
    /**
     * ViewHolder class to hold references to the view elements for each item.
     * @param binding Binding for the item layout.
     */
    class ViewHolder (val binding: ViewholderRecommendedBinding):RecyclerView.ViewHolder(binding.root)
    /**
     * Creates a new ViewHolder for the RecyclerView.
     * @param parent The parent ViewGroup where the new View will be added.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.ViewHolder {
       context=parent.context
        val binding=ViewholderRecommendedBinding.inflate(LayoutInflater.from(context),parent,false)
       return ViewHolder(binding)
    }
    /**
     * Binds data to the ViewHolder at the specified position.
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: PopularAdapter.ViewHolder, position: Int) {
        // Set the text for title, price, and rating.
       holder.binding.titleTxt.text=items[position].title
        holder.binding.priceTxt.text="$" + items[position].price.toString()
        holder.binding.ratingTxt.text=items[position].rating.toString()
      // Create request options for image transformation.
        val requestOptions=RequestOptions().transform(CenterCrop())
       // Load the image using Glide.
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply(requestOptions)
            .into(holder.binding.pic)
        // Set an OnClickListener for the item view.
        holder.itemView.setOnClickListener{
            // Create an Intent to start DetailActivity.
            val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            // Pass the selected item as an extra.
            intent.putExtra("object",items[position])
            // Start DetailActivity.
            holder.itemView.context.startActivity(intent)

            }
    }
    /**
     * Returns the total number of items in the data set.
     * @return The number of items.
     */
    override fun getItemCount(): Int =items.size


}
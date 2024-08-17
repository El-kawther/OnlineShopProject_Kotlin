package com.example.onlineshopkotlinproject.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.onlineshopkotlinproject.Model.ItemsModel
import com.example.onlineshopkotlinproject.databinding.ViewholderCartBinding
import com.example.onlineshopkotlinproject.databinding.ViewholderRecommendedBinding
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.project1762.Helper.ManagmentCart

/**
 * The CartAdapter is a custom RecyclerView adapter used to display items
 * in the shopping cart. It allows users to view cart items,
 * and increment or decrement the quantity of each item in the cart.
 */
class CartAdapter(private val  listItemSelected:ArrayList<ItemsModel>, context: Context, var changeNumberItemsListener: ChangeNumberItemsListener?=null):RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    //Holds a reference to the ViewholderCartBinding for accessing views in the item layout.

    class ViewHolder (val binding:ViewholderCartBinding):RecyclerView.ViewHolder(binding.root){
    }
    // Management class to handle cart operations
private val managmentCart=ManagmentCart(context)
    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     * @param parent The parent ViewGroup
     * @param viewType The type of view to create
     * @return A new ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
       val binding=ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return ViewHolder(binding)
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * @param holder The ViewHolder to bind data to
     * @param position The position of the item in the list
     */
    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val item=listItemSelected[position]
        // Bind item data to views
        holder.binding.titleTxt.text=item.title
        holder.binding.feeEachItem.text="${item.price}"
        holder.binding.totalEachItem.text="${Math.round(item.numberInCart*item.price)}"
        holder.binding.numberItemTxt.text=item.numberInCart.toString()
        // Load item image using Glide
        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.pic)

        // Load item image using Glide
        holder.binding.plusCartBtn.setOnClickListener{
            managmentCart.plusItem(listItemSelected,position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()// Notify any listener of changes
                }

            })
        }
        holder.binding.minusCartBtn.setOnClickListener{
            managmentCart.minusItem(listItemSelected,position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }
    }
    /**
     * Returns the total number of items in the list.
     * @return The number of items in the cart
     */
    override fun getItemCount(): Int =listItemSelected.size


}
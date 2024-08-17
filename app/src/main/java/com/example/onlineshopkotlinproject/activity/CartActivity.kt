package com.example.onlineshopkotlinproject.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshopkotlinproject.Adapter.CartAdapter
import com.example.onlineshopkotlinproject.R
import com.example.onlineshopkotlinproject.databinding.ActivityCartBinding
import com.example.onlineshopkotlinproject.databinding.ActivityDetailBinding
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.project1762.Helper.ManagmentCart
import com.google.android.gms.common.internal.Objects
/**
 * CartActivity displays the user's shopping cart, including a list of items,
 * their total cost, tax, and delivery charges. It allows users to view and
 * manage their cart's content and navigate back to the previous screen.
 */
class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    // Management instance for handling cart operations
    private lateinit var managmentCart: ManagmentCart
    // Variable to store calculated tax
    private var tax :Double=0.0
    /**
     * Initializes the activity by setting up view binding and configuring
     * the cart list, visibility of UI components, and cart calculations.
     *
     * @param savedInstanceState A Bundle object containing the activity's previously saved state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart= ManagmentCart(this)
        setVisible()
        initCartList()
        calculateCart()

    }
    /**
     * Sets up the RecyclerView for displaying cart items.
     * Configures the adapter and layout manager for the RecyclerView.
     * Also manages the visibility of empty cart and content based on cart items.
     */

    private fun initCartList() {
        binding.viewCart.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.viewCart.adapter=CartAdapter(managmentCart.getListCart(),this,
            object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()// Recalculate cart totals when items change
                }

            })
        // Toggle visibility of empty cart message and content based on the cart's state
        with(binding){
            emptyTxt.visibility=if(managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility=if(managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE

        }
    }
    /**
     * Calculates the total cost of items in the cart, including tax and delivery charges.
     * Updates the corresponding UI elements with the calculated values.
     */
    private fun calculateCart(){
        // Tax percentage
        val parcentTax=0.02
        // Fixed delivery charge
        val delivery=10.0
        // Calculate tax and total cost
        tax=Math.round((managmentCart.getTotalFee()*parcentTax)*100)/100.0
        val total =Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal=Math.round(managmentCart.getTotalFee()*100)/100
        // Update UI with calculated values
        with(binding){
            totalrFeeTxt.text="$$itemTotal"
            taxTxt.text="$$tax"
            deliveryTxt.text="$$delivery"

            totalTxt.text="$$total"

        }
    }
    /**
     * Sets up the visibility and click listeners for the back button.
     * Navigates back to the previous screen when the back button is clicked.
     */
    private fun setVisible() {
        binding.backBtn.setOnClickListener{finish()// Close the activity and return to the previous screen}
    }
}}
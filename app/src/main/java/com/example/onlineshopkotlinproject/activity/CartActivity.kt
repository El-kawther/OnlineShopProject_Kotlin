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

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax :Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart= ManagmentCart(this)
        setVisible()
        initCartList()
        calculateCart()

    }

    private fun initCartList() {
        binding.viewCart.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.viewCart.adapter=CartAdapter(managmentCart.getListCart(),this,
            object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()
                }

            })
        with(binding){
            emptyTxt.visibility=if(managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility=if(managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE

        }
    }
    private fun calculateCart(){
        val parcentTax=0.02
        val delivery=10.0
        tax=Math.round((managmentCart.getTotalFee()*parcentTax)*100)/100.0
        val total =Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal=Math.round(managmentCart.getTotalFee()*100)/100
        with(binding){
            totalrFeeTxt.text="$$itemTotal"
            taxTxt.text="$$tax"
            deliveryTxt.text="$$delivery"

            totalTxt.text="$$total"

        }
    }

    private fun setVisible() {
        binding.backBtn.setOnClickListener{finish()}
    }
}
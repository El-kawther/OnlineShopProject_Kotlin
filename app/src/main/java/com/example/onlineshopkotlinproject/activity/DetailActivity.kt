package com.example.onlineshopkotlinproject.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopkotlinproject.Adapter.ColorAdapter
import com.example.onlineshopkotlinproject.Adapter.SizeAdapter
import com.example.onlineshopkotlinproject.Adapter.SliderAdapter
import com.example.onlineshopkotlinproject.Model.ItemsModel
import com.example.onlineshopkotlinproject.Model.SliderModel
import com.example.onlineshopkotlinproject.R
import com.example.onlineshopkotlinproject.databinding.ActivityDetailBinding
import com.example.project1762.Helper.ManagmentCart
import java.util.ResourceBundle.getBundle

class DetailActivity : BaseActivity() {
    private lateinit var binding:ActivityDetailBinding

    private lateinit var item:ItemsModel
    private var numberOder=1
    private lateinit var managmentCart: ManagmentCart



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart=ManagmentCart(this)

        getBundle()
        banners()
        initLists()
    }

    private fun initLists() {
        val sizeList=ArrayList<String>()
        for (size in item.size){
            sizeList.add(size.toString())
        }
        binding.sizeList.adapter=SizeAdapter(sizeList)
        binding.sizeList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val colorList=ArrayList<String>()
        for (imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }
        binding.colorList.adapter=ColorAdapter(colorList)
        binding.colorList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

    }

    private fun banners() {
        val sliderItems=ArrayList<SliderModel>()
        for(imageUrl in item.picUrl){
            sliderItems.add(SliderModel(imageUrl))
        }
        binding.slider.adapter=SliderAdapter(sliderItems,binding.slider)
        binding.slider.clipToPadding=true
        binding.slider.clipChildren=true
        binding.slider.offscreenPageLimit=1



        if(sliderItems.size>1){
            binding.dotIndicator.visibility= View.VISIBLE
            binding.dotIndicator.attachTo(binding.slider)
        }


    }

    fun getBundle(){
        item=intent.getParcelableExtra("object")!!

        binding.titleTxt.text=item.title
        binding.descpritionTxt.text= item.price.toString()
        binding.priceTxt.text="$"+item.price
        binding.ratingTxt.text="${item.rating} Rating"
        binding.addToCartBtn.setOnClickListener{
            item.numberInCart=numberOder
            managmentCart.insertFood(item)


        }
        binding.backBtn.setOnClickListener{
            finish() }
        binding.cartBtn.setOnClickListener{
        startActivity(Intent(this@DetailActivity,CartActivity::class.java))
        }
    }
}
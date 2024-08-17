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
/**
 * DetailActivity displays detailed information about a selected item, including its images, sizes, colors, and price.
 * It also handles adding the item to the cart and navigating to other activities.
 */
class DetailActivity : BaseActivity() {
    private lateinit var binding:ActivityDetailBinding

    private lateinit var item:ItemsModel
    // Quantity of the item to be ordered
    private var numberOder=1
    // Management instance for handling cart operations
    private lateinit var managmentCart: ManagmentCart


    /**
     * Initializes the activity by setting up view binding and retrieving item details from the intent.
     * Also configures the banners, size list, and color list for the item.
     *
     * @param savedInstanceState A Bundle object containing the activity's previously saved state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart=ManagmentCart(this)

        getBundle()
        banners()
        initLists()
    }

    /**
     * Sets up the RecyclerViews for item sizes and colors.
     * Configures horizontal layout managers and attaches adapters with the relevant data.
     */
    private fun initLists() {
        // Create and populate the list of item sizes
        val sizeList=ArrayList<String>()
        for (size in item.size){
            sizeList.add(size.toString())
        }
        binding.sizeList.adapter=SizeAdapter(sizeList)
        binding.sizeList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        // Create and populate the list of item colors
        val colorList=ArrayList<String>()
        for (imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }
        binding.colorList.adapter=ColorAdapter(colorList)
        binding.colorList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

    }
    /**
     * Configures the image slider (banners) with the item images.
     * Sets up the page transformer effects and shows dot indicators if there are multiple images.
     */
    private fun banners() {
        val sliderItems=ArrayList<SliderModel>()
        for(imageUrl in item.picUrl){
            sliderItems.add(SliderModel(imageUrl))
        }
        binding.slider.adapter=SliderAdapter(sliderItems,binding.slider)
        binding.slider.clipToPadding=true
        binding.slider.clipChildren=true
        binding.slider.offscreenPageLimit=1


        // Show dot indicator if there are multiple images
        if(sliderItems.size>1){
            binding.dotIndicator.visibility= View.VISIBLE
            binding.dotIndicator.attachTo(binding.slider)
        }


    }
    /**
     * Retrieves item details from the intent, sets up UI components with the item's data,
     * and configures click listeners for adding the item to the cart and navigating back or to the cart.
     */
    fun getBundle(){
        item=intent.getParcelableExtra("object")!!

        binding.titleTxt.text=item.title
        binding.descpritionTxt.text= item.price.toString()
        binding.priceTxt.text="$"+item.price
        binding.ratingTxt.text="${item.rating} Rating"
        binding.addToCartBtn.setOnClickListener{
            item.numberInCart=numberOder
            managmentCart.insertFood(item)// Add item to the cart


        }
        binding.backBtn.setOnClickListener{
            finish()// Close the activity and return to the previous screen }
        binding.cartBtn.setOnClickListener{
        startActivity(Intent(this@DetailActivity,CartActivity::class.java))// Navigate to CartActivity
        }
    }
}}
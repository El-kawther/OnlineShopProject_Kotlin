package com.example.onlineshopkotlinproject.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.onlineshopkotlinproject.Adapter.BrandAdapter
import com.example.onlineshopkotlinproject.Adapter.PopularAdapter
import com.example.onlineshopkotlinproject.Model.SliderModel
import com.example.onlineshopkotlinproject.Adapter.SliderAdapter
import com.example.onlineshopkotlinproject.Model.BrandModel
import com.example.onlineshopkotlinproject.ViewModel.MainViewModel
import com.example.onlineshopkotlinproject.databinding.ActivityMainBinding
/**
 * MainActivity serves as the primary screen of the app, displaying banners, brand items,
 * and popular products. It also handles navigation to other activities.
 */
class MainActivity : BaseActivity() {
    // ViewModel instance for managing UI-related data in a lifecycle-conscious way
    private  var viewModel= MainViewModel()
    // View binding instance for efficient access to UI components

    private lateinit var binding: ActivityMainBinding
    /**
     * Initializes the activity and sets up the view components by inflating the layout.
     * Calls methods to initialize the banners, brand items, popular products, and bottom navigation menu.
     *
     * @param savedInstanceState A Bundle object containing the activity's previously saved state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBanner()// Initialize the banner slider
        initBrand()// Initialize the brand section
        initPopular() // Initialize the popular products section
        initBottomMenu()// Initialize the bottom menu for navigation
    }

    /**
     * Sets up the bottom navigation menu, specifically the cart button.
     * Navigates to CartActivity when the cart button is clicked.
     */
    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity,CartActivity::class.java))
        }
    }

    private fun initBanner() {
        // Show progress bar while loading banners
        binding.progressBarBanner.visibility= View.VISIBLE
        // Observe changes in banner data
        viewModel.banners.observe(this, Observer { items-> banners(items)
            //Hide progress bar when data is loaded
            binding.progressBarBanner.visibility=View.GONE

        })
        // Trigger loading of banners from the ViewModel
        viewModel.loadBanners()
    }
    //method configures the banner slider UI component with a list of images.
    private fun  banners(images:List<SliderModel>){
        binding.viewPageSlider.adapter= SliderAdapter(images,binding.viewPageSlider)
        binding.viewPageSlider.clipToPadding=false
        binding.viewPageSlider.clipChildren=false
        binding.viewPageSlider.offscreenPageLimit=3
        binding.viewPageSlider.getChildAt(0).overScrollMode=RecyclerView.OVER_SCROLL_NEVER

//Adds a margin between pages, in this case, 40 pixels. This helps to create
// a space effect between pages when they are scrolled.
        val compositePageTransformer=CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))

        }
        binding.viewPageSlider.setPageTransformer(compositePageTransformer)
        // Show dot indicator if there are multiple images
        if(images.size>1){
            binding.dotIndicator.visibility=View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPageSlider)
        }

    }
    /**
    * Sets up the brand section of the main screen.
    * Observes changes in the brand data and updates the UI accordingly.
    */
    private fun initBrand(){
        binding.progressBarBrand.visibility=View.VISIBLE
        viewModel.brands.observe(this, Observer {
            binding.viewBrand.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.viewBrand.adapter=BrandAdapter(it)
            binding.progressBarBrand.visibility=View.GONE

        })
        viewModel.loadBrand()
    }
    /**
     * Sets up the popular products section of the main screen.
     * Observes changes in the popular products data and updates the UI accordingly.
     */
    private fun initPopular(){
        binding.progressBarPopular.visibility=View.VISIBLE
        viewModel.popular.observe(this, Observer {
            binding.viewPopular.layoutManager=GridLayoutManager(this@MainActivity,2)
            binding.viewPopular.adapter= PopularAdapter(it)
            binding.progressBarPopular.visibility=View.GONE

        })
        viewModel.loadPopular()
    }
}
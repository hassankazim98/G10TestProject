package com.example.myapplication.View

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.denzcoskun.imageslider.models.SlideModel
import com.example.myapplication.Model.ProductsItem
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var productsItem: ProductsItem

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productsItem = intent.extras?.getParcelable("productItem")!!
        setProductItem()

    }

    private fun setProductItem() {
        binding?.apply {
            productName.text=productsItem.title
            productDetails.text=productsItem.description
            rating.text=productsItem.rating.toString()

            val imageList = ArrayList<SlideModel>() // Create image list

            for (i in productsItem.images!!){
                imageList.add(SlideModel(i, ""))
            }
            imageSlider.setImageList(imageList)
        }
    }
}
package com.example.myapplication.View

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.myapplication.Model.ProductModel
import com.example.myapplication.ViewModel.ProductViewModel
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //calling Api
        if (isNetworkAvail(this)) {
            callApi()
        } else {
            Toast.makeText(this, "PLease Check Internet Connection!", Toast.LENGTH_LONG).show()
        }

        //setting Observer
        setObserVer()
    }

    private fun setObserVer() {
        productViewModel.productModelData.observe(this) {
            setProductDetails(it)
        }
    }


    private fun callApi() {
        if (productViewModel != null) {
            productViewModel.getProductDetails()
        }
    }


    //setting Product Details after Getting Response
    private fun setProductDetails(productModel: ProductModel) {
        if (binding.progressBar.isVisible) {
            binding.progressBar.visibility = View.GONE
        }
        binding.mainLayout.visibility = View.VISIBLE
        productModel?.apply {
            binding.productName.text = products?.get(0)?.title
            binding.productDetails.text = products?.get(0)?.description
            binding.rating.text = products?.get(0)?.rating.toString()
            Glide.with(this@MainActivity).load(products?.get(0)?.thumbnail).into(binding.productImg)
        }
    }


    //checking Network Availability
    private fun isNetworkAvail(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

}
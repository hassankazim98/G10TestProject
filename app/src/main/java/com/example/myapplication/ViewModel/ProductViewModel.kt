package com.example.myapplication.ViewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Model.ProductModel
import com.example.myapplication.Retrofit.Api
import com.example.myapplication.Retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    val productModelData = MutableLiveData<ProductModel>()

    fun getProductDetails(): MutableLiveData<ProductModel> {

        val retrofitInstance = RetrofitClient.getInstance().create(Api::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val result = retrofitInstance.getProductDetail()
            if (result != null) {
                result.body()?.let { productModelData.postValue(it) }
            }
        }
        return productModelData
    }
}
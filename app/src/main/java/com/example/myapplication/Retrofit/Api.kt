package com.example.myapplication.Retrofit

import com.example.myapplication.Model.ProductModel
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("/products")
    suspend fun getProductDetail() : Response<ProductModel>
}
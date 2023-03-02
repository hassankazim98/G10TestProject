package com.example.myapplication.Model

data class ProductModel(val total: Int = 0,
                        val limit: Int = 0,
                        val skip: Int = 0,
                        val products: List<ProductsItem>?)
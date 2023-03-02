package com.example.myapplication.Model

data class ProductsItem(val discountPercentage: Double = 0.0,
                        val thumbnail: String = "",
                        val images: List<String>?,
                        val price: Int = 0,
                        val rating: Double = 0.0,
                        val description: String = "",
                        val id: Int = 0,
                        val title: String = "",
                        val stock: Int = 0,
                        val category: String = "",
                        val brand: String = "")
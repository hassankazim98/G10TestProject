package com.example.myapplication.View

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Model.ProductsItem
import com.example.myapplication.databinding.ProductItemBinding


class ListRecyclerAdapter(private var productList: List<ProductsItem>, var activity: Activity) :
    RecyclerView.Adapter<ListRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(productsItem: ProductsItem, activity: Activity) {
            binding?.apply {
                binding.productName.text = productsItem?.title
                binding.rating.text = productsItem?.rating.toString()
                Glide.with(activity).load(productsItem?.thumbnail).into(binding.productImg)

                root.setOnClickListener {
                    val intent = Intent(activity, ProductDetailActivity::class.java)
                    intent.putExtra("productItem", productsItem)
                    activity.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(productList[position], activity)
    }
}
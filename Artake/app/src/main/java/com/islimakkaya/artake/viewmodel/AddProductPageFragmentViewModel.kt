package com.islimakkaya.artake.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.islimakkaya.artake.repository.ArtsDAORepo

class AddProductPageFragmentViewModel: ViewModel() {
    private val arepo = ArtsDAORepo()

    fun addProduct(seller_name: String, product_name: String, product_price: String, product_description: String, product_image_url: String) {
        Log.e("Add Product","$seller_name - $product_name")
        arepo.addProduct(seller_name, product_name, product_price, product_description, product_image_url)

    }
}
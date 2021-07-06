package com.islimakkaya.artake.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.islimakkaya.artake.R
import com.islimakkaya.artake.databinding.FragmentAddProductPageBinding
import com.islimakkaya.artake.retrofit.ApiUtils
import com.islimakkaya.artake.retrofit.ArtsDAOInterface
import com.islimakkaya.artake.viewmodel.AddProductPageFragmentViewModel

class AddProductPageFragment : Fragment() {
    private lateinit var design: FragmentAddProductPageBinding
    private lateinit var viewModel: AddProductPageFragmentViewModel
    private val adaoi: ArtsDAOInterface

    init {
        adaoi = ApiUtils.getArtsDaoInterface()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_add_product_page, container, false)
        design.addProductPageFragment = this
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: AddProductPageFragmentViewModel by viewModels()
        viewModel = temp
    }

    fun addProduct(seller_name: String, product_name: String, product_price: String, product_description: String, product_image_url: String) {
        viewModel.addProduct(seller_name, product_name, product_price, product_description, product_image_url)
    }

}
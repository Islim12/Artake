package com.islimakkaya.artake.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.islimakkaya.artake.R
import com.islimakkaya.artake.adapter.CartAdapter
import com.islimakkaya.artake.databinding.FragmentCartPageBinding
import com.islimakkaya.artake.viewmodel.CartPageFragmentViewModel

class CartPageFragment : Fragment() {
    private lateinit var design: FragmentCartPageBinding
    private lateinit var adapter: CartAdapter
    private lateinit var viewModel: CartPageFragmentViewModel
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_cart_page, container, false)

      viewModel.cartList.observe(viewLifecycleOwner, { list ->
          adapter = CartAdapter(requireContext(), list, viewModel)
          design.cartAdapter = adapter
      })

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CartPageFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

}
package com.islimakkaya.artake.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.translationMatrix
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.islimakkaya.artake.R
import com.islimakkaya.artake.databinding.FragmentArtsDetailPageBinding
import com.islimakkaya.artake.viewmodel.ArtsDetailPageFragmentViewModel
import com.squareup.picasso.Picasso

class ArtsDetailPageFragment : Fragment() {
    private lateinit var design: FragmentArtsDetailPageBinding
    private lateinit var viewModel: ArtsDetailPageFragmentViewModel
    private var txt = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_arts_detail_page, container, false)

        val bundle: ArtsDetailPageFragmentArgs by navArgs()
        val transmittedArt = bundle.artObject

        val url = transmittedArt.art_image_url
        Picasso.get().load(url).into(design.imageViewDetailPage)

        design.artObject = transmittedArt
        design.detailPageFragment = this

        txt = transmittedArt.art_name

        viewModel.chipUnselectedPrice(transmittedArt.art_price)

        viewModel.result.observe(viewLifecycleOwner,{ s->
            design.artPrice = s
        })

        if (transmittedArt.is_campaign_product == 1) {
            design.textViewDetailCampaignPrice.isVisible = true
        }
        return design.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ArtsDetailPageFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun chipClicked(view: Chip, price: String) {
        if (view.isChecked)
            viewModel.chipSelectedPrice(price)
        else
            viewModel.chipUnselectedPrice(price)
    }

    fun onClickAddCart(id: Int, status: Int) {
        val toastTextMsg = "$txt added to the cart!\uD83C\uDF0C"
        Toast.makeText(requireContext(), toastTextMsg, Toast.LENGTH_LONG).show()
        viewModel.addCart(id, status)
    }

}
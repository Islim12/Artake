package com.islimakkaya.artake.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.islimakkaya.artake.R
import com.islimakkaya.artake.adapter.CampaignAdapter
import com.islimakkaya.artake.databinding.FragmentCampaignPageBinding
import com.islimakkaya.artake.viewmodel.CampaignPageFragmentViewModel


class CampaignPageFragment : Fragment() {
    private lateinit var design: FragmentCampaignPageBinding
    private lateinit var adapter: CampaignAdapter
    private lateinit var viewModel: CampaignPageFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_campaign_page, container, false)

        viewModel.campaignList.observe(viewLifecycleOwner, { list ->
            adapter = CampaignAdapter(requireContext(), list, viewModel)
            design.campaignAdapter = adapter
        })



        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CampaignPageFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

}

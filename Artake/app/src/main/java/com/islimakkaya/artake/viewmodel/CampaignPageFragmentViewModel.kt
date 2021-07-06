package com.islimakkaya.artake.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.islimakkaya.artake.entity.Arts
import com.islimakkaya.artake.repository.ArtsDAORepo

class CampaignPageFragmentViewModel: ViewModel() {
    private val arepo = ArtsDAORepo()
    val campaignList: MutableLiveData<List<Arts>>
    var campaignResult = MutableLiveData<String>()

    init {
        loadCampaignArts()
        campaignList = arepo.bringCampaignArts()
        campaignResult = arepo.bringCampaignResult()
    }

    fun loadCampaignArts() {
        arepo.getCampaignArts()
    }

    fun campaignPrice(price: String){
        arepo.getCampaignPrice(price)
    }
}
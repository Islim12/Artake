package com.islimakkaya.artake.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.islimakkaya.artake.entity.Arts
import com.islimakkaya.artake.repository.ArtsDAORepo

class HomePageFragmentViewModel: ViewModel() {
    private val arepo = ArtsDAORepo()
    val artsList: MutableLiveData<List<Arts>>
    var campaignResult = MutableLiveData<String>()

    init {
        loadArts()
        artsList = arepo.bringArts()
        campaignResult = arepo.bringCampaignResult()
    }

    fun loadArts() {
        arepo.getAllArts()
    }

    fun addCart(id: Int, status: Int) {
        arepo.addCart(id, status)
    }

    fun doCampaign(id: Int, isCampaign: Int) {
        arepo.campaignArt(id, isCampaign)
    }

    fun campaignPrice(price: String){
        arepo.getCampaignPrice(price)
    }
}
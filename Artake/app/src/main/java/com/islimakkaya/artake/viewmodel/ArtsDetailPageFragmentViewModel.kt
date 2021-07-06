package com.islimakkaya.artake.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.islimakkaya.artake.repository.ArtsDAORepo

class ArtsDetailPageFragmentViewModel: ViewModel() {
    private val arepo = ArtsDAORepo()
    var result = MutableLiveData<String>()
    var campaignResult = MutableLiveData<String>()

    init {
        result = arepo.bringResult()
        campaignResult = arepo.bringCampaignResult()
    }

    fun campaignPrice(price: String) {
        arepo.getCampaignPrice(price)
    }

    fun chipSelectedPrice(price: String) {
        arepo.getChipSelectedPrice(price)
    }

    fun chipUnselectedPrice(price: String) {
        arepo.getChipUnselectedPrice(price)
    }

    fun addCart(id: Int, status: Int) {
        arepo.addCart(id, status)
    }
}
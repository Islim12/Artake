package com.islimakkaya.artake.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.islimakkaya.artake.entity.Arts
import com.islimakkaya.artake.repository.ArtsDAORepo

class CartPageFragmentViewModel: ViewModel() {
    private val arepo = ArtsDAORepo()
    val cartList: MutableLiveData<List<Arts>>

    init {
        loadCartArts()
        cartList = arepo.bringCartArts()
    }

    fun loadCartArts() {
        arepo.cartArts()
    }

    fun deleteFromCart(id: Int, cartStatus: Int) {
        arepo.deleteFromCart(id, cartStatus)
        loadCartArts()
    }
}
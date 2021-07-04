package com.islimakkaya.artake.viewmodel

import androidx.lifecycle.ViewModel
import com.islimakkaya.artake.repository.ArtsDAORepo

class LogInPageFragmentViewModel: ViewModel() {
    private val arepo = ArtsDAORepo()

    fun logIn(mail_address: String, password: String) {
        arepo.logIn(mail_address, password)

    }
}
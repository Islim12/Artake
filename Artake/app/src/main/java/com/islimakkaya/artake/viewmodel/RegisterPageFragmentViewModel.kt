package com.islimakkaya.artake.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.islimakkaya.artake.repository.ArtsDAORepo

class RegisterPageFragmentViewModel: ViewModel() {
    private val arepo = ArtsDAORepo()

    fun register(mail_adress: String, password: String, name_surname: String, phone_number: String) {
        Log.e("Register Person","$name_surname - $password")
        arepo.register(mail_adress, password, name_surname, phone_number)
    }
}
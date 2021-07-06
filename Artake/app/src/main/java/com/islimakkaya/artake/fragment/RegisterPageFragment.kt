package com.islimakkaya.artake.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.islimakkaya.artake.R
import com.islimakkaya.artake.databinding.FragmentRegisterPageBinding
import com.islimakkaya.artake.viewmodel.RegisterPageFragmentViewModel

class RegisterPageFragment : Fragment() {
    private lateinit var design: FragmentRegisterPageBinding
    private lateinit var viewModel: RegisterPageFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_register_page, container, false)
        design.registerPageFragment = this
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp:RegisterPageFragmentViewModel by viewModels()
        viewModel = temp
    }

    fun buttonRegister(mail_adress: String, password: String, name_surname: String, phone_number: String){
        viewModel.register(mail_adress, password, name_surname, phone_number)
    }
}
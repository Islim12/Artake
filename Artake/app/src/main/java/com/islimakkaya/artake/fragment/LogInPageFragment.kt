package com.islimakkaya.artake.fragment

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.islimakkaya.artake.R
import com.islimakkaya.artake.databinding.FragmentLogInPageBinding
import com.islimakkaya.artake.entity.ProfileAnswer
import com.islimakkaya.artake.retrofit.ApiUtils
import com.islimakkaya.artake.retrofit.ArtsDAOInterface
import com.islimakkaya.artake.viewmodel.LogInPageFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LogInPageFragment : Fragment() {
    private lateinit var design: FragmentLogInPageBinding
    private lateinit var viewModel: LogInPageFragmentViewModel
    private val adaoi: ArtsDAOInterface

    init {
        adaoi = ApiUtils.getArtsDaoInterface()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in_page, container, false)
        design.logInPageFragment = this
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp:LogInPageFragmentViewModel by viewModels()
        viewModel = temp
    }

    fun onClickFAB(view: View){
        Navigation.findNavController(view).navigate(R.id.toRegisterPage)
    }

    fun logIn(view: View, mail_address: String, password: String) {
        //viewModel.logIn(mail_address, password)
        var value: String
            adaoi.doLogIn(mail_address, password).enqueue(object : Callback<ProfileAnswer> {
                override fun onResponse(call: Call<ProfileAnswer>?, response: Response<ProfileAnswer>) {
                    Log.e("Success Status", response.body().success.toString())
                    value = response.body().success.toString()
                    val listOfPersonData = response.body().profile
                    for (p in listOfPersonData) {
                        Log.e("**********", "***********")
                        Log.e("Value", p.value.toString())
                        Log.e("Mail Address", p.mail_address)
                        Log.e("Password", p.password)
                        Log.e("Full Name", p.name_surname)
                        Log.e("Phone Number", p.phone_number)
                        }

                    if (value.equals("1")) {
                        val mPrefs: SharedPreferences = requireActivity().getPreferences(MODE_PRIVATE)
                        val prefsEditor: SharedPreferences.Editor = mPrefs.edit()
                        val gson = Gson()
                        val json = gson.toJson(listOfPersonData[0])
                        prefsEditor.putString("SerializableObject", json)
                        prefsEditor.commit()
                        Navigation.findNavController(view).navigate(R.id.toHomePage)
                    }
                }

                override fun onFailure(call: Call<ProfileAnswer>?, t: Throwable?) {}
            })
    }
}
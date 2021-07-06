package com.islimakkaya.artake.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.islimakkaya.artake.R
import com.islimakkaya.artake.databinding.FragmentProfilePageBinding
import com.islimakkaya.artake.entity.Profile
import com.islimakkaya.artake.retrofit.ApiUtils
import com.islimakkaya.artake.retrofit.ArtsDAOInterface


class ProfilePageFragment : Fragment() {
    private val adaoi: ArtsDAOInterface
    private lateinit var design: FragmentProfilePageBinding

    init {
        adaoi = ApiUtils.getArtsDaoInterface()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_page, container, false)

        val mPrefs: SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val gson = Gson()
        val json: String = mPrefs.getString("SerializableObject", "").toString()
        val yourSerializableObject = gson.fromJson<Profile>(json, Profile::class.java)

        design.personList = yourSerializableObject
        return design.root
    }


}
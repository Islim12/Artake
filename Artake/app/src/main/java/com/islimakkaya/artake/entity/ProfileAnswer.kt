package com.islimakkaya.artake.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileAnswer(@SerializedName("kullanicilar")
                         @Expose
                         var profile: List<Profile>,
                         @SerializedName("success")
                         @Expose
                         var success: Int) {
}
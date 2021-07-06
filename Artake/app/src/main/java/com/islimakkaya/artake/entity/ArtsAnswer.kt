package com.islimakkaya.artake.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArtsAnswer(@SerializedName("urunler")
                      @Expose
                      var arts: List<Arts>,
                      @SerializedName("success")
                      @Expose
                      var success: Int) {
}
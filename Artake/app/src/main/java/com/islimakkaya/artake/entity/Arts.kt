package com.islimakkaya.artake.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Arts(@SerializedName("id")
                @Expose
                var art_id: Int,
                @SerializedName("urun_adi")
                @Expose
                var art_name: String,
                @SerializedName("satici_adi")
                @Expose
                var seller_name: String,
                @SerializedName("urun_gorsel_url")
                @Expose
                var art_image_url: String,
                @SerializedName("urun_fiyat")
                @Expose
                var art_price: String,
                @SerializedName("urun_aciklama")
                @Expose
                var art_description: String,
                @SerializedName("sepet_durum")
                @Expose
                var cart_status: Int,
                @SerializedName("urun_indirimli_mi")
                @Expose
                var is_campaign_product: Int): Serializable {
}
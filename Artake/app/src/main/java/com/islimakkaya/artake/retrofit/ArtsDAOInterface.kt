package com.islimakkaya.artake.retrofit


import com.islimakkaya.artake.entity.ArtsAnswer
import com.islimakkaya.artake.entity.CRUDAnswer
import com.islimakkaya.artake.entity.ProfileAnswer
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ArtsDAOInterface {
    @POST("urunler.php")
    @FormUrlEncoded
    fun allArts(@Field("satici_adi") seller_name: String): Call<ArtsAnswer>

    @POST("giris_yap.php")
    @FormUrlEncoded
    fun doLogIn(@Field("mail_adres") mail_address: String,
                     @Field("sifre") password: String): Call<ProfileAnswer>

    @POST("indirimli_urun_durum_degistir.php")
    @FormUrlEncoded
    fun isCampaignProduct(@Field("id") art_id: Int,
                          @Field("urun_indirimli_mi") art_campaign_control: Int): Call<CRUDAnswer>

    @POST("sepet_durum_degistir.php")
    @FormUrlEncoded
    fun changeCartStatus(@Field("id") art_id: Int,
                         @Field("sepet_durum") cart_status: Int): Call<CRUDAnswer>

    @POST("urun_ekle.php")
    @FormUrlEncoded
    fun addProduct(@Field("satici_adi") seller_name: String,
                   @Field("urun_adi") product_name: String,
                   @Field("urun_fiyat") product_price: String,
                   @Field("urun_aciklama") product_description: String,
                   @Field("urun_gorsel_url") product_image_url: String): Call<CRUDAnswer>

    @POST("uye_ol.php")
    @FormUrlEncoded
    fun addNewMember(@Field("mail_adres") mail_address: String,
                     @Field("sifre") password: String,
                     @Field("ad_soyad") name_surname: String,
                     @Field("telefon") phone_number: String): Call<CRUDAnswer>

}
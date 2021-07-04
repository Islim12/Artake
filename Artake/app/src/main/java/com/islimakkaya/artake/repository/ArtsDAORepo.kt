package com.islimakkaya.artake.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.islimakkaya.artake.entity.Arts
import com.islimakkaya.artake.entity.ArtsAnswer
import com.islimakkaya.artake.entity.CRUDAnswer
import com.islimakkaya.artake.entity.ProfileAnswer
import com.islimakkaya.artake.retrofit.ApiUtils
import com.islimakkaya.artake.retrofit.ArtsDAOInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtsDAORepo {
    private val adaoi: ArtsDAOInterface
    private val artsList: MutableLiveData<List<Arts>>
    private val cartList: MutableLiveData<List<Arts>>
    private val campaignList: MutableLiveData<List<Arts>>

    var priceResult = MutableLiveData<String>()
    var campaignPriceResult = MutableLiveData<String>()

    init {
        priceResult = MutableLiveData<String>("0")
        campaignPriceResult = MutableLiveData<String>("0")
        adaoi = ApiUtils.getArtsDaoInterface()
        artsList = MutableLiveData()
        cartList = MutableLiveData()
        campaignList = MutableLiveData()
    }

    fun register(mail_address: String, password: String, name_surname: String, phone_number: String) {
        adaoi.addNewMember(mail_address, password, name_surname, phone_number)
            .enqueue(object : Callback<CRUDAnswer> {
                override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>) {
                    Log.e("Success Status", response.body().success.toString())
                    Log.e("Message", response.body().message)
                }

                override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {}
            })
    }

    fun addProduct(seller_name: String, product_name: String, product_price: String, product_description: String, product_image_url: String) {
        adaoi.addProduct(seller_name, product_name, product_price, product_description, product_image_url).enqueue(object : Callback<CRUDAnswer> {
            override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>) {
                Log.e("Adding Product","$seller_name - $product_name")
                Log.e("Success Status", response.body().success.toString())
                Log.e("Message", response.body().message)
            }

            override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {
                Log.e("Error", "failed")
            }
        })
    }

    fun logIn(mail_address: String, password: String) {
        adaoi.doLogIn(mail_address, password).enqueue(object : Callback<ProfileAnswer> {
            override fun onResponse(call: Call<ProfileAnswer>?, response: Response<ProfileAnswer>) {
                Log.e("Success Status", response.body().success.toString())
                val listOfPersonData = response.body().profile
                for (p in listOfPersonData) {
                    Log.e("**********", "***********")
                    Log.e("Value", p.value.toString())
                    Log.e("Mail Address", p.mail_address)
                    Log.e("Password", p.password)
                    Log.e("Full Name", p.name_surname)
                    Log.e("Phone Number", p.phone_number)
                }
            }

            override fun onFailure(call: Call<ProfileAnswer>?, t: Throwable?) {}
        })
    }

    fun bringArts(): MutableLiveData<List<Arts>> {
        return artsList
    }

    fun bringCartArts(): MutableLiveData<List<Arts>> {
        return cartList
    }

    fun bringCampaignArts(): MutableLiveData<List<Arts>> {
        return campaignList
    }

    fun getAllArts() {
        adaoi.allArts("islimakkaya").enqueue(object : Callback<ArtsAnswer> {
            override fun onResponse(call: Call<ArtsAnswer>?, response: Response<ArtsAnswer>) {
                val list: List<Arts> = response.body().arts
                artsList.value = list
              /* for (p in list) {
                    Log.e("Id", p.art_id.toString())
                    Log.e("Name", p.art_name)
                }
               */
            }

            override fun onFailure(call: Call<ArtsAnswer>?, t: Throwable?) {
                Log.e("Error", "failed")
            }
        })
    }

    fun cartArts() {
        adaoi.allArts("islimakkaya").enqueue(object : Callback<ArtsAnswer> {
            override fun onResponse(call: Call<ArtsAnswer>?, response: Response<ArtsAnswer>) {
                val list: List<Arts> = response.body().arts
                val productInCartList = ArrayList<Arts>()
                artsList.value = list
                for (i in 0 until list.size) {
                    if (list.get(i).cart_status == 1) productInCartList.add(list.get(i)) else productInCartList.remove(list.get(i))
                }
                cartList.value = productInCartList
            }

            override fun onFailure(call: Call<ArtsAnswer>?, t: Throwable?) {
                Log.e("Error", "failed")
            }
        })
    }

    fun getCampaignArts() {
        adaoi.allArts("islimakkaya").enqueue(object : Callback<ArtsAnswer> {
            override fun onResponse(call: Call<ArtsAnswer>?, response: Response<ArtsAnswer>) {
                val list: List<Arts> = response.body().arts
                val productInCampaignList = ArrayList<Arts>()
                campaignList.value = list
                for (i in 0 until list.size) {
                    if (list.get(i).is_campaign_product == 1) productInCampaignList.add(list.get(i))
                }
                campaignList.value = productInCampaignList
            }

            override fun onFailure(call: Call<ArtsAnswer>?, t: Throwable?) {
                Log.e("Error", "failed")
            }
        })
    }

    fun getCampaignPrice(price: String) {
        val campaignPrice = price.toDouble() / 2
        campaignPriceResult.value = campaignPrice.toString()
    }

    fun getChipSelectedPrice(price: String) {
        val withFramedPrice = price.toDouble() + 10
        priceResult.value = withFramedPrice.toString()
    }

    fun getChipUnselectedPrice(price: String) {
        priceResult.value = price
    }

    fun bringResult(): MutableLiveData<String> {
        return priceResult
    }

    fun bringCampaignResult(): MutableLiveData<String> {
        return campaignPriceResult
    }

    fun addCart(id: Int, cartStatus: Int) {
        adaoi.changeCartStatus(id, cartStatus)
                .enqueue(object : Callback<CRUDAnswer> {
                    override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>) {
                        Log.e("Adding to Cart", id.toString())
                        Log.e("Success Status", response.body().success.toString())
                        Log.e("Cart Status", cartStatus.toString())
                        Log.e("Message", response.body().message)
                    }

                    override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {}
                })
    }

    fun deleteFromCart(id: Int, cartStatus: Int) {
        adaoi.changeCartStatus(id, cartStatus)
                .enqueue(object : Callback<CRUDAnswer> {
                    override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>) {
                        Log.e("Deleting from Cart", id.toString())
                        Log.e("Success Status", response.body().success.toString())
                        Log.e("Cart Status", cartStatus.toString())
                        Log.e("Message", response.body().message)
                    }

                    override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {}
                })
    }

    fun campaignArt(id: Int, isCampaign: Int) {
        adaoi.isCampaignProduct(id, isCampaign)
                .enqueue(object : Callback<CRUDAnswer> {
                    override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>) {
                        Log.e("Campaign Art", id.toString())
                        Log.e("Success Status", response.body().success.toString())
                        Log.e("Message", response.body().message)
                    }

                    override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {}
                })
    }
}
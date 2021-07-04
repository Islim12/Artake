package com.islimakkaya.artake.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "http://upschool.canerture.com/"

        fun getArtsDaoInterface(): ArtsDAOInterface {
            return RetrofitClient.getClient(BASE_URL).create(ArtsDAOInterface::class.java)
        }
    }
}
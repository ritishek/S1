package com.example.s1

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface RetrofitInterface {

    @POST("/login")
    fun executeLogin(@Body map: HashMap<String?, String?>?): Call<LoginResult?>?

    @POST("/signup")
    fun executeSignup(@Body map: HashMap<String, String>): Call<Void?>?

    @POST("/details")
    fun executeDetail(@Body map: HashMap<String, String>): Call<Void?>?

    @POST("/education")
    fun executeEducation(@Body map: HashMap<String, String>): Call<Void?>?

    @POST("/preference")
    fun executePreference(@Body map: HashMap<String, String>): Call<Void?>?

    @Multipart
    @POST("/upload")
    fun postImage(@Part image: MultipartBody.Part?, @Part("upload") name: RequestBody?): Call<ResponseBody?>?

    @GET("/keydetails")
    fun getDetails(@Query("phone") phone: String?): Call<LoginResult?>?


}
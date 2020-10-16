package biz.eldmandate.kotlinsample.api

import biz.eldmandate.kotlinsample.model.NasaApod
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Vaghela Mithun R. on 15/10/20.
 * Smart Call Center Solution Pvt. Ltd.
 * mithun@smartadvancedtech.com
 */
interface RestClient {
    @GET("planetary/apod")
    fun getNasaApodData(@Query("api_key") apiKey: String) : Call<NasaApod>
}
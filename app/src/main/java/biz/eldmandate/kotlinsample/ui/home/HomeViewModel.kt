package biz.eldmandate.kotlinsample.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import biz.eldmandate.kotlinsample.BuildConfig
import biz.eldmandate.kotlinsample.api.RestClient
import biz.eldmandate.kotlinsample.model.NasaApod
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var nasaApod: MutableLiveData<NasaApod> = MutableLiveData()

    fun getNasaApodData(apiKey: String): MutableLiveData<NasaApod> {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RestClient::class.java)
        val call = service.getNasaApodData(apiKey)

        call.enqueue(object : Callback<NasaApod> {
            override fun onResponse(call: Call<NasaApod>, response: Response<NasaApod>) {
                if (response.code() == 200) {
                    nasaApod.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<NasaApod>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return nasaApod;
    }
}
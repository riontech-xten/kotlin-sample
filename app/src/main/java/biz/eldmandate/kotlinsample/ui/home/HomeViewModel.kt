package biz.eldmandate.kotlinsample.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import biz.eldmandate.kotlinsample.BuildConfig
import biz.eldmandate.kotlinsample.api.RestClient
import biz.eldmandate.kotlinsample.model.NasaApod
import biz.eldmandate.kotlinsample.utils.LiveDataResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeViewModel : ViewModel() {

    val nasaApod: MutableLiveData<LiveDataResult<NasaApod>> = MutableLiveData()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun setLoadingVisibility(visible: Boolean) {
        loadingLiveData.postValue(visible)
    }

    fun getNasaApodData(apiKey: String): MutableLiveData<LiveDataResult<NasaApod>> {
        setLoadingVisibility(true)
        nasaApod.postValue(LiveDataResult.loading())
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RestClient::class.java)
        val call = service.getNasaApodData(apiKey)

        call.enqueue(object : Callback<NasaApod> {
            override fun onResponse(call: Call<NasaApod>, response: Response<NasaApod>) {
                if (response.code() == 200) {
                    setLoadingVisibility(false)
                    nasaApod.postValue(LiveDataResult.success(response.body()))
                }
            }

            override fun onFailure(call: Call<NasaApod>, t: Throwable) {
                setLoadingVisibility(false)
                nasaApod.postValue(LiveDataResult.error(t))
            }
        })

        return nasaApod;
    }
}
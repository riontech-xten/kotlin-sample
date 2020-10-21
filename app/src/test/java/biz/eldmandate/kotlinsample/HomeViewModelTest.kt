package biz.eldmandate.kotlinsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import biz.eldmandate.kotlinsample.api.RestClient
import biz.eldmandate.kotlinsample.model.NasaApod
import biz.eldmandate.kotlinsample.ui.home.HomeViewModel
import biz.eldmandate.kotlinsample.utils.LiveDataResult
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.Response.error


/**
 * Created by Vaghela Mithun R. on 21/10/20.
 * Smart Call Center Solution Pvt. Ltd.
 * mithun@smartadvancedtech.com
 */

@RunWith(JUnit4::class)
class HomeViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var restClient: RestClient

    lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        homeViewModel = HomeViewModel()
    }

    @Test
    fun fetchPositiveResponse() {
        `when`(restClient.getNasaApodData(ArgumentMatchers.anyString())).thenAnswer {
            return@thenAnswer Response.success(NasaApod())
        }

        val observer = mock(Observer::class.java) as Observer<LiveDataResult<NasaApod>>
        this.homeViewModel.nasaApod.observeForever(observer)

        this.homeViewModel.getNasaApodData(ArgumentMatchers.anyString())

        assertNotNull(this.homeViewModel.nasaApod.value)
        assertEquals(LiveDataResult.Status.SUCCESS, this.homeViewModel.nasaApod.value?.status)
    }

    @Test
    fun fetchRepositories_error() {
        `when`(restClient.getNasaApodData(ArgumentMatchers.anyString())).thenAnswer {
            return@thenAnswer error<NasaApod>(500, null)
        }

        val observer = mock(Observer::class.java) as Observer<LiveDataResult<NasaApod>>
        this.homeViewModel.nasaApod.observeForever(observer)

        this.homeViewModel.getNasaApodData(ArgumentMatchers.anyString())

        assertNotNull(this.homeViewModel.nasaApod.value)
        assertEquals(LiveDataResult.Status.ERROR, this.homeViewModel.nasaApod.value?.status)
        assert(this.homeViewModel.nasaApod.value?.err is Throwable)
    }

    @Test
    fun setLoadingVisibility_onSuccess() {
        `when`(restClient.getNasaApodData(com.nhaarman.mockitokotlin2.any())).thenAnswer {
            return@thenAnswer Response.success(NasaApod())
        }

        val spiedViewModel = com.nhaarman.mockitokotlin2.spy(this.homeViewModel)

        spiedViewModel.getNasaApodData(ArgumentMatchers.anyString())
        verify(spiedViewModel, times(2)).setLoadingVisibility(ArgumentMatchers.anyBoolean())
    }

    @Test
    fun setLoadingVisibility_onError() {
        `when`(restClient.getNasaApodData(ArgumentMatchers.anyString())).thenAnswer {
            return@thenAnswer error<NasaApod>(500, null)
        }

        val spiedViewModel = com.nhaarman.mockitokotlin2.spy(this.homeViewModel)

        spiedViewModel.getNasaApodData(ArgumentMatchers.anyString())
        verify(spiedViewModel, times(2)).setLoadingVisibility(ArgumentMatchers.anyBoolean())
    }

    @Test
    fun setLoadingVisibility_onNoData() {
        `when`(restClient.getNasaApodData(ArgumentMatchers.anyString())).thenAnswer {
            return@thenAnswer Response.success(NasaApod())
        }

        val spiedViewModel = com.nhaarman.mockitokotlin2.spy(this.homeViewModel)

        spiedViewModel.getNasaApodData(ArgumentMatchers.anyString())
        verify(spiedViewModel, times(2)).setLoadingVisibility(ArgumentMatchers.anyBoolean())
    }
}
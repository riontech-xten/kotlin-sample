package biz.eldmandate.kotlinsample.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import biz.eldmandate.kotlinsample.R
import biz.eldmandate.kotlinsample.databinding.FragmentHomeBinding
import biz.eldmandate.kotlinsample.model.NasaApod
import biz.eldmandate.kotlinsample.utils.LiveDataResult

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)
        loadData()
        return homeBinding.root
    }

    private fun loadData() {
        homeBinding.progressBar.visibility = View.VISIBLE;
        homeViewModel.getNasaApodData(getString(R.string.api_key))
            .observe(viewLifecycleOwner, dataObserver)
        homeViewModel.loadingLiveData.observe(viewLifecycleOwner, loadingObserver)
    }

    private val dataObserver = Observer<LiveDataResult<NasaApod>> { result ->
        when (result?.status) {
            LiveDataResult.Status.LOADING -> {
                homeBinding.progressBar.visibility = View.VISIBLE;
            }

            LiveDataResult.Status.ERROR -> {
                Toast.makeText(context, "Something went wrong! try again", Toast.LENGTH_LONG)
                    .show();
                homeBinding.progressBar.visibility = View.GONE;
            }

            LiveDataResult.Status.SUCCESS -> {
                // Data from API
                homeBinding.progressBar.visibility = View.GONE;
                homeBinding.apod = result.data
                homeBinding.executePendingBindings()
            }
        }
    }

    private val loadingObserver = Observer<Boolean> { visibile ->

    }
}
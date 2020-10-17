package biz.eldmandate.kotlinsample.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import biz.eldmandate.kotlinsample.R
import biz.eldmandate.kotlinsample.databinding.FragmentHomeBinding

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
        homeViewModel.getNasaApodData(getString(R.string.api_key)).observe(viewLifecycleOwner, {
            if (it != null) {
                Log.d("HomeFragment", "loadData: " + it.toString())
                homeBinding.progressBar.visibility = View.GONE
                homeBinding.apod = it;
                homeBinding.executePendingBindings()
            }
        })
    }
}
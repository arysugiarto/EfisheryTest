package com.efishery.test.ui.home

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.efishery.test.R
import com.efishery.test.data.remote.Result
import com.efishery.test.data.remote.model.AreaResponse
import com.efishery.test.data.remote.model.BannerSliderModel
import com.efishery.test.databinding.FragmentHomeBinding
import com.efishery.test.ui.home.HomeAdapter.bannerSliderAdapter
import com.efishery.test.ui.main.MainFragment.Companion.parentToolbar
import com.efishery.test.util.*
import com.efishery.test.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding<FragmentHomeBinding>()
    private val viewModel by hiltNavGraphViewModels<HomeViewModel>(R.id.home)
    private val productAdapter = HomeAdapter.productAdapter
    private var bannerSliderAdapter = HomeAdapter.bannerSliderAdapter

    private var listBanner = emptyList<BannerSliderModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentToolbar?.isVisible = false

        initView()
        initViewModel()
        initViewModelCallback()
        initOnClick()
    }

    private fun listStaticData() {
        listBanner = listOf(
            BannerSliderModel(
                1,
                "https://i.pinimg.com/564x/1b/10/d2/1b10d286f8ee1185c4689bf7d545c9e2.jpg",
            ),
            BannerSliderModel(
                2,
                "https://i.pinimg.com/564x/49/2b/95/492b95e34690ec6d3675fdd7240a4f05.jpg",
            ),
            BannerSliderModel(
                3,
                "https://i.pinimg.com/564x/66/86/7b/66867bfdb3b58320bb3e82384b7fe03f.jpg",
            ),
        )

    }

    private fun initView() {
        listStaticData()
        bannerSliderAdapter.items = listBanner
        initClickAdapter()
        setDataArea()
    }

    private fun initViewModel() {
        viewModel.requestProduct()
        viewModel.requestArea()
    }

    private fun initViewModelCallback() {
        initProductCallback()
        initBannerSliderView()
    }

    private fun initBannerSliderView() {
        binding.vpBannerSlider.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = bannerSliderAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                }
            })
            TabLayoutMediator(binding.tlBannerSlider, this) { _, _ -> }.attach()

            autoScroll(
                lifecycleScope = viewLifecycleOwner.lifecycleScope,
                interval = 3000L
            )
        }
    }

    private fun initProductCallback() {
        viewModel.product.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                }
                is Result.Success -> {
                    productAdapter.items = result.data.orEmpty()
                    Timber.e("test")
                }
                is Result.Error<*> -> {

                }
                else -> {
                }
            }
        }
        binding.rvFish.adapter = productAdapter
    }

    private fun setDataArea(){
        val areaSavedState = viewModel.areaSavedState

        if (areaSavedState.area != null){
            binding.tvProvince.textOrNull = areaSavedState.area
        }
    }

    private fun initClickAdapter() {
        HomeAdapter.SetOnClickItem.setOnClickItemListener { item ->
            navController.navigateOrNull(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    item.komoditas,
                    item.price,
                    item.areaProvinsi,
                    item.areaKota,
                    item.size
                )
            )
        }
    }

    private fun initOnClick() {
        binding.apply {
            tvProvince.setOnClickListener(onClickCallback)
            btnLogin.setOnClickListener(onClickCallback)
        }
    }

    private val onClickCallback = View.OnClickListener { view ->
        when (view) {
            binding.tvProvince -> {
                navController.navigateOrNull(
                    HomeFragmentDirections.actionHomeFragmentToAreaFragment()
                )
            }
            binding.btnLogin -> {
                context?.toast(context?.getString(R.string.feature_on_going))
            }
        }
    }


}
package com.efishery.test.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.efishery.test.R
import com.efishery.test.data.remote.Result
import com.efishery.test.data.remote.model.AreaResponse
import com.efishery.test.data.remote.model.BannerSliderModel
import com.efishery.test.databinding.FragmentDetailBinding
import com.efishery.test.databinding.FragmentHomeBinding
import com.efishery.test.ui.main.MainFragment.Companion.parentNavigation
import com.efishery.test.util.*
import com.efishery.test.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding<FragmentDetailBinding>()
    private val args by navArgs<DetailFragmentArgs>()
    private var listFish = emptyList<BannerSliderModel>()
    private var bannerSliderAdapter = HomeAdapter.bannerSliderAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentNavigation?.isVisible = false
        initView()
        initOnClick()
        initViewModelCallback()
    }

    private fun listStaticData() {
        listFish = listOf(
            BannerSliderModel(
                1,
                "https://i.pinimg.com/564x/fc/72/42/fc7242673bcea10d8af5f90f17696eb9.jpg",
            ),
            BannerSliderModel(
                2,
                "https://i.pinimg.com/564x/fb/bb/0b/fbbb0bfa63fc42d3f515fea0e0ed742c.jpg",
            ),
            BannerSliderModel(
                3,
                "https://i.pinimg.com/564x/74/71/54/747154b30ba697ced5bfb6e1f6b14d54.jpg",
            ),
        )

    }

    private fun initView() {
        setData()
        listStaticData()
        bannerSliderAdapter.items = listFish
    }

    private fun initViewModelCallback() {
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

        }
    }




    private fun setData(){
        binding.apply {
            tvName.textOrNull = args.nameFish
            tvPrice.textOrNull = "Rp" + args.price +"/Kg"
            tvProvince.textOrNull = args.province +"-"+ args.city
            tvStock.textOrNull = args.stock
        }
    }

    private fun initOnClick() {
        binding.apply {
            btnOrder.setOnClickListener(onClickCallback)
        }
    }

    private val onClickCallback = View.OnClickListener { view ->
        when (view) {
            binding.btnOrder -> {
                navController.navigateOrNull(
                    DetailFragmentDirections.actionDetailFragmentToOrderFragment(
                        args.nameFish,
                        args.province,
                        args.city
                    )
                )
            }
        }
    }

}
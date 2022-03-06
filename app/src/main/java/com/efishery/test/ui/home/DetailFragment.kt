package com.efishery.test.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.efishery.test.R
import com.efishery.test.data.remote.Result
import com.efishery.test.data.remote.model.AreaResponse
import com.efishery.test.databinding.FragmentDetailBinding
import com.efishery.test.databinding.FragmentHomeBinding
import com.efishery.test.util.*
import com.efishery.test.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding<FragmentDetailBinding>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initOnClick()
    }

    private fun initView() {
        setData()
    }

    private fun setData(){
        binding.apply {
            tvName.textOrNull = args.nameFish
            tvPrice.textOrNull = "Rp" + args.price
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
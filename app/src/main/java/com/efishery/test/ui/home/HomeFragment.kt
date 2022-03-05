package com.efishery.test.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.efishery.test.R
import com.efishery.test.data.remote.Result
import com.efishery.test.databinding.FragmentHomeBinding
import com.efishery.test.util.*
import com.efishery.test.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding<FragmentHomeBinding>()
    private val viewModel by viewModels<HomeViewModel>()
    private val productAdapter = HomeAdapter.productAdapter
    lateinit var searchView: SearchView

    var keyword = emptyString

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
        initViewModelCallback()
        initOnClick()
    }

    private fun initView() {
//        initTextDelayOnType()
//        binding.etSearch.setOnEditorActionListener(onImeSearchClicked)
    }


    private fun initViewModel() {
        viewModel.requestProduct()
    }

    private fun initViewModelCallback() {
        initProductCallback()
    }

    private fun initProductCallback() {
        viewModel.product.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                }
                is Result.Success -> {
                    productAdapter.items = result.data. orEmpty()
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

    private fun search(){

    }



    private fun initOnClick() {
        binding.apply {
//            boxSearch.setOnClickListener(onClickCallback)
        }
    }

    private val onClickCallback = View.OnClickListener { view ->
        when (view) {
//            binding.boxSearch -> {
//            }
        }
    }

}
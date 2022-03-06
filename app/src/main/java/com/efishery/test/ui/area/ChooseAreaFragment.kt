package com.efishery.test.ui.area

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.efishery.test.R
import com.efishery.test.data.remote.Result
import com.efishery.test.data.remote.model.Area
import com.efishery.test.databinding.LayoutBottomSheetAreaBinding
import com.efishery.test.ui.home.HomeAdapter
import com.efishery.test.ui.home.HomeFragmentDirections
import com.efishery.test.util.navController
import com.efishery.test.util.navigateOrNull
import com.efishery.test.util.viewBinding
import com.efishery.test.viewmodel.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ChooseAreaFragment : BottomSheetDialogFragment() {

    private val binding by viewBinding<LayoutBottomSheetAreaBinding>()
    private val viewModel by hiltNavGraphViewModels<HomeViewModel>(R.id.home)

    private val areaAdapter = HomeAdapter.areaAdapter
    private var area = Area()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return LayoutInflater.from(context).inflate(
            R.layout.layout_bottom_sheet_area,
            container,
            false
        )
    }

    override fun getTheme() = R.style.BottomSheetThemeTheme

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initViewModelCallback()
        initAdapter()
    }

    private fun initViewModel() {
        viewModel.requestProduct()
        viewModel.requestArea()
    }

    private fun initViewModelCallback() {
        initProductCallback()
    }

    private fun initAdapter(){
        initClickAdapter()
    }

    private fun initProductCallback() {
        viewModel.area.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                }
                is Result.Success -> {
                    areaAdapter.items = result.data.orEmpty()
                    Timber.e("test")
                }
                is Result.Error<*> -> {

                }
                else -> {
                }
            }
        }
        binding.rvArea.adapter = areaAdapter
    }

    private fun initClickAdapter() {
        HomeAdapter.SetOnClickItem.setOnClickItemAreaListener { item ->
            area = Area(
                area = item.city
            )
            viewModel.areaSavedState = area
            navController.navigateOrNull(
                ChooseAreaFragmentDirections.actionAreaFragmentToHomeFragment()
            )
            dismiss()
        }
    }

}
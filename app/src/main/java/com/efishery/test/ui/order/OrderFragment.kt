package com.efishery.test.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.navArgs
import com.efishery.test.R
import com.efishery.test.data.local.entity.Order
import com.efishery.test.data.remote.Result
import com.efishery.test.data.remote.model.Area
import com.efishery.test.databinding.LayoutBottomSheetAreaBinding
import com.efishery.test.databinding.LayoutBottomSheetOrderBinding
import com.efishery.test.ui.home.DetailFragmentArgs
import com.efishery.test.ui.home.DetailFragmentDirections
import com.efishery.test.ui.home.HomeAdapter
import com.efishery.test.ui.home.HomeFragmentDirections
import com.efishery.test.util.*
import com.efishery.test.viewmodel.HomeViewModel
import com.efishery.test.viewmodel.OrderViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class OrderFragment : BottomSheetDialogFragment() {

    private val binding by viewBinding<LayoutBottomSheetOrderBinding>()
    private val viewModel by hiltNavGraphViewModels<OrderViewModel>(R.id.home)
    private val args by navArgs<OrderFragmentArgs>()

    private val areaAdapter = HomeAdapter.areaAdapter
    private var area = Area()
    private var sort = emptyString
    private var domisli = emptyString

    private var order: Order = Order()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return LayoutInflater.from(context).inflate(
            R.layout.layout_bottom_sheet_order,
            container,
            false
        )
    }

    override fun getTheme() = R.style.BottomSheetThemeTheme

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()

    }

    private fun initView() {
        initSpinnerSort()
        initSpinnerSortDomisli()
        setData()
    }

    private fun initSpinnerSort() {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sort_pickup,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSort.adapter = adapter

        binding.spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sort = binding.spinnerSort.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun initSpinnerSortDomisli() {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.kota,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSortDomisili.adapter = adapter

        binding.spinnerSortDomisili.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                domisli = binding.spinnerSortDomisili.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setData() {
        binding.etFish.textOrNull = args.nameFish
    }

    private fun initOnClick() {
        binding.apply {
            btnOrder.setOnClickListener(onClickCallback)
        }
    }

    private val onClickCallback = View.OnClickListener { view ->
        when (view) {
            binding.btnOrder -> {
                order = Order(
                    komoditas = binding.etFish.text.toString(),
                    pickup = sort,
                    jumlah_pesanan = binding.etKg.text.toString(),
                    kode_promo = binding.etKodePromo.text.toString(),
                    nama_penerima = binding.etName.text.toString(),
                    hp = binding.etPhone.text.toString(),
                    domisili = domisli
                )
                viewModel.insertLocalOrder(order)
//                dismiss()
            }
        }
    }

}
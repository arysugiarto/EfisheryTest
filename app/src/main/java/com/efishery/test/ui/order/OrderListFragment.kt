package com.efishery.test.ui.order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.efishery.test.R
import com.efishery.test.data.local.entity.Order
import com.efishery.test.databinding.FragmentOrderBinding
import com.efishery.test.ui.home.HomeAdapter
import com.efishery.test.util.viewBinding
import com.efishery.test.viewmodel.OrderViewModel
import timber.log.Timber

class OrderListFragment : Fragment(R.layout.fragment_order) {

    private val binding by viewBinding<FragmentOrderBinding>()
    private val viewModel by hiltNavGraphViewModels<OrderViewModel>(R.id.cart)
    private val orderAdapter = HomeAdapter.orderAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        localDataOrder()
    }

    private fun localDataOrder() {
        var data = emptyList<Order>()
        viewModel.getLocalOrder.observe(viewLifecycleOwner) { result ->
            Timber.e(result.toString())
            data = result.orEmpty()
            orderAdapter.items = data
        }
        binding.rvOrder.adapter = orderAdapter
    }

}
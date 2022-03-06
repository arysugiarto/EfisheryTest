package com.efishery.test.ui.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.efishery.test.R
import com.efishery.test.data.local.entity.Order
import com.efishery.test.data.remote.model.BannerSliderModel
import com.efishery.test.databinding.FragmentFeedBinding
import com.efishery.test.databinding.ItemAreaBinding
import com.efishery.test.ui.home.HomeAdapter
import com.efishery.test.util.orEmpty
import com.efishery.test.util.textOrNull
import com.efishery.test.util.viewBinding
import com.efishery.test.viewmodel.OrderViewModel
import timber.log.Timber

class FeedFragment : Fragment(R.layout.fragment_feed) {

    private val binding by viewBinding<FragmentFeedBinding>()
    private val viewModel by hiltNavGraphViewModels<OrderViewModel>(R.id.cart)
    private val orderAdapter = HomeAdapter.orderAdapter
    private var listOrder = emptyList<Order>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        localDataOrder()


//        binding.rvOrder.adapter = orderAdapter
    }

    private fun localDataOrder() {
        var data = emptyList<Order>()
        viewModel.getLocalOrder.observe(viewLifecycleOwner) { result ->
            Timber.e(result.toString())
            data = result.orEmpty()
            orderAdapter.items = data
        }
        binding.rvOrder.adapter = orderAdapter
//        data.forEach { local ->
//            binding.rvOrder.findViewHolderForAdapterPosition(local.id.orEmpty())
//                ?.apply {
//                    val itemBinding = ItemAreaBinding.bind(itemView)
//                    itemBinding.tvArea.textOrNull = local.jumlah_pesanan
//                }
//        }
    }
}
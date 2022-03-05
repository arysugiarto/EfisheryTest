package com.efishery.test.ui.home

import com.efishery.test.base.BaseAdapter
import com.efishery.test.data.remote.model.ProductResponse
import com.efishery.test.databinding.ItemProductBinding
import com.efishery.test.util.textOrNull

object HomeAdapter {

    val productAdapter =
        BaseAdapter.adapterOf<ProductResponse, ItemProductBinding>(
            register = BaseAdapter.Register(
                onBindHolder = { pos, item, view ->
                    view.run {

                        tvKomoditas.textOrNull = item.komoditas
                        tvPrice.textOrNull = "Rp." + item.price
                        tvSize.textOrNull = item.size

                    }
                }
            ),
            diff = BaseAdapter.Diff(
                areItemsTheSame = { old, new -> old.uuid == new.uuid },
                areContentsTheSame = { old, new -> old == new }
            )
        )


}
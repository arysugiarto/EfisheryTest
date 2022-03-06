package com.efishery.test.ui.home

import android.widget.ImageView
import com.efishery.test.base.BaseAdapter
import com.efishery.test.data.local.entity.Order
import com.efishery.test.data.remote.model.AreaResponse
import com.efishery.test.data.remote.model.BannerSliderModel
import com.efishery.test.data.remote.model.ProductResponse
import com.efishery.test.databinding.ItemAreaBinding
import com.efishery.test.databinding.ItemBannerSliderBinding
import com.efishery.test.databinding.ItemProductBinding
import com.efishery.test.util.ImageCornerOptions
import com.efishery.test.util.loadImage
import com.efishery.test.util.textOrNull

object HomeAdapter {

    val bannerSliderAdapter =
        BaseAdapter.adapterOf<BannerSliderModel, ItemBannerSliderBinding>(
            register = BaseAdapter.Register(
                onBindHolder = { _, item, view ->
                    view.run {
                        ivBannerImage.loadImage(
                            source = item.image,
                            scaleType = ImageView.ScaleType.CENTER_CROP,
                            radius = 8,
                            corner = ImageCornerOptions.ROUNDED
                        )

                    }
                }
            ),
            diff = BaseAdapter.Diff(
                areItemsTheSame = { old, new -> old.id == new.id },
                areContentsTheSame = { old, new -> old == new }
            )
        )

    val productAdapter =
        BaseAdapter.adapterOf<ProductResponse, ItemProductBinding>(
            register = BaseAdapter.Register(
                onBindHolder = { pos, item, view ->
                    view.run {

                        tvKomoditas.textOrNull = item.komoditas
                        tvPrice.textOrNull = "Rp." + item.price + "/kg"
                        tvSize.textOrNull = item.size

                        cvFish.setOnClickListener {
                            SetOnClickItem.onClickListener.invoke(item)
                        }

                    }
                }
            ),
            diff = BaseAdapter.Diff(
                areItemsTheSame = { old, new -> old.uuid == new.uuid },
                areContentsTheSame = { old, new -> old == new }
            )
        )

    val areaAdapter =
        BaseAdapter.adapterOf<AreaResponse, ItemAreaBinding>(
            register = BaseAdapter.Register(
                onBindHolder = { pos, item, view ->
                    view.run {

                        tvArea.textOrNull = item.city

                        tvArea.setOnClickListener {
                            SetOnClickItem.onClickAreaListener.invoke(item)
                        }

                    }
                }
            ),
            diff = BaseAdapter.Diff(
                areItemsTheSame = { old, new -> old.province == new.province },
                areContentsTheSame = { old, new -> old == new }
            )
        )

    val orderAdapter =
        BaseAdapter.adapterOf<Order, ItemAreaBinding>(
            register = BaseAdapter.Register(
                onBindHolder = { pos, item, view ->
                    view.run {
                        tvArea.textOrNull = item.nama_penerima
                    }
                }
            ),
            diff = BaseAdapter.Diff(
                areItemsTheSame = { old, new -> old.id == new.id },
                areContentsTheSame = { old, new -> old == new }
            )
        )

    object SetOnClickItem {
        var onClickListener: (ProductResponse) -> Unit = { _ -> }

        fun setOnClickItemListener(listener: (ProductResponse) -> Unit) {
            onClickListener = listener
        }

        var onClickAreaListener: (AreaResponse) -> Unit = { _ -> }

        fun setOnClickItemAreaListener(listener: (AreaResponse) -> Unit) {
            onClickAreaListener = listener
        }
    }

}
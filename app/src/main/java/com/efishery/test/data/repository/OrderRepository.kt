package com.efishery.test.data.repository

import com.efishery.test.data.local.entity.Order
import com.efishery.test.data.source.OrderLocalDataSource
import com.efishery.test.data.source.callback.HomeSourceCallback
import com.efishery.test.data.source.callback.OrderSourceCallback

class OrderRepository(
    localDataSource: OrderLocalDataSource
) : OrderSourceCallback {
    private val localDataSource = localDataSource

    override suspend fun insertOrderLocal(order: Order) = localDataSource.insertOrder(order)

    override fun getLocalOrder() = localDataSource.getOrder()
}
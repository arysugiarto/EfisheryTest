package com.efishery.test.data.source

import com.efishery.test.data.local.dao.OrderDao
import com.efishery.test.data.local.entity.Order

class OrderLocalDataSource(
    orderDao: OrderDao
) {
    private val daoOrder = orderDao

    suspend fun insertOrder(order: Order) = daoOrder.inserOrder(order)

    fun getOrder() = daoOrder.getOrder()
}
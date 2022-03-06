package com.efishery.test.data.source.callback

import com.efishery.test.data.local.entity.Order
import com.efishery.test.data.remote.Result
import com.efishery.test.data.remote.model.AreaResponse
import com.efishery.test.data.remote.model.ProductResponse
import kotlinx.coroutines.flow.Flow

interface OrderSourceCallback {

    suspend fun insertOrderLocal(order: Order)

    fun getLocalOrder(): Flow<List<Order>>

}
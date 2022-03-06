package com.efishery.test.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.efishery.test.base.BaseViewModel
import com.efishery.test.data.local.entity.Order
import com.efishery.test.data.remote.Result
import com.efishery.test.data.remote.model.Area
import com.efishery.test.data.remote.model.AreaResponse
import com.efishery.test.data.remote.model.ProductResponse
import com.efishery.test.data.repository.HomeRepository
import com.efishery.test.data.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    application: Application,
   orderRepository: OrderRepository
) : BaseViewModel(application) {
    private val repository = orderRepository

    fun insertLocalOrder(order: Order) =
        viewModelScope.launch {
            repository.insertOrderLocal(order)
        }

    val getLocalOrder
        get() = repository
            .getLocalOrder()
            .asLiveData(viewModelScope.coroutineContext)

}
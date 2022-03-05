package com.efishery.test.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.efishery.test.base.BaseViewModel
import com.efishery.test.data.remote.Result
import com.efishery.test.data.remote.model.ProductResponse
import com.efishery.test.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    homeRepository: HomeRepository
) : BaseViewModel(application) {
    private val repository = homeRepository

    private var _product: MutableLiveData<Result<List<ProductResponse>>> = MutableLiveData()
    val product: LiveData<Result<List<ProductResponse>>> get() = _product

    fun requestProduct() =
        repository.requestProduct()
            .onEach { result ->
                _product.value = result
            }.launchIn(viewModelScope)


}
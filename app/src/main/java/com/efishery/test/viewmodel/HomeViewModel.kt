package com.efishery.test.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.efishery.test.base.BaseViewModel
import com.efishery.test.data.remote.Result
import com.efishery.test.data.remote.model.Area
import com.efishery.test.data.remote.model.AreaResponse
import com.efishery.test.data.remote.model.ProductResponse
import com.efishery.test.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    homeRepository: HomeRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(application) {
    private val repository = homeRepository
    private val stateHandler = savedStateHandle

    private var _product: MutableLiveData<Result<List<ProductResponse>>> = MutableLiveData()
    val product: LiveData<Result<List<ProductResponse>>> get() = _product

    private var _area: MutableLiveData<Result<List<AreaResponse>>> = MutableLiveData()
    val area: LiveData<Result<List<AreaResponse>>> get() = _area

    var areaSavedState: Area
        get() = stateHandler[AREA] ?: Area()
        set(value) = stateHandler.set(AREA, value)

    fun requestProduct() =
        repository.requestProduct()
            .onEach { result ->
                _product.value = result
            }.launchIn(viewModelScope)

    fun requestArea() =
        repository.requestArea()
            .onEach { result ->
                _area.value = result
            }.launchIn(viewModelScope)

    companion object {
        const val AREA = "area_state_key"
    }

}
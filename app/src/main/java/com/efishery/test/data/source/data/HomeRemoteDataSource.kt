package com.efishery.test.data.source.data

import com.efishery.test.data.remote.api.ApiCallback
import com.efishery.test.util.flowResponse


class HomeRemoteDataSource(callback: ApiCallback) {
    private val apiCallback = callback


    fun requestProduct() = flowResponse { apiCallback.requestProduct() }
}
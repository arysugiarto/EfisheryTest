package com.efishery.test.data.repository

import com.efishery.test.data.source.callback.HomeSourceCallback
import com.efishery.test.data.source.data.HomeRemoteDataSource

class HomeRepository(
    homeRemoteDataSource: HomeRemoteDataSource
) : HomeSourceCallback {
    private val remoteDataSource = homeRemoteDataSource

    override fun requestProduct() = remoteDataSource.requestProduct()

}
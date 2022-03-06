package com.efishery.test.data.source.callback

import com.efishery.test.data.remote.Result
import com.efishery.test.data.remote.model.AreaResponse
import com.efishery.test.data.remote.model.ProductResponse
import kotlinx.coroutines.flow.Flow

interface HomeSourceCallback {

    fun requestProduct(): Flow<Result<List<ProductResponse>>>

    fun requestArea(): Flow<Result<List<AreaResponse>>>

}
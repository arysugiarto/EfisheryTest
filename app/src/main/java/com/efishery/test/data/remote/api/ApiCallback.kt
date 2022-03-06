package com.efishery.test.data.remote.api

import com.efishery.test.data.remote.model.AreaResponse
import com.efishery.test.data.remote.model.ProductResponse
import com.efishery.test.util.Const
import retrofit2.Response
import retrofit2.http.GET

interface ApiCallback {
    @GET(Const.Network.Home.PRODUCT)
    suspend fun requestProduct(
    ): Response<List<ProductResponse>>

    @GET(Const.Network.Home.AREA)
    suspend fun requestArea(
    ): Response<List<AreaResponse>>
}
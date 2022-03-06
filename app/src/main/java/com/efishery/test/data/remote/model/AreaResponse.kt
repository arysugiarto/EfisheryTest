package com.efishery.test.data.remote.model


import com.google.gson.annotations.SerializedName

class AreaResponse(
    @SerializedName("province")
    var province: String,
    @SerializedName("city")
    var city: String
)

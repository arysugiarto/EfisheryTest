package com.efishery.test.data.remote.model


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("uuid")
    var uuid: String,
    @SerializedName("komoditas")
    var komoditas: String,
    @SerializedName("area_provinsi")
    var areaProvinsi: String,
    @SerializedName("area_kota")
    var areaKota: String,
    @SerializedName("size")
    var size: String,
    @SerializedName("price")
    var price: String,
    @SerializedName("tgl_parsed")
    var tglParsed: String,
    @SerializedName("timestamp")
    var timestamp: String

)


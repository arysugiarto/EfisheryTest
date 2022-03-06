package com.efishery.test.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.efishery.test.util.Const

@Entity(tableName = Const.Database.Table.ORDER)
data class Order(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    var komoditas: String? = null,
    var pickup: String? = null,
    var jumlah_pesanan: String? = null,
    var kode_promo: String? = null,
    var nama_penerima: String? = null,
    var hp: String? = null,
    var domisili: String? = null
)

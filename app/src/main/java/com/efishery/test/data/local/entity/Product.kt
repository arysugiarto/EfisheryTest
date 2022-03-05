package com.efishery.test.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.efishery.test.util.Const

@Entity(tableName = Const.Database.Table.PRODUCT)
data class Product(
    @PrimaryKey
    var id: Long
)

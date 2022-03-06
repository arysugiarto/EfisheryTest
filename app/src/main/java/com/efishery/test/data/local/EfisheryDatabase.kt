package com.efishery.test.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.efishery.test.data.local.dao.OrderDao
import com.efishery.test.data.local.entity.Order

@Database(
    entities = [Order::class],
    version = 2,
    exportSchema = false
)
abstract class EfisheryDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao

}
package com.efishery.test.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.efishery.test.data.local.dao.ProductDao
import com.efishery.test.data.local.entity.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
abstract class EfisheryDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    // Todo Add More Dao
}
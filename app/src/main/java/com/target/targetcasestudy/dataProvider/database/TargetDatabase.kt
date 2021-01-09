package com.target.targetcasestudy.dataProvider.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.target.targetcasestudy.dataModel.Product
import com.target.targetcasestudy.dataProvider.database.DatabaseConstants.DATABASE_VERSION


@Database(entities = [Product::class],version=DATABASE_VERSION,exportSchema = false)
@TypeConverters(DealListTypeConverter::class)
abstract class TargetDatabase:RoomDatabase() {
      abstract fun getDealDataDao():DealDataDao
}
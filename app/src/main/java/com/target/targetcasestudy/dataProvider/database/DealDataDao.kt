package com.target.targetcasestudy.dataProvider.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.target.targetcasestudy.dataModel.Product
import com.target.targetcasestudy.dataProvider.database.DatabaseConstants.DEAL_TABLE_NAME

@Dao
interface DealDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDealListIntoDataBase(dealList:List<Product>)

    @Query("SELECT * FROM $DEAL_TABLE_NAME")
    fun getDealsList():List<Product>?

    @Query("DELETE FROM $DEAL_TABLE_NAME")
    fun clearAllDeals()
}
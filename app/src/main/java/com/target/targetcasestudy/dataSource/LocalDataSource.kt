package com.target.targetcasestudy.dataSource

import com.target.targetcasestudy.dataModel.Product
import com.target.targetcasestudy.dataProvider.database.TargetDatabase
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class LocalDataSource @Inject constructor(private val database:TargetDatabase) {

    /**
     * fetch deals from the deals database
     */
    fun getDealsList(): List<Product>?{
        return database.getDealDataDao().getDealsList()
    }
}
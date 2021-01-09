package com.target.targetcasestudy.dataProvider.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.target.targetcasestudy.dataModel.Product

object DealListTypeConverter {

    private val gSon by lazy { Gson() }
    @TypeConverter
    fun dealListToStringObject(dealList: List<Product>):String{
        return gSon.toJson(dealList)
    }

    @TypeConverter
    fun stringObjectToDeaList(profile:String): List<Product> {
        val typeToken = object: TypeToken<List<Product>>(){}.type
        return gSon.fromJson(profile,typeToken)
    }
}
package com.target.targetcasestudy.dataProvider.status

import com.target.targetcasestudy.dataModel.Product


sealed class Status {
    object NoInternet:Status()
    data class Error(val errorMessage:String?):Status()
    data class Response(val dealList:List<Product>?):Status()
}
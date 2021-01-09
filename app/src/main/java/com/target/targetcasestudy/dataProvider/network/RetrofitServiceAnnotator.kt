package com.target.targetcasestudy.dataProvider.network

import com.target.targetcasestudy.dataModel.DealsData
import com.target.targetcasestudy.dataProvider.network.RequestUrl.DEAL_LISTING_API
import retrofit2.Response
import retrofit2.http.GET


interface RetrofitServiceAnnotator {

    @GET(DEAL_LISTING_API)
    suspend fun getDeals():Response<DealsData>

}
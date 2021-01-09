package com.target.targetcasestudy.dataSource

import androidx.room.withTransaction
import com.target.targetcasestudy.dataProvider.database.TargetDatabase
import com.target.targetcasestudy.dataProvider.network.RetrofitServiceAnnotator
import com.target.targetcasestudy.dataProvider.status.Status
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


@ExperimentalCoroutinesApi
@ActivityScoped
class RemoteDataSource @Inject constructor(private val retrofitServiceAnnotator: RetrofitServiceAnnotator,private val database: TargetDatabase)  {

    /**
     * fetch data from remote using retrofit service annotator and emit the data to consumer using flow builder emit
     * method
     */
    suspend fun getDealsList(): Flow<Status> {
        return flow {
             try {
                  val response = retrofitServiceAnnotator.getDeals()
                  if (response.isSuccessful){
                      response.body()?.products?.let {
                          emit(Status.Response(it))
                          database.apply {
                              withTransaction {
                                  getDealDataDao().apply {
                                      clearAllDeals()
                                      insertDealListIntoDataBase(it)
                                  }
                              }
                          }
                      }?: kotlin.run {
                              emit(Status.Error(null))
                      }
                  }
                  else{
                      emit(Status.Error(null))
                  }
             }
             catch (e:Exception){
                emit(Status.Error(e.message))
             }
        }
    }
}
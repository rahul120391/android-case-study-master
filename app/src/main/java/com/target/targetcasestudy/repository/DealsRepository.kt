package com.target.targetcasestudy.repository

import com.target.targetcasestudy.dataProvider.connectivity.NetworkConnectivityListenerImp
import com.target.targetcasestudy.dataProvider.status.Status
import com.target.targetcasestudy.dataSource.LocalDataSource
import com.target.targetcasestudy.dataSource.RemoteDataSource
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@ExperimentalCoroutinesApi
@ActivityScoped
class DealsRepository @Inject constructor(private val networkConnectivityListenerImp: NetworkConnectivityListenerImp,
                 private val localDataSource: LocalDataSource,
                 private val remoteDataSource: RemoteDataSource) {


    /**
     * Fetch data on the basis of isRefreshingFlag, if true fetch from remote else first check the local data source
     * if not found fetch from remote
     */
    suspend fun getDealsList(isRefreshing:Boolean): Flow<Status> {
        if(isRefreshing){
            return fetchFromRemote()
        }
        else{
            localDataSource.getDealsList()?.let {
                return if(it.isNotEmpty()){
                    flow {
                        emit(Status.Response(it))
                    }
                } else{
                    return fetchFromRemote()
                }
            }?: kotlin.run {
                return fetchFromRemote()
            }
        }
    }

    /**
     * Fetch Data From Remote
     */
    private suspend fun fetchFromRemote():Flow<Status>{
        return if(networkConnectivityListenerImp.isAvailable){
            remoteDataSource.getDealsList()
        } else{
            flow {
                emit(Status.NoInternet)
            }
        }
    }

}
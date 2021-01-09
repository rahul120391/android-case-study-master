package com.target.targetcasestudy.domain

import com.target.targetcasestudy.base.BaseUseCase
import com.target.targetcasestudy.dataProvider.status.Status
import com.target.targetcasestudy.repository.DealsRepository
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ActivityScoped
class DealsUseCase @Inject constructor(private val repository: DealsRepository):BaseUseCase() {


    /**
     * fetch data from deals repository
     */
    override suspend fun executeUseCase(isRefreshing:Boolean): Flow<Status> {
        return flow {
             repository.getDealsList(isRefreshing).collect {
                 emit(it)
             }
        }.flowOn(Dispatchers.Default)
    }

}
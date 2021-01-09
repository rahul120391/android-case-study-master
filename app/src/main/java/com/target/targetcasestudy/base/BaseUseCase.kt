package com.target.targetcasestudy.base

import com.target.targetcasestudy.dataProvider.status.Status
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase {
    abstract suspend fun executeUseCase(isRefreshing:Boolean):Flow<Status>
}
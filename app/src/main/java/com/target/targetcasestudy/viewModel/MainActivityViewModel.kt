package com.target.targetcasestudy.viewModel

import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.dataProvider.status.Status
import com.target.targetcasestudy.domain.DealsUseCase
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ExperimentalCoroutinesApi
@ActivityScoped
class MainActivityViewModel @Inject constructor(private val dealsUseCase: DealsUseCase):ViewModel() {



    suspend fun getDealsData(isRefreshing:Boolean): Flow<Status> = dealsUseCase.executeUseCase(isRefreshing)

}
package com.target.targetcasestudy.view.di

import android.content.Context
import com.target.targetcasestudy.dataProvider.connectivity.NetworkConnectivityListenerImp
import com.target.targetcasestudy.domain.DealsUseCase
import com.target.targetcasestudy.viewModel.MainActivityViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {

    @ActivityScoped
    @Provides
    fun provideNetworkImplementation(@ActivityContext context: Context): NetworkConnectivityListenerImp = NetworkConnectivityListenerImp(context)

    @Provides
    @ActivityScoped
    fun provideMainActivityViewModel(useCase: DealsUseCase):MainActivityViewModel = MainActivityViewModel(dealsUseCase = useCase)
}
package com.target.targetcasestudy.di

import android.content.Context
import androidx.room.Room
import com.target.targetcasestudy.dataProvider.database.DatabaseConstants.DATABASE_NAME
import com.target.targetcasestudy.dataProvider.database.TargetDatabase
import com.target.targetcasestudy.dataProvider.network.RetrofitClient
import com.target.targetcasestudy.dataProvider.network.RetrofitServiceAnnotator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Provides
    @Singleton
    internal fun provideDatabase(@ApplicationContext context: Context): TargetDatabase =
        Room.databaseBuilder(context,TargetDatabase::class.java,DATABASE_NAME)
        .build()

    @Provides
    @Singleton
    internal fun provideRetrofitServiceAnnotator():RetrofitServiceAnnotator = RetrofitClient.createRetrofitService()
}
package com.daffa.core.di

import android.content.Context
import androidx.room.Room
import com.daffa.core.data.ProductRepositoryImpl
import com.daffa.core.data.source.local.LocalDataSource
import com.daffa.core.data.source.local.room.ProductDao
import com.daffa.core.data.source.local.room.ProductDatabase
import com.daffa.core.data.source.remote.RemoteDataSource
import com.daffa.core.data.source.remote.network.ApiService
import com.daffa.core.domain.repository.ProductRepository
import com.daffa.core.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            Constant.PRODUCT_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductDao(
        db: ProductDatabase
    ): ProductDao {
        return db.productDao()
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): ProductRepository {
        return ProductRepositoryImpl(
            remoteDataSource,
            localDataSource
        )
    }
}
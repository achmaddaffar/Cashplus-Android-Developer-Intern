package com.daffa.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.daffa.core.data.source.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAllCartItems(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE id = :id")
    fun getCartItem(id: Int): Flow<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItems(products: List<ProductEntity>)

    @Query("DELETE FROM product WHERE id = :id")
    suspend fun deleteCartItem(id: Int)

    @Query("SELECT * FROM product WHERE cartCount > 0")
    fun getCartResult(): Flow<List<ProductEntity>>

    @Query("DELETE FROM product")
    fun deleteTable()

    @Update
    fun updateProduct(product: ProductEntity)
}
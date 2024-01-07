package com.daffa.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daffa.core.data.mapper.ProductMapper
import com.daffa.core.data.source.local.LocalDataSource
import com.daffa.core.data.source.remote.RemoteDataSource
import com.daffa.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProductRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var localDataSource: LocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var repository: ProductRepository

    @Before
    fun setup() {
        localDataSource = LocalDataSource(FakeProductDao())
        remoteDataSource = RemoteDataSource(FakeApiService())
        repository = ProductRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `when getAllProducts, should Not Null and should equal`() = runTest {
        val expectedProducts = DataDummy.generateDummyProductResponse()
        val actualProducts = repository.getAllProducts().first { it.data != null }
        assertNotNull(actualProducts)
        assertEquals(
            ProductMapper.mapEntitiesToDomain(
                ProductMapper.mapResponsesToEntity(expectedProducts)
            ),
            actualProducts.data
        )
    }

    @Test
    fun `when addCartItem, cartCount increased by 1`() = runTest {
        val listOfProduct = repository.getAllProducts().first { it.data != null }.data
        repository.addCartItem(listOfProduct?.get(6)!!)
        val cartCount = repository.getCartItem(6).first().cartCount
        assertEquals(1, cartCount)
    }
}
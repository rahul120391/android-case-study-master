package com.target.targetcasestudy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.target.targetcasestudy.DummyTestData.DUMMY_DEAL_AISLE
import com.target.targetcasestudy.DummyTestData.DUMMY_DEAL_DESCRIPTION
import com.target.targetcasestudy.DummyTestData.DUMMY_DEAL_ID
import com.target.targetcasestudy.DummyTestData.DUMMY_DEAL_IMAGE_URL
import com.target.targetcasestudy.DummyTestData.DUMMY_DEAL_TITLE
import com.target.targetcasestudy.DummyTestData.DUMMY_ERROR
import com.target.targetcasestudy.dataModel.Product
import com.target.targetcasestudy.dataModel.RegularPrice
import com.target.targetcasestudy.dataModel.SalePrice
import com.target.targetcasestudy.dataProvider.status.Status
import com.target.targetcasestudy.domain.DealsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var dealUseCase: DealsUseCase

    @Before
    fun loadBefore(){
         MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testForSuccessResponse(){
        testCoroutineRule.runBlockingTest {
            val dummyPositiveResultFlow = flow<Status> {
                val regularPrice = mock(RegularPrice::class.java)
                val salesPrice = mock(SalePrice::class.java)
                val product = Product(id = DUMMY_DEAL_ID,
                    aisle = DUMMY_DEAL_AISLE,
                    description = DUMMY_DEAL_DESCRIPTION,
                    image_url = DUMMY_DEAL_IMAGE_URL,
                    title = DUMMY_DEAL_TITLE,
                regular_price = regularPrice,
                sale_price = salesPrice)
                val list = listOf(product)
                emit(Status.Response(list))
            }
            Mockito.lenient().`when`(dealUseCase.executeUseCase(false)).thenReturn(dummyPositiveResultFlow)
            var result = listOf<Product>()
            dealUseCase.executeUseCase(false).collectLatest {
                result = (it as Status.Response).dealList?: emptyList()
            }
            Assert.assertTrue(result.isNotEmpty())
         }
    }

    @Test
    fun testForNoInternetResponse(){
        testCoroutineRule.runBlockingTest {
            val noInternetTestFlow = flow<Status> {
                emit(Status.NoInternet)
            }
            Mockito.lenient().`when`(dealUseCase.executeUseCase(false)).thenReturn(noInternetTestFlow)
            val expectedResult = Status.NoInternet
            dealUseCase.executeUseCase(false).collectLatest {
                Assert.assertTrue(it==expectedResult)
            }
        }
    }

    @Test
    fun testForErrorResponse(){
        testCoroutineRule.runBlockingTest {
            val errorResultFlow = flow {
                emit(Status.Error(DUMMY_ERROR))
            }
            Mockito.lenient().`when`(dealUseCase.executeUseCase(false)).thenReturn(errorResultFlow)
            val expectedResult = Status.Error(DUMMY_ERROR)
            dealUseCase.executeUseCase(false).collectLatest {
                Assert.assertTrue(it==expectedResult)
            }
        }
    }



}
package com.example.mytestapp.presentation.screen

import com.example.mytestapp.domain.model.Product
import com.example.mytestapp.domain.repository.ProductRepository
import com.example.mytestapp.domain.usecase.GetProductsUseCase
import com.example.mytestapp.presentation.UiState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description


@OptIn(ExperimentalCoroutinesApi::class)
class ProductViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: ProductRepository

    private lateinit var useCase: GetProductsUseCase

    private lateinit var viewModel: ProductViewModel

    @Before
    fun setup() {
        repository = mockk()
        useCase =
            GetProductsUseCase(repository)
    }

    @Test
    fun `when api returns products then emit Success state`() =
        runTest {

            // Given
            val products = listOf(
                Product(
                    id = "1",
                    title = "iPhone",
                    price = "999"
                )
            )

            coEvery {
                repository.getProductList()
            } returns products

            // When
            viewModel =
                ProductViewModel(useCase)

            advanceUntilIdle()

            // Then
            val state = viewModel.sate.value

            assertTrue(
                state is UiState.Success
            )

            val data =
                (state as UiState.Success<List<Product>>).data

            assertEquals(
                1,
                data.size
            )

            assertEquals(
                "iPhone1",
                data.first().title
            )
        }

    @Test
    fun `when api returns empty list then emit Empty state`() =
        runTest {

            // Given
            coEvery {
                repository.getProductList()
            } returns emptyList()

            // When
            viewModel =
                ProductViewModel(useCase)

            advanceUntilIdle()

            // Then
            assertEquals(
                UiState.Empty,
                viewModel.sate.value
            )
        }

    @Test
    fun `when api throws exception then emit Error state`() =
        runTest {

            // Given
            coEvery {
                repository.getProductList()
            } throws RuntimeException("Network Error")

            // When
            viewModel =
                ProductViewModel(useCase)

            advanceUntilIdle()

            // Then
            val state =
                viewModel.sate.value

            assertTrue(
                state is UiState.Error
            )

            assertEquals(
                "Network Error",
                (state as UiState.Error).errorMessage
            )
        }
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val dispatcher: TestDispatcher =
        UnconfinedTestDispatcher()
) : TestWatcher() {

    override fun starting(description: Description) {
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
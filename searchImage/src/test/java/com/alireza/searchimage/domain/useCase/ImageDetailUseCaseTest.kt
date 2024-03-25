package com.alireza.searchimage.domain.useCase

import com.alireza.searchimage.data.fakeData.fakeImageEntityList
import com.alireza.searchimage.data.local.mapper.toImageDetail
import com.alireza.searchimage.domain.repository.SearchImageRepository
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ImageDetailUseCaseTest {

    private val testScope = TestScope(StandardTestDispatcher())

    private lateinit var useCase: ImageDetailUseCase
    @Mock
    private lateinit var repository: SearchImageRepository

    @Before
    fun setUp() {
        useCase = ImageDetailUseCase(repository)
    }

    @Test
    fun `test use case invocation`() = testScope.runTest {
        val imageId = 1
        val expectedImageDetail = fakeImageEntityList.map { it.toImageDetail() }[0]

        whenever(repository.imageDetails(imageId)).thenReturn(expectedImageDetail)

        val result = useCase(imageId)

        assertEquals(expectedImageDetail, result)
    }


    @Test(expected = RuntimeException::class)
    fun `test use case with repository throwing exception`() = testScope.runTest {
        val imageId = 1

            whenever(repository.imageDetails(imageId)).thenThrow(RuntimeException("Error fetching image detail"))

        useCase(imageId)

    }

}

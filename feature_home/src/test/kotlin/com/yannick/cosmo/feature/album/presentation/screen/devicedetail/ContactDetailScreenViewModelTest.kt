package com.yannick.cosmo.feature.album.presentation.screen.devicedetail

import app.cash.turbine.test
import com.yannick.cosmo.feature.album.data.repository.DevicesRepositoryImpl
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ContactDetailScreenViewModelTest {

    private lateinit var sut: ContactDetailScreenViewModel
    private val deviceRepository: DevicesRepositoryImpl = mockk(relaxed = true, relaxUnitFun = true)

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        sut = ContactDetailScreenViewModel(deviceRepository)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun onEnter() = runTest {
        val id = 1
        sut.onEnter(id)
        sut.viewState.test {
            val state = awaitItem()
            //do checks on your state here, don't have time to check everything
            cancelAndConsumeRemainingEvents()
        }
        coVerify(exactly = 1) { deviceRepository.getDeviceDetail(id) }
    }
}
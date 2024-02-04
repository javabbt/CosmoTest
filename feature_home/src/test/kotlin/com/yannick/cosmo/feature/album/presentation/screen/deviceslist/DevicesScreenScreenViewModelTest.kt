package com.yannick.cosmo.feature.album.presentation.screen.deviceslist

import app.cash.turbine.test
import com.yannick.cosmo.feature.album.data.repository.DevicesRepositoryImpl
import com.yannick.cosmo.feature.album.presentation.screen.devicedetail.ContactDetailScreenViewModel
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DevicesScreenScreenViewModelTest {
    private lateinit var sut: DevicesScreenScreenViewModel
    private val deviceRepository: DevicesRepositoryImpl = mockk(relaxed = true, relaxUnitFun = true)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        sut = DevicesScreenScreenViewModel(deviceRepository)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getDevices() = runTest {
        sut.uiState.test {
            val state = awaitItem()
            //do checks on your state here, don't have enough time to check everything
            cancelAndConsumeRemainingEvents()
        }
        sut.getDevices()
        coVerify() { deviceRepository.getDevices() }
    }
}
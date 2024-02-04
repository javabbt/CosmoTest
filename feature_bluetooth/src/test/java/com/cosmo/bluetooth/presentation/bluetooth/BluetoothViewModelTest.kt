package com.cosmo.bluetooth.presentation.bluetooth


import com.cosmo.bluetooth.domain.bluetooth.BluetoothController
import com.cosmo.bluetooth.domain.bluetooth.BluetoothDevice
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BluetoothViewModelTest {

    private lateinit var sut: BluetoothViewModel
    private val bluetoothController: BluetoothController = mockk(relaxed = true, relaxUnitFun = true)

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        sut = BluetoothViewModel(
            bluetoothController
        )
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN connect to device method is called, THEN verify that the correct methods are called`() = runTest{
        val device = BluetoothDevice("test" ,"test")
        sut.connectToDevice(device)
        verify { bluetoothController.connectToDevice(device) }
    }

    @Test
    fun disconnectFromDevice() {
        sut.disconnectFromDevice()
        verify {
            bluetoothController.closeConnection()
        }
    }


    @Test
    fun startScan() {
        sut.startScan()
        verify {
            bluetoothController.startDiscovery()
        }
    }

    @Test
    fun stopScan() {
        sut.stopScan()
        verify {
            bluetoothController.stopDiscovery()
        }
    }
}
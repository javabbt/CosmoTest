package com.cosmo.bluetooth.data.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.cosmo.bluetooth.domain.bluetooth.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain(): BluetoothDeviceDomain {
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}
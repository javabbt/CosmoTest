package com.cosmo.bluetooth.presentation.bluetooth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.yannick.cosmo.feature.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BluetoothScreen(
    navController: NavHostController,
    state: BluetoothUiState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onDeviceClick: (com.cosmo.bluetooth.domain.bluetooth.BluetoothDevice) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.bluetooth),
                        fontSize = 18.sp,
                        maxLines = 1,
                        color = Color.Black,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                        )
                    }
                }
            )
        },
    ) { p ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(p)
        ) {
            BluetoothDeviceList(
                pairedDevices = state.pairedDevices,
                scannedDevices = state.scannedDevices,
                onClick = onDeviceClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = onStartScan) {
                    Text(text = stringResource(R.string.start_scan))
                }
                Button(onClick = onStopScan) {
                    Text(text = stringResource(R.string.stop_scan))
                }
            }
            if (state.isConnecting) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Text(text = stringResource(R.string.connecting))
                }
            }
        }
    }

}

@Composable
fun BluetoothDeviceList(
    pairedDevices: List<com.cosmo.bluetooth.domain.bluetooth.BluetoothDevice>,
    scannedDevices: List<com.cosmo.bluetooth.domain.bluetooth.BluetoothDevice>,
    onClick: (com.cosmo.bluetooth.domain.bluetooth.BluetoothDevice) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Text(
                text = stringResource(R.string.paired_devices),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(pairedDevices.size) { i ->
            val device = pairedDevices[i]
            Text(
                text = device.name ?: stringResource(R.string.no_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(device) }
                    .padding(16.dp),
                color = if (device.isConnected) Color.Blue else Color.Black
            )

            if (device.isConnected) {
                Text(
                    text = stringResource(
                        R.string.infos_of_connected_device_addres,
                        device.address
                    ),
                    modifier = Modifier
                        .offset(y = (-10).dp)
                        .padding(start = 25.dp),
                    color = Color.Blue,
                    fontSize = 13.sp
                )
            }
        }

        item {
            Text(
                text = stringResource(R.string.scanned_devices),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(scannedDevices.size) { i ->
            val device = scannedDevices[i]
            Text(
                text = device.name ?: stringResource(R.string.no_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(device) }
                    .padding(16.dp)
            )
        }
    }
}
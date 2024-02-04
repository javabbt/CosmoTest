package com.yannick.cosmo.feature.album.presentation.screen.deviceslist

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.yannick.cosmo.base.presentation.compose.BluetoothScreen
import com.yannick.cosmo.base.presentation.compose.DeviceDetailScreen
import com.yannick.cosmo.base.presentation.compose.composable.TextTitleMedium
import com.yannick.cosmo.feature.home.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevicesListScreen(
    viewModel: DevicesScreenScreenViewModel,
    navController: NavHostController,
) {
    val uiState by viewModel.uiState.collectAsState()
    val devices = uiState.devices
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        viewModel.sideEffects.onEach { effects ->
            when (effects) {
                is SideEffects.DisplayError -> {
                    Toast.makeText(context, effects.error, Toast.LENGTH_SHORT).show()
                }

                is SideEffects.DisplayGenericError -> {
                    Toast.makeText(context, context.getString(effects.error), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }.collect()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.home),
                        fontSize = 18.sp,
                        maxLines = 1,
                        color = Color.Black,
                    )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(BluetoothScreen)
                },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.bluetooth),
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            if (uiState.loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            } else {
                if (devices.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        TextTitleMedium(
                            text = stringResource(id = R.string.nothing_found),
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(
                    devices.size,
                ) { index ->
                    val device = devices[index]
                    DeviceItem(
                        deviceUiModel = device,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        navController.navigate("{${DeviceDetailScreen}}/${device.id}")
                    }
                }
            }
        }
    }
}

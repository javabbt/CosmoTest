package com.yannick.cosmo.app.presentation

import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cosmo.bluetooth.presentation.bluetooth.BluetoothScreen
import com.cosmo.bluetooth.presentation.bluetooth.BluetoothViewModel
import com.yannick.cosmo.base.presentation.compose.BluetoothScreen
import com.yannick.cosmo.base.presentation.compose.DeviceDetailScreen
import com.yannick.cosmo.feature.album.presentation.screen.devicedetail.ContactDetailScreenViewModel
import com.yannick.cosmo.feature.album.presentation.screen.devicedetail.DeviceDetailedScreen
import com.yannick.cosmo.feature.album.presentation.screen.deviceslist.DevicesListScreen
import com.yannick.cosmo.feature.album.presentation.screen.deviceslist.DevicesScreenScreenViewModel
import com.yannick.cosmo.feature.home.R
import org.koin.androidx.compose.get

@Composable
fun DevicesContainer() {
    val navController = rememberNavController()
    val viewModel = get<DevicesScreenScreenViewModel>()
    DevicesContainerNavGraph(navController, viewModel)
}

@Composable
fun DevicesContainerNavGraph(
    navController: NavHostController,
    viewModel: DevicesScreenScreenViewModel
) {
    return NavHost(
        navController = navController,
        startDestination = com.yannick.cosmo.base.presentation.compose.DevicesListScreen,
    ) {
        composable(
            route = com.yannick.cosmo.base.presentation.compose.DevicesListScreen,
            enterTransition = { slideInHorizontally(animationSpec = tween(500)) },
            exitTransition = { slideOutHorizontally(animationSpec = tween(500)) },
        ) {
            DevicesContainer(
                navController,
                viewModel,
            )
        }
        composable(
            route = "{$DeviceDetailScreen}/{id}",
            enterTransition = { slideInHorizontally(animationSpec = tween(500)) },
            exitTransition = { slideOutHorizontally(animationSpec = tween(500)) },
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
            ),
        ) { backStackEntry ->
            DeviceDetailContainer(navController, backStackEntry.arguments?.getInt("id"))
        }
        composable(
            route = BluetoothScreen,
            enterTransition = { slideInHorizontally(animationSpec = tween(500)) },
            exitTransition = { slideOutHorizontally(animationSpec = tween(500)) },
        ) { backStackEntry ->
            BluetoothScreenContainer(navController)
        }
    }
}

@Composable
fun DevicesContainer(
    navController: NavHostController,
    viewModel: DevicesScreenScreenViewModel,
) {
    DevicesListScreen(
        viewModel = viewModel,
        navController = navController,
    )
}

@Composable
fun DeviceDetailContainer(
    navController: NavHostController,
    id: Int?,
) {
    val viewModel = get<ContactDetailScreenViewModel>()
    id?.let {
        viewModel.onEnter(it)
    }
    DeviceDetailedScreen(viewModel, navController)
}

@Composable
fun BluetoothScreenContainer(
    navController: NavHostController,
) {
    val viewModel = get<BluetoothViewModel>()
    val uiState by viewModel.state.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(key1 = uiState.errorMessage) {
        uiState.errorMessage?.let { message ->
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(key1 = uiState.isConnected) {
        if(uiState.isConnected) {
            Toast.makeText(
                context,
                context.getString(com.yannick.cosmo.feature.app.R.string.you_connected),
                Toast.LENGTH_LONG
            ).show()
        }
    }


    BluetoothScreen(
        navController,
        uiState,
        { viewModel.startScan() },
        { viewModel.stopScan() },
        viewModel::connectToDevice
    )
}



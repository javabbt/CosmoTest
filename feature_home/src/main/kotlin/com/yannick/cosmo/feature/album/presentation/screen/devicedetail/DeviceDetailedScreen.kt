package com.yannick.cosmo.feature.album.presentation.screen.devicedetail

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.yannick.cosmo.base.presentation.compose.composable.TextTitleMedium
import com.yannick.cosmo.feature.home.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceDetailedScreen(
    viewModel: ContactDetailScreenViewModel,
    navController: NavHostController,
) {
    val viewState = viewModel.viewState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(viewModel) {
        viewModel.sideEffects.onEach { effects ->
            when (effects) {
                is SideEffects.DisplayError -> {
                    Toast.makeText(context, effects.error, Toast.LENGTH_SHORT).show()
                }
                is SideEffects.DisplayGenericError -> {
                    Toast.makeText(context, context.getString(effects.error), Toast.LENGTH_SHORT).show()
                }
            }
        }.collect()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.details),
                        fontSize = 18.sp,
                        maxLines = 1,
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
                },
            )
        },
    ) { padding ->
        viewState.value.deviceUiModel?.let { deviceUiModel ->
            DeviceItemDetail(
                deviceUiModel = deviceUiModel,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

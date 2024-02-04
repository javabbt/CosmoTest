package com.yannick.cosmo.feature.album.presentation.screen.deviceslist

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yannick.cosmo.feature.album.domain.model.DeviceUiModel
import com.yannick.cosmo.feature.album.domain.repository.DevicesRepository
import com.yannick.cosmo.feature.album.domain.utils.Result
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DevicesScreenScreenViewModel(
    private val devicesRepository: DevicesRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ViewState())
    val uiState: StateFlow<ViewState> get() = _uiState.asStateFlow()

    private val _sideEffects = MutableSharedFlow<SideEffects>()
    val sideEffects: SharedFlow<SideEffects> get() = _sideEffects.asSharedFlow()

    init {
        getDevices()
    }
    fun getDevices() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(loading = true)
            }
            when (val devices = devicesRepository.getDevices()) {
                is Result.Success -> {
                    _uiState.update {
                        it.copy(
                            devices = devices.output
                        )
                    }
                }
                is Result.Error -> sendSideEffect(SideEffects.DisplayError(devices.exception))
                is Result.UnexpectedError -> sendSideEffect(SideEffects.DisplayGenericError(devices.exception))
            }
            _uiState.update {
                it.copy(loading = false)
            }
        }
    }

    private fun sendSideEffect(sideEffect: SideEffects) {
        viewModelScope.launch {
            _sideEffects.emit(sideEffect)
        }
    }
}

sealed class SideEffects() {
    data class DisplayError(val error: String): SideEffects()
    data class DisplayGenericError(@StringRes val error: Int): SideEffects()
}

data class ViewState(
    val devices: List<DeviceUiModel> = emptyList(),
    val loading: Boolean = false,
)

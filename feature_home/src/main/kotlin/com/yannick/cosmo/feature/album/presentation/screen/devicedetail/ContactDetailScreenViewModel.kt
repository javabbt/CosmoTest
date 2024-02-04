package com.yannick.cosmo.feature.album.presentation.screen.devicedetail

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

class ContactDetailScreenViewModel(
    private val devicesRepository: DevicesRepository,
) : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState())
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    private val _sideEffects = MutableSharedFlow<SideEffects>()
    val sideEffects: SharedFlow<SideEffects> get() = _sideEffects.asSharedFlow()

    fun onEnter(id: Int) {
        viewModelScope.launch {
            _viewState.update {
                it.copy(loading = true)
            }
            when(val device = devicesRepository.getDeviceDetail(id)) {
                is Result.Success -> {
                    _viewState.update {
                        it.copy(
                            deviceUiModel = device.output,
                        )
                    }
                }
                is Result.Error -> sendSideEffect(SideEffects.DisplayError(device.exception))
                is Result.UnexpectedError -> sendSideEffect(SideEffects.DisplayGenericError(device.exception))
                else -> {}
            }
            _viewState.update {
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
    val loading: Boolean = false,
    val deviceUiModel: DeviceUiModel? = null,
)

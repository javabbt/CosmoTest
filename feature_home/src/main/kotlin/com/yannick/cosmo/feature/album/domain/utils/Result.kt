package com.yannick.cosmo.feature.album.domain.utils

import androidx.annotation.StringRes

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val output: T) : Result<T>()
    data class Error(val exception: String) : Result<Nothing>()
    data class UnexpectedError(@StringRes val exception: Int) : Result<Nothing>()
}
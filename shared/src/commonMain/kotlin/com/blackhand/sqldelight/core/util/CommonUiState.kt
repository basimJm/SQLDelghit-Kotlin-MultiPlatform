package com.blackhand.sqldelight.core.util

sealed class CommonUiState<T>(
    data: T? = null,
    successMessage: String? = null,
    errorMessage: String? = null, isLoading: Boolean? = null
) {
    data class Success<T>(var data: T? = null, var successMessage: String? = null) :
        CommonUiState<T>(data = data, successMessage = successMessage)

    data class Error<T>(var errorMessage: String? = null) :
        CommonUiState<T>(errorMessage = errorMessage)

    data class Loading<T>(var isLoading: Boolean = false) : CommonUiState<T>(isLoading = isLoading)
}
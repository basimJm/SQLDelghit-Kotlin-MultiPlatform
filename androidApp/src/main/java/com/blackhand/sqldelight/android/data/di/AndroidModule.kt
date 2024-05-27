package com.blackhand.sqldelight.android.data.di

import com.blackhand.sqldelight.android.presentation.feature.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AndroidModule = module {
    viewModel { HomeViewModel(get(), get(), get()) }
}

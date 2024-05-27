package com.blackhand.sqldelight.core.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface  Dispatcher {
    val io : CoroutineDispatcher
}

expect fun provideDispatcher():Dispatcher


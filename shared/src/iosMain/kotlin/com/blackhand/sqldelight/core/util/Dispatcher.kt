package com.blackhand.sqldelight.core.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class IosCoroutineDispatcher : Dispatcher{
    override val io: CoroutineDispatcher
        get() = Dispatchers.Default
}

actual fun provideDispatcher():Dispatcher = IosCoroutineDispatcher()
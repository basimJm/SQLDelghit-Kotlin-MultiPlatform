package com.blackhand.sqldelight.core.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AndroidCoroutineDispatcher() : Dispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

}

actual fun provideDispatcher(): Dispatcher = AndroidCoroutineDispatcher()
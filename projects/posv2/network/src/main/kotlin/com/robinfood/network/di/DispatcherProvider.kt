package com.robinfood.network.di

import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {

    val io get() = Dispatchers.IO
}

class DefaultDispatcherProvider : DispatcherProvider
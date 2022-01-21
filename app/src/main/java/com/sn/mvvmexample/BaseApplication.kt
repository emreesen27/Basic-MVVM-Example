package com.sn.mvvmexample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Dependency Injection with Hilt
 * Reference: https://developer.android.com/training/dependency-injection/hilt-android
 */
@HiltAndroidApp
class BaseApplication : Application()
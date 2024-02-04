package com.yannick.cosmo.app

import android.app.Application
import com.cosmo.bluetooth.featureBluetoothModules
import com.yannick.cosmo.BuildConfig
import com.yannick.cosmo.appModule
import com.yannick.cosmo.base.baseModule
import com.yannick.cosmo.feature.album.featureHomeModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import timber.log.Timber

class CosmosApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
    }

    private fun initKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@CosmosApplication)
            modules(appModule)
            modules(baseModule)
            modules(featureHomeModules)
            modules(featureBluetoothModules)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

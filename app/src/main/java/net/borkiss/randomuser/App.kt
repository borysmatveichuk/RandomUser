package net.borkiss.randomuser

import androidx.multidex.MultiDexApplication
import net.borkiss.randomuser.di.apiModule
import net.borkiss.randomuser.di.viewModelsModule
import org.koin.android.ext.android.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(viewModelsModule, apiModule))
    }
}
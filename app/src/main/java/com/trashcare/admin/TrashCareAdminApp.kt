package com.trashcare.admin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class TrashCareAdminApp : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@TrashCareAdminApp)
            modules(
                // TODO: add modules here
            )
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}
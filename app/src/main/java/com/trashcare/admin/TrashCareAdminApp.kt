package com.trashcare.admin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.trashcare.admin.data.remote.ApiService
import com.trashcare.admin.data.remote.RetrofitClient
import com.trashcare.admin.data.repository.TrashAdminRepository
import com.trashcare.admin.presentation.viewmodel.TrashCareAdminViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

class TrashCareAdminApp : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@TrashCareAdminApp)
            modules(
                repositoryModule, vmModule
            )
        }
    }

    private val vmModule = module {
        viewModel { TrashCareAdminViewModel(get()) }
    }

    private val repositoryModule = module {
        single { RetrofitClient.createService<ApiService>() }
        single { TrashAdminRepository(get()) }
    }



    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}
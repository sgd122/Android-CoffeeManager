/*
 * Created by Lee Oh Hyoung on 2019-08-10..
 */
package com.dnd.killcaffeine

import android.app.Application
import com.dnd.killcaffeine.di.*
import com.dnd.killcaffeine.model.remote.service.CoffeeManagerService
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class  CoffeeManagerApplication : Application() {

    companion object {
        private const val PRINT_STACK_COUNT: Int = 5
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CoffeeManagerApplication)
            modules(listOf(adapterModule, apiModule, databaseModule,
                repositoryModule, retrofitModule, viewModelModule))
        }

        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .methodCount(PRINT_STACK_COUNT)
            .build()

        Logger.addLogAdapter(object: AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                // DEBUG 모드에서만 로그 출력
                return BuildConfig.DEBUG
            }
        })
    }

}
/*
 * Created by Lee Oh Hyoung on 2019-08-10..
 */
package com.dnd.killcaffeine.di

import androidx.room.Room
import com.dnd.killcaffeine.detail.DetailViewModel
import com.dnd.killcaffeine.history.HistoryTodayViewModel
import com.dnd.killcaffeine.history.recyclerview.HistoryTodayAdapter
import com.dnd.killcaffeine.main.MainViewModel
import com.dnd.killcaffeine.main.home.MainHomeViewModel
import com.dnd.killcaffeine.main.home.recyclerview.DecaffeineRecyclerViewAdpater
import com.dnd.killcaffeine.main.home.recyclerview.RecentDrinkRecyclerViewAdapter
import com.dnd.killcaffeine.main.settings.MainSettingsViewModel
import com.dnd.killcaffeine.main.statistics.MainStatisticsViewModel
import com.dnd.killcaffeine.model.data.history.HistoryDatabase
import com.dnd.killcaffeine.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        SplashViewModel()
    }
    viewModel {
        MainViewModel()
    }
    viewModel {
        MainHomeViewModel()
    }
    viewModel {
        MainStatisticsViewModel()
    }
    viewModel {
        MainSettingsViewModel()
    }
    viewModel {
        DetailViewModel()
    }
    viewModel {
        HistoryTodayViewModel()
    }
}

val adapterModule = module {
    factory {
        DecaffeineRecyclerViewAdpater()
    }
    factory {
        RecentDrinkRecyclerViewAdapter()
    }
    factory {
        HistoryTodayAdapter()
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), HistoryDatabase::class.java, "History-db").build()
    }
}

val appModule =  listOf(viewModelModule, adapterModule, databaseModule)
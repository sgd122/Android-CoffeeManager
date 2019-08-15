/*
 * Created by Lee Oh Hyoung on 2019-08-10..
 */
package com.dnd.killcaffeine.di

import com.dnd.killcaffeine.detail.DetailViewModel
import com.dnd.killcaffeine.main.MainViewModel
import com.dnd.killcaffeine.main.home.MainHomeViewModel
import com.dnd.killcaffeine.main.home.recyclerview.DecaffeineRecyclerViewAdpater
import com.dnd.killcaffeine.main.home.recyclerview.RecentDrinkRecyclerViewAdpater
import com.dnd.killcaffeine.main.settings.MainSettingsViewModel
import com.dnd.killcaffeine.main.statistics.MainStatisticsViewModel
import com.dnd.killcaffeine.splash.SplashViewModel
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
}

val adapterModule = module {
    factory {
        DecaffeineRecyclerViewAdpater()
    }
    factory {
        RecentDrinkRecyclerViewAdpater()
    }
}

val appModule =  listOf(viewModelModule, adapterModule)
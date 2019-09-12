/*
 * Created by Lee Oh Hyoung on 2019-08-10..
 */
package com.dnd.killcaffeine.di

import androidx.preference.PreferenceManager
import androidx.room.Room
import com.dnd.killcaffeine.history.HistoryTodayViewModel
import com.dnd.killcaffeine.history.today.HistoryTodayRegisterViewModel
import com.dnd.killcaffeine.history.today.register.HistoryRegisterChoiceFranchiseViewModel
import com.dnd.killcaffeine.history.today.register.HistoryRegisterChoiceMenuViewModel
import com.dnd.killcaffeine.history.today.register.HistoryRegisterChoiceTypeViewModel
import com.dnd.killcaffeine.main.MainViewModel
import com.dnd.killcaffeine.main.home.MainHomeViewModel
import com.dnd.killcaffeine.main.home.show_more.TodayRecommendDrinkViewModel
import com.dnd.killcaffeine.main.settings.MainSettingsViewModel
import com.dnd.killcaffeine.main.settings.notice.MainSettingsNoticeViewModel
import com.dnd.killcaffeine.main.settings.notice.fragment.NoticeDetailViewModel
import com.dnd.killcaffeine.main.settings.notice.fragment.NoticeListViewModel
import com.dnd.killcaffeine.main.settings.personal.MainPersonalSettingViewModel
import com.dnd.killcaffeine.main.settings.terms.MainSettingsTermsViewModel
import com.dnd.killcaffeine.main.statistics.MainStatisticsViewModel
import com.dnd.killcaffeine.model.data.room.menu.MenuDatabase
import com.dnd.killcaffeine.recyclerview.*
import com.dnd.killcaffeine.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single{
        Room.databaseBuilder(androidApplication(), MenuDatabase::class.java, "Menu-db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        PreferenceManager.getDefaultSharedPreferences(androidContext())
    }
}

val viewModelModule = module {
    viewModel {
        SplashViewModel(get())
    }
    viewModel {
        MainViewModel()
    }
    viewModel {
        MainHomeViewModel(get(), get())
    }
    viewModel {
        MainStatisticsViewModel()
    }
    viewModel {
        MainSettingsViewModel()
    }
    viewModel {
        TodayRecommendDrinkViewModel()
    }
    viewModel {
        HistoryTodayViewModel(get())
    }
    viewModel {
        HistoryTodayRegisterViewModel()
    }
    viewModel {
        HistoryRegisterChoiceTypeViewModel()
    }
    viewModel {
        HistoryRegisterChoiceFranchiseViewModel()
    }
    viewModel {
        HistoryRegisterChoiceMenuViewModel()
    }
    viewModel {
        MainSettingsTermsViewModel()
    }
    viewModel {
        MainSettingsNoticeViewModel()
    }
    viewModel {
        NoticeListViewModel()
    }
    viewModel {
        NoticeDetailViewModel()
    }
    viewModel {
        MainPersonalSettingViewModel(get())
    }
}

val adapterModule = module {
    factory {
        DecaffeineAdpater()
    }
    factory {
        RecentDrinkAdapter()
    }
    factory {
        HistoryTodayAdapter()
    }
    factory {
        FranchiseMenuAdapter()
    }
    factory {
        NoticeAdapter()
    }
}

val appModule =  listOf(databaseModule, viewModelModule, adapterModule)
/*
 * Created by Lee Oh Hyoung on 2019-08-10..
 */
package com.dnd.killcaffeine.di

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
import com.dnd.killcaffeine.membership.SignInViewModel
import com.dnd.killcaffeine.membership.SignUpViewModel
import com.dnd.killcaffeine.membership.sign_up.SignUpBasicViewModel
import com.dnd.killcaffeine.membership.sign_up.SignUpHintViewModel
import com.dnd.killcaffeine.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SplashViewModel(get(), get())
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
        NoticeListViewModel(get())
    }
    viewModel {
        NoticeDetailViewModel(get())
    }
    viewModel {
        MainPersonalSettingViewModel(get())
    }
    viewModel {
        SignInViewModel()
    }
    viewModel {
        SignUpViewModel()
    }
    viewModel {
        SignUpBasicViewModel()
    }
    viewModel {
        SignUpHintViewModel()
    }
}
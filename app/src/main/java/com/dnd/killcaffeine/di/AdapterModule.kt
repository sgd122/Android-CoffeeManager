/*
 * Created by Lee Oh Hyoung on 2019-09-14..
 */
package com.dnd.killcaffeine.di

import com.dnd.killcaffeine.recyclerview.*
import org.koin.dsl.module

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
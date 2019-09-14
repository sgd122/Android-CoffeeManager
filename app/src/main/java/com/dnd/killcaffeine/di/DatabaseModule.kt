/*
 * Created by Lee Oh Hyoung on 2019-09-14..
 */
package com.dnd.killcaffeine.di

import androidx.preference.PreferenceManager
import androidx.room.Room
import com.dnd.killcaffeine.model.data.room.menu.MenuDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
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
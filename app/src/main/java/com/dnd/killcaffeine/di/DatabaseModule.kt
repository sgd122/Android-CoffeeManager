/*
 * Created by Lee Oh Hyoung on 2019-09-14..
 */
package com.dnd.killcaffeine.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.dnd.killcaffeine.model.data.room.menu.MenuDao
import com.dnd.killcaffeine.model.data.room.menu.MenuDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun menuDaoFactory(roomDataBase: MenuDatabase): MenuDao {
        return roomDataBase.menuDao
    }

    fun menuDataBaseFactory(application: Application): MenuDatabase {
        return Room.databaseBuilder(application, MenuDatabase::class.java, "Menu-db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun preferenceFactory(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    single { menuDaoFactory(get()) }

    single{ menuDataBaseFactory(androidApplication()) }

    single { preferenceFactory(androidApplication()) }
}
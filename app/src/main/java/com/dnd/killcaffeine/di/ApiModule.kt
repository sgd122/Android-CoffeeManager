/*
 * Created by Lee Oh Hyoung on 2019-09-14..
 */
package com.dnd.killcaffeine.di

import com.dnd.killcaffeine.model.remote.service.CoffeeManagerService
import com.dnd.killcaffeine.model.remote.service.GithubService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    single {
        get<Retrofit>().create(CoffeeManagerService::class.java)
    }
    single{
        get<Retrofit>().create(GithubService::class.java)
    }
}
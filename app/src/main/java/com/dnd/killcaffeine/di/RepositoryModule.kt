/*
 * Created by Lee Oh Hyoung on 2019-09-14..
 */
package com.dnd.killcaffeine.di

import com.dnd.killcaffeine.model.CoffeeRepository
import com.dnd.killcaffeine.model.GithubRepository
import com.dnd.killcaffeine.model.remote.CoffeeRemoteDataSource
import com.dnd.killcaffeine.model.remote.GithubRemoteDataSource
import org.koin.dsl.module

val repositoryModule = module {

    factory {
        CoffeeRepository(CoffeeRemoteDataSource(get()))
    }
    factory {
        GithubRepository(GithubRemoteDataSource(get()))
    }
}
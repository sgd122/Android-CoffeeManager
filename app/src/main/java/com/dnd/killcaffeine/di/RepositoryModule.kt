/*
 * Created by Lee Oh Hyoung on 2019-09-14..
 */
// Reference: https://medium.com/swlh/mvvm-on-android-with-the-architecture-components-koin-f53c3c200363
package com.dnd.killcaffeine.di

import com.dnd.killcaffeine.model.CoffeeRepository
import com.dnd.killcaffeine.model.GithubRepository
import com.dnd.killcaffeine.model.data.room.menu.MenuDao
import com.dnd.killcaffeine.model.remote.service.CoffeeManagerService
import com.dnd.killcaffeine.model.remote.service.GithubService
import org.koin.dsl.module

val repositoryModule = module {

    fun coffeeRepositoryFactory(mCoffeeService: CoffeeManagerService,
                                mMenuDao: MenuDao): CoffeeRepository {
        return CoffeeRepository(mCoffeeService, mMenuDao)
    }

    fun githubRepositoryFactory(mGithubService: GithubService): GithubRepository {
        return GithubRepository(mGithubService)
    }

    single {
        coffeeRepositoryFactory(get() , get())
    }
    single {
        githubRepositoryFactory(get())
    }
}
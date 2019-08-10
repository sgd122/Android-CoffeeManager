/*
 * Created by iohyeong on 2019-08-10..
 */
package com.dnd.killcaffeine.model.remote

import com.dnd.killcaffeine.model.BaseRetrofit
import com.dnd.killcaffeine.model.data.result.DecaffeineResult
import com.dnd.killcaffeine.model.data.result.FranchiseResult
import io.reactivex.Single
import retrofit2.http.GET

interface CoffeeManagerService {

    @GET("/api/decaffeine-menu")
    fun getDecaffeineMenuList() : Single<DecaffeineResult>

    @GET("/api/franchise")
    fun getFranchiseMenuList() : Single<FranchiseResult>

    companion object {
        fun getDecaffineMenuList() : Single<DecaffeineResult> = BaseRetrofit.create(CoffeeManagerService::class.java).getDecaffeineMenuList()

        fun getFranchiseMenuList() : Single<FranchiseResult> = BaseRetrofit.create(CoffeeManagerService::class.java).getFranchiseMenuList()
    }
}
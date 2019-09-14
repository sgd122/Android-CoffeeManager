/*
 * Created by iohyeong on 2019-08-10..
 */
package com.dnd.killcaffeine.model.remote.service

import com.dnd.killcaffeine.model.data.response.NoticeDetail
import com.dnd.killcaffeine.model.data.result.DecaffeineResult
import com.dnd.killcaffeine.model.data.result.FranchiseResult
import com.dnd.killcaffeine.model.data.result.NoticeResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CoffeeManagerService {

    @GET("/api/menu/deffeine")
    fun getDecaffeineMenuList() : Single<DecaffeineResult>

    @GET("/api/franchise")
    fun getFranchiseMenuList() : Single<FranchiseResult>

    @GET("/api/notice")
    fun getNoticeList() : Single<NoticeResult>

    @GET("/api/notice/{noticeId}")
    fun getNoticeDetail(@Path("noticeId") noticeId: Int): Single<NoticeDetail>
}
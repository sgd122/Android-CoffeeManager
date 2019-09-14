/*
 * Created by Lee Oh Hyoung on 2019-09-14..
 */
package com.dnd.killcaffeine.model.remote

import com.dnd.killcaffeine.model.data.response.NoticeDetail
import com.dnd.killcaffeine.model.data.result.DecaffeineResult
import com.dnd.killcaffeine.model.data.result.FranchiseResult
import com.dnd.killcaffeine.model.data.result.NoticeResult
import com.dnd.killcaffeine.model.remote.service.CoffeeManagerService
import io.reactivex.Single

class CoffeeRemoteDataSource(private val mCoffeeService: CoffeeManagerService) : CoffeeManagerService {

    override fun getDecaffeineMenuList(): Single<DecaffeineResult> {
        return mCoffeeService.getDecaffeineMenuList()
    }

    override fun getFranchiseMenuList(): Single<FranchiseResult> {
        return mCoffeeService.getFranchiseMenuList()
    }

    override fun getNoticeList(): Single<NoticeResult> {
        return mCoffeeService.getNoticeList()
    }

    override fun getNoticeDetail(noticeId: Int): Single<NoticeDetail> {
        return mCoffeeService.getNoticeDetail(noticeId = noticeId)
    }
}
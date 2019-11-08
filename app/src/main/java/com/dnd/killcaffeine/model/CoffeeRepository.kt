/*
 * Created by Lee Oh Hyoung on 2019-09-14..
 */
package com.dnd.killcaffeine.model

import com.dnd.killcaffeine.model.data.response.NoticeDetail
import com.dnd.killcaffeine.model.data.result.DecaffeineResult
import com.dnd.killcaffeine.model.data.result.FranchiseResult
import com.dnd.killcaffeine.model.data.result.NoticeResult
import com.dnd.killcaffeine.model.data.room.menu.Menu
import com.dnd.killcaffeine.model.data.room.menu.MenuDao
import com.dnd.killcaffeine.model.remote.service.CoffeeManagerService
import io.reactivex.Completable
import io.reactivex.Single

class CoffeeRepository(private val mCoffeeService: CoffeeManagerService,
                       private val mMenuDao: MenuDao) : CoffeeManagerService, MenuDao {

    override fun insertMenu(menu: Menu): Completable {
        return mMenuDao.insertMenu(menu)
    }

    override fun insertMenuList(menuList: List<Menu>) : Completable {
        return mMenuDao.insertMenuList(menuList)
    }

    override fun getAllMenu(): Single<List<Menu>> {
        return mMenuDao.getAllMenu()
    }

    override fun deleteMenu(menu: Menu): Completable {
        return mMenuDao.deleteMenu(menu)
    }

    override fun deleteAllMenu(menuList: List<Menu>): Completable {
        return mMenuDao.deleteAllMenu(menuList)
    }

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
/*
 * Created by Lee Oh Hyoung on 2019-09-03..
 */
package com.dnd.killcaffeine.model.data.room.menu

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenu(menu: Menu) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenuList(menuList: List<Menu>) : Completable

    @Query("SELECT * FROM menu")
    fun getAllMenu(): Single<List<Menu>>

    @Delete
    fun deleteMenu(menu: Menu) : Completable

    @Delete
    fun deleteAllMenu(menuList: List<Menu>) : Completable
}
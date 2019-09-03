/*
 * Created by Lee Oh Hyoung on 2019-09-03..
 */
package com.dnd.killcaffeine.model.data.menu

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(Menu::class)], version = 1, exportSchema = false)
abstract class MenuDatabase : RoomDatabase() {

    abstract val menuDao: MenuDao
}
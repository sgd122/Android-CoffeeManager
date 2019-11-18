/*
 * Created by Lee Oh Hyoung on 2019-09-03..
 */
package com.dnd.killcaffeine.model.data.room.menu

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(Menu::class)], version = 3, exportSchema = false)
abstract class MenuDatabase : RoomDatabase() {

    abstract val menuDao: MenuDao
}
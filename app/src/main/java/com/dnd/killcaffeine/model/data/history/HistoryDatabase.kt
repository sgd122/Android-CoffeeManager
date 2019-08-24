/*
 * Created by Lee Oh Hyoung on 2019-08-24..
 */
package com.dnd.killcaffeine.model.data.history

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(History::class)], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {

    abstract val historyDao: HistoryDao
}
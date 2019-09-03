/*
 * Created by Lee Oh Hyoung on 2019-09-03..
 */
package com.dnd.killcaffeine.model.data.room.personal

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(Personal::class)], version = 2)
abstract class PersonalDatabase : RoomDatabase() {

    abstract val mPersonDao: PersonalDao
}
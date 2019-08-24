/*
 * Created by Lee Oh Hyoung on 2019-08-24..
 */
package com.dnd.killcaffeine.model.data.history

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(history: History) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistoryList(historyList: List<History>) : Completable

    @Query("SELECT * FROM history")
    fun getAllHistory(): Single<List<History>>

    @Delete
    fun deleteHistory(history: History) : Completable

    @Delete
    fun deleteAllHistory(historyList: List<History>) : Completable


}
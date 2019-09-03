/*
 * Created by Lee Oh Hyoung on 2019-09-03..
 */
package com.dnd.killcaffeine.model.data.room.personal

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PersonalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPersonalInfo(personal: Personal): Completable

    @Query("SELECT * FROM personal LIMIT 1")
    fun getPersonalInfo() : Single<Personal>

    @Query("DELETE FROM personal")
    fun deletePersonalInfo(): Completable
}
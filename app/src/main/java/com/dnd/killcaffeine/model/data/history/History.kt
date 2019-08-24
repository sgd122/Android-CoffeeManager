/*
 * Created by Lee Oh Hyoung on 2019-08-24..
 */
package com.dnd.killcaffeine.model.data.history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(@PrimaryKey(autoGenerate = true) val id: Int,
                   val menuName: String,
                   val franchiseName: String,
                   val caffeine: Int)
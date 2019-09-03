package com.dnd.killcaffeine.model.data.menu

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/*
 * Created by iohyeong on 2019-08-10..
 */

@Entity(tableName = "menu")
data class Menu(@PrimaryKey(autoGenerate = true) val menuId: Int,
                val menuName: String,
                val menuImgUrl: String,
                val franchiseName: String,
                val caffeine: Int,
                val personalShop: Boolean) : Serializable
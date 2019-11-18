package com.dnd.killcaffeine.model.data.room.menu

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dnd.killcaffeine.R
import java.io.Serializable

/*
 * Created by iohyeong on 2019-08-10..
 */

@Entity(tableName = "menu")
data class Menu(val menuName: String,
                val franchiseName: String,
                val caffeine: Int = 0,
                val menuImgUrl: Int = R.drawable.coffee_sample,
                val personalShop: Boolean = false,
                val createAt: Long = System.currentTimeMillis(),
                @PrimaryKey(autoGenerate = true) val menuId: Int = 0) : Serializable {

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Menu>() {
            override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
                return oldItem.menuId == newItem.menuId
            }

            override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
                return oldItem == newItem
            }

        }
    }
}
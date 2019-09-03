package com.dnd.killcaffeine.model.data.result

import com.dnd.killcaffeine.model.data.room.menu.Menu
import java.io.Serializable

/*
 * Created by iohyeong on 2019-08-10..
 */
data class DecaffeineResult(val success: Boolean,
                            val code: Int,
                            val msg: String,
                            val list: ArrayList<Menu>): Serializable
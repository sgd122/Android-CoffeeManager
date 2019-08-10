package com.dnd.killcaffeine.model.data.result

import com.dnd.killcaffeine.model.data.menu.Menu

/*
 * Created by iohyeong on 2019-08-10..
 */
data class DecaffeineResult(val success: Boolean,
                            val code: Int,
                            val msg: String,
                            val list: ArrayList<Menu>)
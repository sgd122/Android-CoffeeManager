package com.dnd.killcaffeine.model.data.menu

/*
 * Created by iohyeong on 2019-08-10..
 */
data class Menu(val menuId: Int,
                val menuName: String,
                val menuImgUrl: String,
                val franchiseName: String,
                val caffeine: Int,
                val personalShop: Boolean)
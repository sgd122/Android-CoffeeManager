package com.dnd.killcaffeine.model.data.menu

/*
 * Created by iohyeong on 2019-08-10..
 */
data class FranchiseMenu(val franchiseId: Int,
                         val franchiseName: String,
                         val franchiseImgUrl: String,
                         val menu: ArrayList<Menu>)
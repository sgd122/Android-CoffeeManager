package com.dnd.killcaffeine.model.data.result

import com.dnd.killcaffeine.model.data.menu.FranchiseMenu

/*
 * Created by iohyeong on 2019-08-10..
 */
data class FranchiseResult(val success: Boolean,
                           val code: Int,
                           val msg: String,
                           val list: ArrayList<FranchiseMenu>)
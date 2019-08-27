/*
 * Created by Lee Oh Hyoung on 2019-08-27..
 */
package com.dnd.killcaffeine.model.data.response

import java.io.Serializable

data class Notice(val noticeId: Int,
                  val title: String,
                  val writeTime: String) : Serializable
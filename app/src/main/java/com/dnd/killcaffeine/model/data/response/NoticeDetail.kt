/*
 * Created by Lee Oh Hyoung on 2019-08-28..
 */
package com.dnd.killcaffeine.model.data.response

import java.io.Serializable

data class NoticeDetail(val success: Boolean,
                        val code: Int,
                        val message: String,
                        val data: String) : Serializable
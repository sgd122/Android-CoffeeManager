/*
 * Created by Lee Oh Hyoung on 2019-08-27..
 */
package com.dnd.killcaffeine.model.data.result

import com.dnd.killcaffeine.model.data.response.Notice
import java.io.Serializable

data class NoticeResult(val success: Boolean,
                        val code: Int,
                        val message: String,
                        val list: ArrayList<Notice>) : Serializable
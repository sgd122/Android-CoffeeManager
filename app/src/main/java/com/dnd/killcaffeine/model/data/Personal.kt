/*
 * Created by Lee Oh Hyoung on 2019-09-04..
 */
package com.dnd.killcaffeine.model.data

import java.io.Serializable

data class Personal(val bodyType: PersonalBodyType,
                    val weight: Int,
                    val recommendIntake: Int): Serializable {
}
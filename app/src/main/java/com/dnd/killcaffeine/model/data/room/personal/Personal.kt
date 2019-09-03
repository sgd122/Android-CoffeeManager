/*
 * Created by Lee Oh Hyoung on 2019-09-03..
 */
package com.dnd.killcaffeine.model.data.room.personal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personal")
data class Personal(@PrimaryKey(autoGenerate = true) val id: Int,
                    val bodyType: String,
                    val weight: Int,
                    val recommendIntake: Int){

    enum class BodyType {
        ADULT, PREGNANT, TEEN_OLD
    }
}
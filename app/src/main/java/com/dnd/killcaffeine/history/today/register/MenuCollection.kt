/*
 * Created by Lee Oh Hyoung on 2019/11/19.
 */
package com.dnd.killcaffeine.history.today.register

import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.model.data.room.menu.Menu

object MenuCollection {

    private const val STARBUCKS: String = "스타벅스"
    private const val EDIYA: String = "이디야"
    private const val HOLLYS: String = "할리스"
    private const val ANGELINUS: String = "엔젤리너스"

    fun starBucks(): ArrayList<Menu> = arrayListOf(
        Menu("나이트로 쇼콜라", STARBUCKS, 245, R.drawable.starbucks_nitro_coldbrew)
    )

    fun ediya(): ArrayList<Menu> = arrayListOf(
        Menu("이디야", EDIYA, 245, R.drawable.starbucks_nitro_coldbrew)
    )

    fun hollys(): ArrayList<Menu> = arrayListOf(
        Menu("할리스", HOLLYS, 245, R.drawable.starbucks_nitro_coldbrew)
    )

    fun angelinus(): ArrayList<Menu> = arrayListOf(
        Menu("엔젤리너스", ANGELINUS, 245, R.drawable.starbucks_nitro_coldbrew)
    )
}
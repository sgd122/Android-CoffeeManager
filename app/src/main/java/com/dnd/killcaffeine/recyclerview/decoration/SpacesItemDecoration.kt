/*
 * Created by Lee Oh Hyoung on 2019-08-25..
 */
package com.dnd.killcaffeine.recyclerview.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * GridLayout 일때 Column 가운데 정렬 해주는 ItemDecoration
 * @param space 단위 : Pixels
 * **/
class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        outRect.left = space
        outRect.right = space
        outRect.bottom = space
    }

}
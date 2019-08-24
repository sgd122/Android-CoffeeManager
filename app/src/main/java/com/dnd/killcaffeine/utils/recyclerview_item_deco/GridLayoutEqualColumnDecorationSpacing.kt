/*
 * Created by Lee Oh Hyoung on 2019-08-22 .. 
 */
package com.dnd.killcaffeine.utils.recyclerview_item_deco

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

/**
 * GridLayout 일때 Column 가운데 정렬 해주는 ItemDecoration
 * 주고 싶은 Padding 값의 절반을 넣어야한다.
 * **/
class GridLayoutEqualColumnDecorationSpacing constructor(private val mItemOffset: Int) : RecyclerView.ItemDecoration() {

    constructor(context: Context, @DimenRes itemOffset: Int) : this(context.resources.getDimensionPixelSize(itemOffset))

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset)
    }
}
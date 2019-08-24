/*
 * Created by Lee Oh Hyoung on 2019-08-22 .. 
 */
package com.dnd.killcaffeine.utils.recyclerview_item_deco

import android.graphics.Rect
import android.view.View
import androidx.annotation.IntRange
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemMargin constructor(@IntRange(from=0) val margin: Int, @IntRange(from=0) val columns: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.bottom = margin

    }


}
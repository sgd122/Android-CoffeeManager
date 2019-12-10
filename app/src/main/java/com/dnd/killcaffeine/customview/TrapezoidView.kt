package com.dnd.killcaffeine.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.PathShape
import android.util.AttributeSet
import android.view.View
import com.dnd.killcaffeine.R

/*
 * Created by iohyeong on 2019-08-10..
 */
class TrapezoidView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val mTrapezoid: ShapeDrawable

    init {
        val path = Path().apply {
            moveTo(0.0f, 0.0f)
            lineTo(100.0f, 0.0f)
            lineTo(200.0f, 100.0f)
            lineTo(0.0f, 100.0f)
            lineTo(0.0f, 0.0f)
        }

        mTrapezoid = ShapeDrawable(PathShape(path, 200.0f, 100.0f)).apply {
            with(paint) {
                strokeWidth = 1.0f
                background = GradientDrawable(GradientDrawable.Orientation.BL_TR, intArrayOf(R.color.colorGradientStart, R.color.colorGradientCenter, R.color.colorGradientEnd))
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mTrapezoid.bounds = Rect(0, 0, w, h)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            mTrapezoid.draw(it)
        }
    }
}
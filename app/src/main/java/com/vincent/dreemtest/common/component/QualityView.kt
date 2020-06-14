package com.vincent.dreemtest.common.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.vincent.dreemtest.R
import com.vincent.dreemtest.common.binding.setPercentage
import kotlin.properties.Delegates

class QualityView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0): FrameLayout(context, attributeSet, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var rectF: RectF
    private val textView = TextView(context)


    init {
        setWillNotDraw(false)
        textView.setTextAppearance(R.style.TextAppearance_Headline4)
        textView.setTextColor(ContextCompat.getColor(context, R.color.onSurface500))
        textView.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT).apply { gravity = Gravity.CENTER }
        textView.gravity = Gravity.CENTER
        addView(textView)
        setPercentage(textView, 0f)

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = context.resources.displayMetrics.density * 4f
    }

    var percentage: Float by Delegates.observable(0f) { _, _, newValue ->
        setPercentage(textView, newValue)
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val padding = paint.strokeWidth / 2f
        rectF = RectF(padding, padding, w.toFloat() - padding, h.toFloat() - padding)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        paint.color = ContextCompat.getColor(context, R.color.primary100)
        canvas.drawArc(rectF, -90f, 360f, false, paint)
        paint.color = ContextCompat.getColor(context, R.color.primary500)
        canvas.drawArc(rectF, -90f, 360f * percentage, false, paint)

    }

}
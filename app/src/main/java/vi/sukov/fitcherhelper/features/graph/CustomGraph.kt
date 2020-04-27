package vi.sukov.fitcherhelper.features.graph

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View

class CustomGraph @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attr, defStyle) {

    companion object {
        // colors
        const val DEF_COLOR_BACKGROUND: Int = Color.WHITE
        const val DEF_COLOR_GRAPH: Int = Color.LTGRAY
        const val DEF_COLOR_STROKE: Int = Color.GRAY
        const val DEF_COLOR_LINE: Int = Color.RED

        const val MIN_PRESSURE: Int = 500
        const val MAX_PRESSURE: Int = 1000
    }

    private var mapPressure: Map<Int, Int>? = null     // DAY | PRESSURE

    init {

        val a: TypedArray = context.obtainStyledAttributes(TODO())

        try {

        } finally {
            a.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    private fun drawBackground(canvas: Canvas) {}

    private fun drawRails(canvas: Canvas) {}

    private fun drawDayNumbers(canvas: Canvas) {}

    private fun drawGraph(canvas: Canvas) {}

    private fun drawLineMin(canvas: Canvas) {}

    private fun drawLineMax(canvas: Canvas) {}

    private fun drawLineAverage(canvas: Canvas) {}

    private fun drawApexPoint(canvas: Canvas) {}

    fun setMapPressure(map: Map<Int, Int>) {
        mapPressure = map
    }

    private val minPressure = mapPressure?.minBy { it.value } ?: MIN_PRESSURE
    private val maxPressure = mapPressure?.maxBy { it.value } ?: MAX_PRESSURE
    private val avePressure =
        mapPressure!!.map { it.value }.sumBy { it } / mapPressure!!.keys.last()

}
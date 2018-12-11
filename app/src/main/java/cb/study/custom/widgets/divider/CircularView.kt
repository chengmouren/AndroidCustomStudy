package cb.study.custom.widgets.divider

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircularView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    :View(context, attrs, defStyleAttr){

    private val paint =Paint().apply {
        color=Color.RED //画笔颜色
        /**
         *   设置填充样式
         *   Style.FILL 仅填充内部
         *   Style.FILL_AND_STROKE 填充内部和描边
         *   Style.STROKE  仅描边
         */

        style =Paint.Style.STROKE
        strokeWidth =50f //设置画笔宽度
        //是否打开抗锯齿（依赖算法，一般在绘制不规则的图形时使用，比如圆形，文字等）
        isAntiAlias=true

    }

    /**
     * 绘制函数，
     * canvas:画布，调用它的函数直接显示在控件上
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //绘制一个圆
        canvas?.drawCircle(190f,200f,150f,paint)
    }
}
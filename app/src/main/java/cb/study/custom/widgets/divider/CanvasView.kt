package cb.study.custom.widgets.divider

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CanvasView  @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color= Color.RED //画笔颜色
        /**
         *   设置填充样式
         *   Style.FILL 仅填充内部
         *   Style.FILL_AND_STROKE 填充内部和描边
         *   Style.STROKE  仅描边
         */
        style = Paint.Style.STROKE
        strokeWidth =50f //设置画笔宽度(单位：px)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        /**
         * 设置画布背景颜色
         */
        canvas?.drawRGB(255,0,255) //rgb设置，十进制，也可以传入十六禁制0xFF..等
//        canvas?.drawColor(0xFFFF00FF) //变种写法
//        canvas?.drawARGB(0xFF,0xFF,0xFF)//变种写法，可以设置透明度

        /**
         * 画直线（paint设置Style不会影响，StrokeWidth才会影响线的粗细）
         * startx,starty 起始点x,y坐标
         * stopx,stopy 终点x,y坐标
         */

        canvas?.drawLine(100f,100f,200f,200f,paint)

        /**
         * 画多条直线
         * pts格式为{x1,y1,x2,y2,x3,y3，....}
         * 不是连接线，而是对应的坐标点两两连成一条直线
         */
        val pts = floatArrayOf(10f,10f,100f,100f,200f,200f,400f,400f)
        canvas?.drawLines(pts,paint)
    }
}
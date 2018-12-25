package cb.study.custom.widgets.divider

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class PaintTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {
    private val mPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.RED
        strokeWidth = 5f
        isAntiAlias = true
        textSize = 120f //设置文字大小
        textAlign =Paint.Align.RIGHT //设置所绘制的字符串与起始点相对的位置
        isFakeBoldText = true // 设置是否为粗体文字
        isUnderlineText = true //是否设置下划线
        textSkewX = 0.5f //设置字体水平倾斜度，普通字体设为-0.25
        isStrikeThruText=true //设置带有删除线效果
//        textScaleX=2f //水平方向拉伸，高度不变
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        //起始位置开始绘制字符串，x,y为起始点坐标
//        canvas.drawText("我要做大佬",800f,200f,mPaint)
        //start，end截取字符串起始和终止位置
//        canvas.drawText("我要做大佬",2,4,800f,200f,mPaint)


    }
}
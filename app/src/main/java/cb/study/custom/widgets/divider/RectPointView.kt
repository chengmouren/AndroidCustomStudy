package cb.study.custom.widgets.divider

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class RectPointView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    private var mx = 0
    private var mY = 0
    private var mRect: Rect = Rect(10, 10, 200, 200)
    private var mRect1: Rect = Rect(190, 10, 250, 200)
    private var mRect2: Rect = Rect(100, 200, 300, 400)
    private var mRect3: Rect = Rect(100, 200, 300, 400)

    private val mPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (mRect.contains(mx,mY)){
            mPaint.color=Color.RED
        }else{
            mPaint.color=Color.GREEN
        }
        canvas?.drawRect(mRect,mPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //获取当前手指在xy的坐标
        mx = event.x.toInt()
        mY = event.y.toInt()

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                //重新绘制
                invalidate()
                //atcionDown返回true,该控件拦截消费，move，up消息仍然传递过来，如果是false,
                //表示控件不需要这个消息，后续的move,up不会传递到控件
                return true
            }
            MotionEvent.ACTION_UP -> {
                mx = -1
                mY = -1
            }
        }
        //可以在任意线程调用，这里用做个对比而已，重新绘制
        postInvalidate()
        return super.onTouchEvent(event)
    }

}
package cb.study.custom.widgets.divider

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.widget.toast

class RectPointView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    private var mx = 0
    private var mY = 0
    private var mRect: Rect = Rect(10, 10, 200, 200)
    private var mRect1: Rect = Rect(10, 10, 200, 200)
    private var mRect2: Rect = Rect(190, 10, 250, 200)
    private var mRect3: Rect = Rect(10, 210, 200, 300)

    private val mPaint = Paint().apply {
        style = Paint.Style.STROKE
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        mPaint.color = Color.RED
//        canvas?.drawRect(mRect1, mPaint)
//        mPaint.color = Color.GREEN
//        canvas?.drawRect(mRect2, mPaint)
//        mPaint.color = Color.BLUE
//        canvas?.drawRect(mRect3, mPaint)

//        interset()
            unioApi(canvas)

//        if (mRect.contains(mx,mY)){
//            mPaint.color=Color.RED
//        }else{
//            mPaint.color=Color.GREEN
//        }
//        canvas?.drawRect(mRect,mPaint)
    }

    private fun unioApi(canvas: Canvas?) {
        /**
         * 合并是将两个矩形合并一个矩形
         * 无论这两个矩形是否相交，取两个矩形里最小的左上角点作为结果矩形的左上角点，取两个矩形最大右下角点
         * 作为结果矩形的右下角点，如果合并有个矩形为空，有值的作为最终结果
         */
//        mPaint.color=Color.RED
//        canvas?.drawRect(mRect,mPaint)
//        mPaint.color=Color.GREEN
//        canvas?.drawRect(mRect3,mPaint)
//        mRect.union(mRect3)
//        mPaint.color=Color.BLUE
//        canvas?.drawRect(mRect,mPaint)

        /**
         * 合并矩形与某个坐标点
         * 先判断当前矩形与目标合并点的关系，如果不相交，根据目标点（x,y）位置，将目标点
         * 设置为当前矩形的左上角点和右下角点，如果当前矩形是空矩形，则最后结果矩形为（0，0，x,y）
         */
        mRect.union(300,300)
      Log.e("cb","${mRect.toShortString()}")
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

    private fun interset() {
        //判断mRect1,mRect2是否相交,静态函数
//        val interset1_2 = Rect.intersects(mRect1, mRect2)
//        Log.e("cb", "${interset1_2}")
//        val interset1_3 = Rect.intersects(mRect1, mRect3)
//        Log.e("cb", "${interset1_3}")
        /**
         * 成员intersects函数不仅会判断相交结果，而且会把相交部分矩形赋值给当前Rect对象，如果不相交，当前对象值不变
         */
        val result = mRect1.intersects(190, 10, 250, 200)
        //相交赋值后被改变
        printResult(result, mRect1)
        val result2 = mRect1.intersect(210, 10, 250, 200)
        //不相交，值不改变
        printResult(result2, mRect1)
        val result3 = mRect1.intersect(190, 10, 250, 200)
        //相交，值被改变
        printResult(result3, mRect1)


    }





    private fun printResult(result: Boolean, rect: Rect) {
        Log.e("cb", "${rect.toShortString()} result:$result")
    }

}
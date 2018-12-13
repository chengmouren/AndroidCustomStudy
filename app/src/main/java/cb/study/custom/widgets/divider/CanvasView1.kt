package cb.study.custom.widgets.divider

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CanvasView1 @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = Color.RED //画笔颜色
        /**
         *   设置填充样式
         *   Style.FILL 仅填充内部
         *   Style.FILL_AND_STROKE 填充内部和描边
         *   Style.STROKE  仅描边
         */
        style = Paint.Style.STROKE
        strokeWidth = 5f //设置画笔宽度(单位：px)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        /**
         * 设置画布背景颜色
         */
        canvas?.drawRGB(255, 0, 255) //rgb设置，十进制，也可以传入十六禁制0xFF..等
//        canvas?.drawColor(0xFFFF00FF) //变种写法
//        canvas?.drawARGB(0xFF,0xFF,0xFF)//变种写法，可以设置透明度

//        lineApi(canvas)
//        pointApi(canvas)
//        rectApi(canvas)
//        circleApi(canvas)
        arcApi(canvas)
    }



    private fun lineApi(canvas: Canvas?) {
        /**
         * 画直线（paint设置Style不会影响，StrokeWidth才会影响线的粗细）
         * startx,starty 起始点x,y坐标
         * stopx,stopy 终点x,y坐标
         */

        //        canvas?.drawLine(100f,100f,200f,200f,paint)

        /**
         * 画多条直线构造参数一
         * pts格式为{x1,y1,x2,y2,x3,y3，....}
         * 不是连接线，而是对应的坐标点两两连成一条直线
         */
        val pts = floatArrayOf(10f, 10f, 100f, 100f, 200f, 200f, 400f, 400f)
        //        canvas?.drawLines(pts,paint)

        /**
         *画多条直线构造参数2
         *offset 集合中跳过的数值个数（不是点的个数，一个点有两个数值）
         * count 指数组里的个数（不是点的个数，一个点有两个数值）
         */
        //表示有四个点参与了绘制，(100f,100f,200f,200f)
        canvas?.drawLines(pts, 2, 4, paint)
    }

    private fun pointApi(canvas: Canvas?) {
        /**
         * 画点（paint设置Style不会影响，StrokeWidth才会影响线的粗细）
         * x,y 起始点x,y坐标
         */
        paint.strokeWidth =50f
        canvas?.drawPoint(100f,100f,paint)
        /**
         * 绘制多个点api与直线效果相同
         */
        val pts = floatArrayOf(10f, 10f, 100f, 100f, 200f, 200f, 400f, 400f)
//        canvas?.drawPoints(pts, paint)
        canvas?.drawPoints(pts,2,4,paint)
    }

    private fun rectApi(canvas: Canvas?) {
        //直接构造矩形
//        val rect=Rect(100,100,400,400)
        //间接构造矩形
        val rect=RectF()
        rect.set(100f,100f,400f,400f)
        /**
         * 绘制矩形
         */
//        canvas?.drawRect(100f,100f,400f,400f,paint)
//        canvas?.drawRect(rect,paint)
        /**
         * 绘制圆角矩形
         * rx:生成圆角的椭圆x轴半径
         * ry:生成圆角的y轴半径
         */
        canvas?.drawRoundRect(rect,20f,20f,paint)
    }


    private fun circleApi(canvas: Canvas?) {
        /**绘制圆
         *cx 圆心点x轴的坐标
         * cy圆心点的y轴坐标
         * radius圆的半径
         */
//        canvas?.drawCircle(190f,200f,150f,paint)

        val rect=RectF()
        rect.set(100f,50f,300f,600f)
        /**
         * 绘制椭圆，根据矩形形成，以矩形长度为椭圆的X轴，以矩形的宽为椭圆的Y轴
         */
        canvas?.drawOval(rect,paint)
    }

    private fun arcApi(canvas: Canvas?) {
        val rect=RectF(100f,100f,400f,400f)
        /**
         * oval:生成椭圆的矩形
         * startAngle：弧开始的角度，以X轴正方向为0
         * sweepAngle:弧持续的角度
         * useCenter:是否有弧的两边，true带两边，false只有一条边
         */
//        canvas?.drawArc(rect,0f,90f,true,paint)//带两边
//        canvas?.drawArc(rect,0f,90f,false,paint)//不带两边
        paint.style =Paint.Style.FILL
//                canvas?.drawArc(rect,0f,90f,true,paint)//带两边填充模式，会填充封闭区域
        canvas?.drawArc(rect,0f,90f,false,paint)//不带两边，只填充圆弧部分
    }



}
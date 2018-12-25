package cb.study.custom.widgets.divider

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.bumptech.glide.Glide.init

class PathView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
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

    private val mPath = Path()
    private var mRect: RectF = RectF(100f, 10f, 200f, 100f)

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
//        pathLineApi(canvas)
//        pathArcApi(canvas)
//        pathAddApi(canvas)
        fillTypeApi(canvas)
        clearPathApi(canvas)
    }



    private fun pathLineApi(canvas: Canvas?) {
        /**
         * 直线路径
         */
        mPath.apply {
            moveTo(10f, 10f)//起点
            lineTo(10f, 100f)//第一条直线的终点，第二条线的起始点
            lineTo(300f, 100f)//画第二条直线
            close()//闭环
        }
        //用路径画一个三角形
        canvas?.drawPath(mPath, paint)
    }

    private fun pathArcApi(canvas: Canvas?) {
        //弧线路径
        mPath.apply {
            moveTo(10f, 10f)
            /**
             * oval生成椭圆的矩形
             * startAngle弧开始的角度，以X轴正方向为0
             * sweepAngle 弧持续的角度
             */
//            arcTo(mRect,0f,90f) //这里弧会和起点连接起来
//            arcTo(mRect,0f,90f,true)//是否强制将弧的起点作为绘制起始位置
//            close()
            lineTo(100f, 50f)
            mRect.set(100f, 100f, 150f, 150f)
            mPath.addArc(mRect, 0f, 90f)
        }
        canvas?.drawPath(mPath, paint)
    }

    private fun pathAddApi(canvas: Canvas?) {
//        mPath.apply {
//            /**
//             * 创建两条大小相同的路径
//             *Direction.CCW)，路径逆向生成
//             */
////            addRect(mRect,Path.Direction.CCW)
//            mRect.set(290f,50f,480f,200f)
//            //顺向生成
//            addRect(mRect,Path.Direction.CW)
//        }
//        canvas?.drawPath(mPath,paint)
//        val text="kotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlin"
//        paint.textSize=35f
//
//        canvas?.drawTextOnPath(text,mPath,-30f,-30f,paint)

        /**
         * 添加圆角矩形路径
         * radii,4组八个值，两个值表示一个坐标点
         *
         */
        mPath.apply {
            mRect.set(290f, 50f, 480f, 200f)
            addRoundRect(mRect, floatArrayOf(10f, 15f, 20f, 25f, 30f, 35f, 40f, 45f), Path.Direction.CCW)
        }
        canvas?.drawPath(mPath, paint)

        //添加圆添加椭圆和弧形基本同理不解释
    }


    private fun fillTypeApi(canvas: Canvas?) {
        /**
         * Path的填充模式与Paint的填充模式不同,Path的填充模式是指填充Path的哪部分
         * Path.FillType表示填充模式，四个值
         */
        paint.style = Paint.Style.FILL
        mPath.apply {
            addRect(100f, 100f, 300f, 300f, Path.Direction.CW)
            addCircle(300f, 300f, 100f, Path.Direction.CW)
//            fillType=Path.FillType.WINDING//默认值，两个图形相交时，取相交部分显示
//            fillType=Path.FillType.EVEN_ODD//取path所在并不相交的区域
//            fillType = Path.FillType.INVERSE_WINDING//取path的外部区域
            fillType=Path.FillType.INVERSE_EVEN_ODD //取path的外部和相交区域
            canvas?.drawPath(mPath, paint)
        }
    }

    /**
     * 路径重置有两个api
     */
    private fun clearPathApi(canvas: Canvas?) {
        /**
         * rewind会清除FillType及所有的直线，曲线，点的数据等，但是会保留数据结构
         * 这样可以实现快速重用，提高一定性能（只有绘制重复的路径时候，这些数据结构才能被复用）
         * 不会清除内存，会清除FillType
         */
        mPath.rewind()
        /**
         * reset会类似新建一个path对象，所有数据空间都会被回收并重新分配，清除内存，但是不会清除FillType
         */
        mPath.reset()
    }

}
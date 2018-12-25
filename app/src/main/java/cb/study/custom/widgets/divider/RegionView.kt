package cb.study.custom.widgets.divider

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View


/**
 * Region不是用来绘图的，所以Region最重要的功能在区域相交中
 */
class RegionView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
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
    val region = Region(50, 50, 200, 500)

    val path = Path()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        drawRegion(canvas)
//        drawOvalPath(canvas)
//        unionApi(canvas)
        opApi(canvas)
    }

    private fun opApi(canvas: Canvas) {
        //op:用当前的region对象与指定的一个Rect对象或者Region对象执行相交操作，并将结果赋值给当前的Region对象，如果计算成功，
//        返回true,否则返回false
        val rect1 = Rect(100, 100, 400, 200)
        val rect2 = Rect(200, 0, 300, 300)
        canvas.drawRect(rect1, paint)
        canvas.drawRect(rect2, paint)
        val region1 = Region(rect1)
        val region2 = Region(rect2)

//        region1.op(region2, Region.Op.INTERSECT)       //取两个区域的交集
//        region1.op(region2, Region.Op.DIFFERENCE)       //最终区域为r1不同的区域,补集
//        region1.op(region2, Region.Op.REPLACE)       //替换,最终区域为r2
//        region1.op(region2, Region.Op.REVERSE_DIFFERENCE)       //最终区域为r2不同的区域,反转补集
//        region1.op(region2, Region.Op.UNION)       //最终区域为r1与r2组合在一起的区域
//        region1.op(region2, Region.Op.XOR)       //最终区域取两个区域相交以外的区域

        Log.e("cb", "${region1.isComplex}") //判断该区域是否一个矩阵
        val r = region1.bounds //返回一个矩形的边界
        val path = region1.boundaryPath//返回包裹当前路径的最小矩形
//        region1.contains(10,10)//是否包含某个坐标点
//        region1.quickContains(rect1)//是否包含某个矩形
//        region1.quickReject(rect1)//是否没有和指定矩形相交
//        region1.quickReject(region2)//是否没有和指定区域相交
        /**
         * 将region对象平移x轴dx距离,y轴平移dy距离,并将结果赋予当前region.
         * 重载函数多出个dst,就回赋予dst这个区域
         */
        region1.translate(100,200)
        canvas.drawRect(r, paint)
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL
        drawRegion(canvas, region1)

    }

    private fun unionApi(canvas: Canvas) {
        val r = Region(10, 10, 200, 100)

        r.union(Rect(10, 10, 50, 300))
        drawRegion(canvas, r)
    }

    private fun drawOvalPath(canvas: Canvas) {
        //添加椭圆路径
        path.addOval(50f, 50f, 200f, 500f, Path.Direction.CCW)
        region.setPath(path, region)
        drawRegion(canvas, region)
    }

    private fun drawRegion(canvas: Canvas, r: Region) {
        //根据区域构建对应的矩形集
        val iter = RegionIterator(r)
        val r = Rect()
//        region.apply {
//            setEmpty() //置为空
//            set(r) //用矩形区域代替原来的区域
//            set(this)//利用新的区域来替换原来区域
//            set(50,50,200,100)//根据矩形的两个角点构造出来矩形区域替换原来区域
//            /**
//             * path:用来构造区域的路径
//             * 与前面path所构成的路径取交集，并将该交集置为最终区域
//             */
//            setPath(path,region)//根据路径区域与某区域的交集构造出新区域
//        }

        //获取下一个矩形，保存在r中
        while (iter.next(r)) {
            canvas.drawRect(r, paint)
        }
    }

}
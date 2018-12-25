package cb.study.custom.widgets.divider

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Canvas画布原始状态以左上角为原点,X轴右为正方向,y轴向下是正方向
 *
 * 把绘制图形的屏幕认为就是canvas是错误的理解,canvas实质上相当于是一个透明的图层,每次绘制的
 * 时候都会产生一个透明图层,绘制完成后覆盖在屏幕上显示.
 *在canvas图层与屏幕合成时,超出屏幕范围的图像不会被显示.
 */
class CanvasView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {
    init {
        setLayerType(LAYER_TYPE_SOFTWARE,null)//关闭硬件加速

    }
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

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        translateApi(canvas)
//        rotateApi(canvas)
//        scaleApi(canvas)
//        skewApi(canvas)
//        clipApi(canvas)
        saveAndRestore(canvas)
    }

    private fun saveAndRestore(canvas: Canvas) {
        /**
         * 画布的保存与恢复
         * save() 每次调用都会保存画布当前状态，然后放入栈中,返回为int值，int为在栈的位置
         * restore() 每次都会把栈顶的画布取出来并按照之前保存状态恢复，然后在画布上作画
         */
        val c1 = canvas.save()//保存整个画布屏幕
        canvas.clipRect(100,100,800,800)//裁剪
        canvas.drawColor(Color.GREEN)
        val c2=  canvas.save()
        canvas.clipRect(Rect(200,200,700,700))
        canvas.drawColor(Color.BLUE)
        val c3 = canvas.save()

//        canvas.restore()//出栈

        canvas.restoreToCount(c2)//使保存的画布一直出栈，直到指定索引的画布出栈为止，指定索引的画布作为当前画布
        canvas.drawColor(Color.CYAN)

    }

    private fun clipApi(canvas: Canvas) {
        /**
         * 裁剪画布是指利用clip系列函数，通过Rect,Path，Region取交，并，差等集合运算来获得最新的h画布形状。除调用save(),
         * restore()函数外，这个操作不可逆，一旦被裁剪，不可恢复
         */
        canvas.drawColor(Color.RED)
//        canvas.clipRect(Rect(100,100,200,200)) //画布矩形裁剪
        //其余路径，区域等同，不解释
        canvas.drawColor(Color.GREEN)

    }

    private fun skewApi(canvas: Canvas) {
        /**
         * 扭曲
         * sx将画布在x轴上倾斜相应的角度，sx为倾斜的角度正切值
         * sy将画布在y轴上倾斜相应的角度，sy为倾斜角度的正切值
         */
        val rect =Rect(10,10,200,100)
        canvas.drawRect(rect,paint)
        canvas.skew(1.732f,0f)//x轴倾斜60度，y轴不变
        paint.color=Color.GREEN
        canvas.drawRect(rect,paint)
    }

    private fun scaleApi(canvas: Canvas) {
        /**
         * 变更坐标轴密度，
         * sx:水平方向的伸缩比例，假设原坐标轴比例为n,不变为1，变更后X轴密度为n*sx，所以sx小数是缩小，sy为整数表示为大
         * sy:垂直方向的伸缩比例，同上。
         */
        val rect =Rect(10,10,200,100)
        canvas.drawRect(rect,paint)
//        canvas.scale(0.5f,1f)//缩放比例
        canvas.scale(0.5f,1f,100f,100f)//可以设置缩放中心位置
        paint.color=Color.GREEN
        canvas.drawRect(rect,paint)
    }

    private fun rotateApi(canvas: Canvas) {
        /**
         * 画布的选择默认是围绕坐标点来进行的，因为旋转了画布，所以绘制的图形看起来也是旋转的。
         *
         */
        val rect =Rect(300,10,500,100)
        canvas.drawRect(rect,paint)

//        canvas.rotate(30f)//顺时针旋转画布，正为顺时针，负为逆时针，中心点是原点（0，0）
//        canvas.rotate(30f,100f,100f) //指定旋转中心点
        paint.color=Color.GREEN
        canvas.drawRect(rect,paint)
    }

    private fun translateApi(canvas: Canvas) {
        /**
         * 画布左上角为原点(0,0),所以当平移画布后,坐标系也同样回被平移,被平移后的画布左上角点是新画布的坐标原点
         * dx,水平方向平移的距离,正为右,负为左
         * dy,垂直方向平移距离,正数为下,负数为上
         */

        canvas.drawRect(0f, 0f, 400f, 200f, paint)
        canvas.translate(100f, 100f)
        canvas.drawRect(0f, 0f, 400f, 200f, paint)
    }
}
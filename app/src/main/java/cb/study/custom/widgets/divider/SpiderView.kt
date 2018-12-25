package cb.study.custom.widgets.divider

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.blue
import androidx.core.graphics.green
import com.bumptech.glide.Glide.init

class SpiderView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    private val radarPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.RED
    }

    private val vaulePaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.BLUE
    }

    private var raduis = 0f //网格最大半径
    private var centerX = 0 //中心x
    private var centerY = 0 //中心y

    private val data = doubleArrayOf(2.toDouble(),5.toDouble(),1.toDouble(),6.toDouble(),4.toDouble(),5.toDouble())

    /**
     * 控件大小发生变化时候，都会通过onSizeChanged()函数通知告诉我们当前控件的大小
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        //蛛网总大小占据当前控件比大小的百分90
        raduis = Math.min(h, w) / 2 * 0.9f
        centerX = w / 2
        centerY = h / 2
        postInvalidate()
        super.onSizeChanged(w, h, oldw, oldh)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制蜘蛛网格
        drawPolygon(canvas)
        //绘制网格中线
        drawLines(canvas)
        //绘制数据点
        drawRegion(canvas)
    }

    private fun drawRegion(canvas: Canvas) {
        val path =Path()
        vaulePaint.alpha=127
        for (i in 0 until 6){
            val percent =data[i]/6
            val x =(centerX+raduis*Math.cos(Math.PI*2/6*i)*percent).toFloat()
            val y =(centerY+raduis*Math.sin(Math.PI*2/6*i)*percent).toFloat()
            if (i ==0){
                path.moveTo(x,centerY.toFloat())
            }else{
                path.lineTo(x,y)
            }
            canvas.drawCircle(x,y,10f,vaulePaint)
        }
        vaulePaint.style=Paint.Style.FILL_AND_STROKE
        canvas.drawPath(path,vaulePaint)
    }

    private fun drawLines(canvas: Canvas) {
        val path =Path()
        for (i in 0..6){
            path.reset()
            path.moveTo(centerX.toFloat(),centerY.toFloat())
            val x =(centerX+raduis*Math.cos(Math.PI*2/6*i)).toFloat()
            val y =(centerY+raduis*Math.sin(Math.PI*2/6*i)).toFloat()
            path.lineTo(x,y)
            canvas.drawPath(path,radarPaint)
        }
    }

    private fun drawPolygon(canvas: Canvas) {
        val path = Path()
        val r = raduis / 6 //蛛丝之间的间距
        for (i in 1..6) { //中心点不用绘制
            val curR = r * i //当前半径
            path.reset()
            for (j in 0 until 6){
                if (j==0){
                    path.moveTo(centerX+curR,centerY.toFloat())
                }else{
                    //根据半径计算出蜘蛛丝上每个点的坐标
                    val x =(centerX+curR*Math.cos(Math.PI*2/6*j)).toFloat()
                    val y =(centerY+curR*Math.sin(Math.PI*2/6*j)).toFloat()
                    path.lineTo(x, y)
                }
            }
            path.close()
            canvas.drawPath(path,radarPaint)
        }
    }

}
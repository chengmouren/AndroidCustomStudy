package cb.study.custom.widgets.divider

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import cb.study.custom.R

class CustomCircleView@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr){

    init {
        setLayerType(LAYER_TYPE_SOFTWARE,null)
    }
    private val mBitmap =BitmapFactory.decodeResource(context.resources,R.drawable.ic_navigation)
    private val mPaint=Paint().apply {

    }

    private val mPath =Path()

    init {
        val with =mBitmap.width
        val hegiht =mBitmap.height
        //根据图文件大小绘制圆形路径
        mPath.addCircle(with/2f,hegiht/2f,with/2f,Path.Direction.CCW)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(100f,200f)
        canvas.drawColor(Color.BLACK)
        canvas.save()//保存画布
        canvas.clipPath(mPath)
        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(mBitmap,0f,0f,mPaint)
        canvas.restore()//复原画布
    }
}
package cb.study.custom.ui.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import cb.study.custom.R
import kotlinx.android.synthetic.main.divider_fragment.*

class DividerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.divider_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView =TextView(activity)
        layout.addView(textView.apply {
            text = "新添加的"
        },-1)

        Log.e("cb",(layout[1] as TextView).text.toString())

    }

    companion object {
        fun newInstance() = DividerFragment()
    }
}

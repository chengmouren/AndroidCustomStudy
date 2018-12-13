package cb.study.custom.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cb.study.custom.R

class PaintFragment : Fragment() {

    companion object {
        fun newInstance() = PaintFragment()
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.draw_fragment, container, false)
    }

}

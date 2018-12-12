package cb.study.custom.ui.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cb.study.custom.R

class DividerFragment : Fragment() {

    companion object {
        fun newInstance() = DividerFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.divider_fragment, container, false)
    }
}

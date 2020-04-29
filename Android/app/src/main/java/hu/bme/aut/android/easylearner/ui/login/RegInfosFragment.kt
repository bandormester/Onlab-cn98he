package hu.bme.aut.android.easylearner.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.android.easylearner.R
import kotlinx.android.synthetic.main.fragment_reg_infos.*

class RegInfosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reg_infos, container, false)
    }

    override fun onStart() {
        super.onStart()

        btRegNext.setOnClickListener {
            (activity as RegisterActivity).toPhotoFragment()
        }
    }
}
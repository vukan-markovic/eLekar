package vukan.com.euprava.ui.examination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import vukan.com.euprava.R

class SchedulingExaminationFragment : Fragment() {

    private lateinit var schedulingExaminationViewModel: SchedulingExaminationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        schedulingExaminationViewModel =
            ViewModelProvider(this).get(SchedulingExaminationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_scheduling_examination, container, false)

        schedulingExaminationViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}
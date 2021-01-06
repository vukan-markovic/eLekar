package vukan.com.euprava.ui.examination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import vukan.com.euprava.R

class SchedulingExaminationFragment : Fragment() {
    private lateinit var schedulingExaminationViewModel: SchedulingExaminationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scheduling_examination, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        schedulingExaminationViewModel =
            ViewModelProvider(this).get(SchedulingExaminationViewModel::class.java)

        schedulingExaminationViewModel.text.observe(viewLifecycleOwner, {

        })
    }
}
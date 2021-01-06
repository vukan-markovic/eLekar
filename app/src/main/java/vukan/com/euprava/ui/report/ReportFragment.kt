package vukan.com.euprava.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import vukan.com.euprava.R

class ReportFragment : Fragment() {
    private lateinit var reportViewModel: ReportViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reportViewModel = ViewModelProvider(this).get(ReportViewModel::class.java)

        reportViewModel.text.observe(viewLifecycleOwner, {

        })
    }
}
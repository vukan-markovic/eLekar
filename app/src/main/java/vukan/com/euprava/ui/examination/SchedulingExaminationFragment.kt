package vukan.com.euprava.ui.examination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import vukan.com.euprava.databinding.FragmentSchedulingExaminationBinding

class SchedulingExaminationFragment : Fragment() {
    private val schedulingExaminationViewModel by viewModels<SchedulingExaminationViewModel>()
    private lateinit var binding: FragmentSchedulingExaminationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSchedulingExaminationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        schedulingExaminationViewModel.text.observe(viewLifecycleOwner, {

        })
    }
}
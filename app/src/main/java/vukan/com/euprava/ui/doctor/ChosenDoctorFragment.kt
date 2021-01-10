package vukan.com.euprava.ui.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import vukan.com.euprava.databinding.FragmentChosenDoctorBinding

class ChosenDoctorFragment : Fragment() {
    private val chosenDoctorViewModel by viewModels<ChosenDoctorViewModel>()
    private lateinit var binding: FragmentChosenDoctorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChosenDoctorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chosenDoctorViewModel.getDoctors("")
    }
}
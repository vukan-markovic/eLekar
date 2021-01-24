package vukan.com.euprava.ui.doctor_email

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import vukan.com.euprava.databinding.FragmentDoctorEmailBinding

class DoctorEmailFragment : Fragment() {
    private val doctorEmailViewModel by viewModels<DoctorEmailViewModel>()
    private lateinit var binding: FragmentDoctorEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDoctorEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.send.setOnClickListener {
            doctorEmailViewModel.sendMessage("", "")
        }
    }
}
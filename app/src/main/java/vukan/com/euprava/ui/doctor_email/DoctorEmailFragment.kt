package vukan.com.euprava.ui.doctor_email

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import vukan.com.euprava.DrawerNavigation
import vukan.com.euprava.R
import vukan.com.euprava.ToastListener
import vukan.com.euprava.afterTextChanged
import vukan.com.euprava.databinding.FragmentDoctorEmailBinding

class DoctorEmailFragment : Fragment() {
    private val doctorEmailViewModel by viewModels<DoctorEmailViewModel>()
    private lateinit var binding: FragmentDoctorEmailBinding
    private var isDataValid: Boolean = false
    private var doctorName: String = ""
    private var lbo: String = ""

    override fun onResume() {
        super.onResume()

        if (isDataValid) {
            binding.send.isEnabled = true
            binding.send.isClickable = true
        }

        binding.messageText.afterTextChanged {
            doctorEmailViewModel.checkEmail(binding.messageText.text.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDoctorEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as DrawerNavigation).setDrawerEnabled(true)

        doctorEmailViewModel.getDoctor(DoctorEmailFragmentArgs.fromBundle(requireArguments()).doctorId)
            .observe(viewLifecycleOwner) { doctor ->
                doctorName = getString(R.string.doctor_name_surname, doctor.name, doctor.surname)
                binding.doctorName.text = doctorName
            }

        doctorEmailViewModel.getUser().observe(viewLifecycleOwner) { user ->
            lbo = user.lbo
        }

        doctorEmailViewModel.formState.observe(
            viewLifecycleOwner,
            Observer {
                val state = it ?: return@Observer
                isDataValid = state.isDataValid
                binding.send.isEnabled = isDataValid
                binding.send.isClickable = isDataValid
            })

        binding.send.setOnClickListener {
            doctorEmailViewModel.sendMessage(doctorName, lbo, binding.messageText.text.toString())
            (activity as ToastListener).show("Poruka je poslata!")
            findNavController().navigate(
                DoctorEmailFragmentDirections.actionNavEmailDoctorToNavHome()
            )
        }
    }
}
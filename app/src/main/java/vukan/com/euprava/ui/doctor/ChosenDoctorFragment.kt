package vukan.com.euprava.ui.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import vukan.com.euprava.R

class ChosenDoctorFragment : Fragment() {

    private lateinit var chosenDoctorViewModel: ChosenDoctorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chosenDoctorViewModel =
            ViewModelProvider(this).get(ChosenDoctorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chosen_doctor, container, false)

        chosenDoctorViewModel.text.observe(viewLifecycleOwner, {

        })
        return root
    }
}
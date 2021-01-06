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
        return inflater.inflate(R.layout.fragment_chosen_doctor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chosenDoctorViewModel = ViewModelProvider(this).get(ChosenDoctorViewModel::class.java)

        chosenDoctorViewModel.text.observe(viewLifecycleOwner, {

        })
    }
}
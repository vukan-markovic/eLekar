package vukan.com.euprava.ui.examination

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.Timestamp
import vukan.com.euprava.DrawerNavigation
import vukan.com.euprava.R
import vukan.com.euprava.ToastListener
import vukan.com.euprava.databinding.FragmentSchedulingExaminationBinding
import java.text.SimpleDateFormat
import java.util.*


class SchedulingExaminationFragment : Fragment() {
    private val schedulingExaminationViewModel by viewModels<SchedulingExaminationViewModel>()
    private lateinit var binding: FragmentSchedulingExaminationBinding
    private var isDataValid: Boolean = false
    private var sfdDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    private var sfdTime = SimpleDateFormat("HH:mm", Locale.getDefault())
    private var doctorName: String = ""

    companion object {
        private val calendar = Calendar.getInstance()
        var dateTime: Timestamp = Timestamp(Date())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSchedulingExaminationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        if (isDataValid) {
            binding.confirm.isEnabled = true
            binding.confirm.isClickable = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as DrawerNavigation).setDrawerEnabled(true)
        val doctorID = SchedulingExaminationFragmentArgs.fromBundle(requireArguments()).doctorId
        schedulingExaminationViewModel.getDoctorExaminations(doctorID)

        schedulingExaminationViewModel.getDoctor(doctorID).observe(viewLifecycleOwner) { doctor ->
            doctorName = getString(R.string.doctor_name_surname, doctor.name, doctor.surname)
            binding.doctorName.text = doctorName
        }

        schedulingExaminationViewModel.formState.observe(
            viewLifecycleOwner,
            Observer {
                val state = it ?: return@Observer
                isDataValid = state.isDataValid
                binding.confirm.isEnabled = isDataValid
                binding.confirm.isClickable = isDataValid

                if (state.dateError != null && state.dateError != 0) {
                    (activity as ToastListener).show(getString(state.dateError))
                    binding.timePicker.isEnabled = false
                    binding.timePicker.isClickable = false
                } else {
                    binding.examinationDate.text = sfdDate.format(dateTime.toDate())
                    binding.timePicker.isEnabled = true
                    binding.timePicker.isClickable = true
                }

                if (state.timeError != null && state.timeError != 0)
                    (activity as ToastListener).show(getString(state.timeError))
                else binding.examinationTime.text = sfdTime.format(dateTime.toDate())
            })

        val date = OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            dateTime = Timestamp(calendar.time)
            schedulingExaminationViewModel.checkDate(calendar.get(Calendar.DAY_OF_WEEK))
        }

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            date,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        val calendarMin = Calendar.getInstance()

        calendarMin.set(
            Calendar.DAY_OF_MONTH,
            calendarMin.get(Calendar.DAY_OF_MONTH) + 1
        )

        datePickerDialog.datePicker.minDate = calendarMin.timeInMillis
        val calendarMax = Calendar.getInstance()

        calendarMax.set(
            Calendar.DAY_OF_MONTH,
            calendarMax.getActualMaximum(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.maxDate = calendarMax.timeInMillis

        binding.datePicker.setOnClickListener {
            datePickerDialog.show()
        }

        binding.timePicker.setOnClickListener {
            val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
            val minute: Int = calendar.get(Calendar.MINUTE)
            val timePicker = ExaminationTimer(
                context, { _, selectedHour, selectedMinute ->
                    calendar.set(Calendar.HOUR, selectedHour)
                    calendar.set(Calendar.MINUTE, selectedMinute)
                    dateTime = Timestamp(calendar.time)
                    schedulingExaminationViewModel.checkTime(dateTime.toString())
                }, hour, minute, true
            )

            timePicker.setTitle("Izaberite vreme")
            timePicker.show()
        }

        binding.confirm.setOnClickListener {
            schedulingExaminationViewModel.addExamination(doctorID, doctorName, dateTime)
            (activity as ToastListener).show(getString(R.string.examination_added))
            findNavController().navigate(
                SchedulingExaminationFragmentDirections.actionNavSchedulingExaminationToNavHome()
            )
        }
    }
}
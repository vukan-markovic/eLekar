package vukan.com.euprava.ui.examination

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.Timestamp
import vukan.com.euprava.databinding.FragmentSchedulingExaminationBinding
import java.util.*

class SchedulingExaminationFragment : Fragment() {
    private val schedulingExaminationViewModel by viewModels<SchedulingExaminationViewModel>()
    private lateinit var binding: FragmentSchedulingExaminationBinding
    private var isDataValid: Boolean = false

    companion object {
        private val calendar = Calendar.getInstance()
        lateinit var dateTime: Timestamp

        fun checkDate(day: Int) {

        }
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

        schedulingExaminationViewModel.formState.observe(
            viewLifecycleOwner,
            Observer {
                val state = it ?: return@Observer
                isDataValid = state.isDataValid
                binding.confirm.isEnabled = isDataValid
                binding.confirm.isClickable = isDataValid

                if (state.dateError != null && state.dateError != 0)
                    Toast.makeText(context, getString(state.dateError), Toast.LENGTH_SHORT).show()
            })

        binding.datePicker.setOnClickListener {
            val dialogFragment: DialogFragment = DatePickerFragment()
            dialogFragment.show(parentFragmentManager, "date")
        }

        binding.timePicker.setOnClickListener {
            val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
            val minute: Int = calendar.get(Calendar.MINUTE)
            val timePicker = ExaminationTimer(
                context, { _, selectedHour, selectedMinute ->
                    calendar.set(Calendar.HOUR, selectedHour)
                    calendar.set(Calendar.MINUTE, selectedMinute)
                    dateTime = Timestamp(calendar.time)
                    schedulingExaminationViewModel.setTime()
                }, hour, minute, true
            ) //Yes

            timePicker.setTitle("Izaberite vreme")
            timePicker.show()
        }

        binding.confirm.setOnClickListener {
            schedulingExaminationViewModel.addExamination(
                SchedulingExaminationFragmentArgs.fromBundle(requireArguments()).doctorId,
                dateTime
            )

            findNavController().navigate(
                SchedulingExaminationFragmentDirections.actionNavSchedulingExaminationToNavHome()
            )
        }
    }

    class DatePickerFragment : DialogFragment(),
        OnDateSetListener {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                this,
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
            return datePickerDialog
        }

        override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            checkDate(calendar.get(Calendar.DAY_OF_WEEK))
        }
    }
}
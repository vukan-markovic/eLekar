package vukan.com.euprava.ui.examination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import vukan.com.euprava.R
import vukan.com.euprava.data.Repository
import vukan.com.euprava.data.model.Doctor
import vukan.com.euprava.data.model.Examination
import java.text.SimpleDateFormat
import java.util.*

class SchedulingExaminationViewModel : ViewModel() {
    private val repo: Repository = Repository()
    private val _form = MutableLiveData<ExaminationState>()
    val formState: LiveData<ExaminationState> = _form
    private var timeError: Int? = 0
    private var dateError: Int? = 0
    private var sfdDateTime = SimpleDateFormat("dd-MM-yyyy, HH:mm", Locale.getDefault())

    fun checkDate(day: Int) {
        if (day == 1 || day == 7) {
            dateError = R.string.invalid_date
            setFormState()
        } else {
            dateError = null
            setFormState()
            isDataValid()
        }
    }

    fun checkTime(time: String, examinations: List<Examination>) {
        if (!isTermFree(time, examinations)) {
            timeError = R.string.invalid_term
            setFormState()
        } else {
            timeError = null
            setFormState()
            isDataValid()
        }
    }

    private fun isTermFree(time: String, examinations: List<Examination>): Boolean {
        examinations.forEach {
            val date = it.dateTime?.toDate()
            if (date != null && sfdDateTime.format(date) == time) return false
        }

        return true
    }

    private fun setFormState() {
        _form.value =
            ExaminationState(timeError = timeError, dateError = dateError)
    }

    private fun isDataValid() {
        if (timeError == null && dateError == null)
            _form.value = ExaminationState(isDataValid = true)
    }

    fun addExamination(doctorID: String, doctorName: String, dateTime: Timestamp) {
        repo.addExamination(doctorID, doctorName, dateTime)
    }

    fun getDoctor(doctorID: String): MutableLiveData<Doctor> {
        return repo.getDoctor(doctorID)
    }

    fun getDoctorExaminations(doctorID: String): MutableLiveData<List<Examination>> {
        return repo.getDoctorExaminations(doctorID)
    }
}
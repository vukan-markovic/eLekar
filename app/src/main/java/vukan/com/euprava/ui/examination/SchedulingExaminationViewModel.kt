package vukan.com.euprava.ui.examination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import vukan.com.euprava.R
import vukan.com.euprava.data.Repository
import vukan.com.euprava.data.model.Examination

class SchedulingExaminationViewModel : ViewModel() {
    private val repo: Repository = Repository()
    private val _form = MutableLiveData<ExaminationState>()
    private var examinations: ArrayList<Examination>? = null
    val formState: LiveData<ExaminationState> = _form
    private var timeError: Int? = 0
    private var dateError: Int? = 0

    fun checkDate(day: Int) {
        if (day == 0 || day == 6) {
            dateError = R.string.invalid_date
            setFormState()
        } else {
            dateError = null
            setFormState()
            isDataValid()
        }
    }

    fun checkTime(time: String) {
        if (!isTermFree(time)) {
            timeError = R.string.invalid_term
            setFormState()
        } else {
            timeError = null
            setFormState()
            isDataValid()
        }
    }

    private fun isTermFree(time: String): Boolean {
        examinations?.forEach {
            if (it.dateTime.toString() == time) return false
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

    fun addExamination(doctorID: String, dateTime: Timestamp) {
        repo.addExamination(doctorID, dateTime)
    }

    fun getDoctorExaminations(doctorID: String) {
        examinations = repo.getDoctorExaminations(doctorID)
    }
}
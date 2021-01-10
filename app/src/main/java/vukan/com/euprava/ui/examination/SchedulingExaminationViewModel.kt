package vukan.com.euprava.ui.examination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import vukan.com.euprava.R
import vukan.com.euprava.data.Repository

class SchedulingExaminationViewModel : ViewModel() {
    private val repo: Repository = Repository()
    private val _form = MutableLiveData<ExaminationState>()
    val formState: LiveData<ExaminationState> = _form
    private var timeError: Int? = 0
    private var dateError: Int? = 0

    fun setTime() {
        timeError = null
        setFormState()
        isDataValid()
    }

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
}
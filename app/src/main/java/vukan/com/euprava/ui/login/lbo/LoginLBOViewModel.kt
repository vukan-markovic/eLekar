package vukan.com.euprava.ui.login.lbo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vukan.com.euprava.R

class LoginLBOViewModel : ViewModel() {
    private val _form = MutableLiveData<LBOState>()
    val formState: LiveData<LBOState> = _form
    private var lboError: Int? = 0

    fun checkLBO(lbo: String) {
        if (lbo.isBlank()) {
            lboError = R.string.invalid_lbo
            setFormState()
        } else {
            lboError = null
            setFormState()
            isDataValid()
        }
    }

    private fun setFormState() {
        _form.value =
            LBOState(lboError = lboError)
    }

    private fun isDataValid() {
        if (lboError == null)
            _form.value = LBOState(isDataValid = true)
    }
}
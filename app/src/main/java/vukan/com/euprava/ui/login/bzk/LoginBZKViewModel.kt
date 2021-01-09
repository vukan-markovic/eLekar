package vukan.com.euprava.ui.login.bzk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vukan.com.euprava.R

class LoginBZKViewModel : ViewModel() {
    private val _form = MutableLiveData<BZKState>()
    val formState: LiveData<BZKState> = _form
    private var bzkError: Int? = 0

    fun checkBZK(name: String) {
        if (name.isBlank()) {
            bzkError = R.string.invalid_bzk
            setFormState()
        } else {
            bzkError = null
            setFormState()
            isDataValid()
        }
    }

    private fun setFormState() {
        _form.value =
            BZKState(bzkError = bzkError)
    }

    private fun isDataValid() {
        if (bzkError == null)
            _form.value = BZKState(isDataValid = true)
    }
}
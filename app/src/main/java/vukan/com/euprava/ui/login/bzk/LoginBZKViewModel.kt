package vukan.com.euprava.ui.login.bzk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vukan.com.euprava.R

class LoginBZKViewModel : ViewModel() {
    private val _form = MutableLiveData<BZKState>()
    val formState: LiveData<BZKState> = _form
    private var bzkError: Int? = 0

    fun checkBZK(bzk: String) {
        when {
            bzk.isBlank() -> {
                bzkError = R.string.invalid_bzk
                setFormState()
            }
            bzk.length != 11 -> bzkError = 0
            else -> {
                bzkError = null
                setFormState()
                isDataValid()
            }
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
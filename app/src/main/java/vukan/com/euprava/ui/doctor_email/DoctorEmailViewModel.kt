package vukan.com.euprava.ui.doctor_email

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import vukan.com.euprava.data.Repository
import vukan.com.euprava.data.model.Doctor
import vukan.com.euprava.data.model.User

class DoctorEmailViewModel : ViewModel() {
    private val repo: Repository = Repository()
    private val _form = MutableLiveData<EmailState>()
    val formState: LiveData<EmailState> = _form
    private var emailError: Int? = 0

    fun checkEmail(email: String) {
        if (email.isBlank()) {
            emailError = 1
            setFormState()
        } else {
            emailError = null
            setFormState()
            isDataValid()
        }
    }

    private fun setFormState() {
        _form.value =
            EmailState(emailError = emailError)
    }

    private fun isDataValid() {
        if (emailError == null)
            _form.value = EmailState(isDataValid = true)
    }

    fun sendMessage(doctorName: String, lbo: String, message: String) {
        repo.sendMessage(
            doctorName,
            lbo,
            message,
            FirebaseAuth.getInstance().currentUser?.email.toString()
        )
    }

    fun getUser(): MutableLiveData<User> {
        return repo.getUser()
    }

    fun getDoctor(doctorID: String): MutableLiveData<Doctor> {
        return repo.getDoctor(doctorID)
    }
}
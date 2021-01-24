package vukan.com.euprava.ui.doctor_email

import androidx.lifecycle.ViewModel
import vukan.com.euprava.data.Repository

class DoctorEmailViewModel : ViewModel() {
    private val repo: Repository = Repository()

    fun sendMessage(doctorID: String, message: String) {

    }
}
package vukan.com.euprava.ui.doctor

import androidx.lifecycle.ViewModel
import vukan.com.euprava.data.Repository

class ChosenDoctorViewModel : ViewModel() {
    private val repo: Repository = Repository()

    fun getDoctors(userID: String) {
        repo.getDoctors(userID)
    }
}
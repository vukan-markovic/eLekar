package vukan.com.euprava.ui.doctor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vukan.com.euprava.data.Repository
import vukan.com.euprava.data.model.Doctor
import vukan.com.euprava.data.model.Institution
import vukan.com.euprava.data.model.User

class ChosenDoctorViewModel : ViewModel() {
    private val repo: Repository = Repository()

    fun getDoctors(lboBzk: Array<String>): MutableLiveData<List<Doctor>> {
        return repo.getDoctors(lboBzk)
    }

    fun getUser(): MutableLiveData<User> {
        return repo.getUser()
    }

    fun getInstitution(institutionID: String): MutableLiveData<Institution> {
        return repo.getInstitution(institutionID)
    }
}
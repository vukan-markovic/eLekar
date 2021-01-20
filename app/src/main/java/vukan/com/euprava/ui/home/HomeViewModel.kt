package vukan.com.euprava.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vukan.com.euprava.data.Repository
import vukan.com.euprava.data.model.Examination
import vukan.com.euprava.data.model.User

class HomeViewModel : ViewModel() {
    private val repo: Repository = Repository()

    fun getExaminations(): MutableLiveData<List<Examination>> {
        return repo.getExaminations()
    }

    fun getUser(): MutableLiveData<User> {
        return repo.getUser()
    }

    fun cancelExamination(examinationID: String) {
        repo.cancelExamination(examinationID)
    }
}
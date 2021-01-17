package vukan.com.euprava.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vukan.com.euprava.data.Repository
import vukan.com.euprava.data.model.Examination

class HomeViewModel : ViewModel() {
    private val repo: Repository = Repository()

    fun getExaminations(): MutableLiveData<List<Examination>> {
        return repo.getExaminations()
    }

    fun cancelExamination(examinationID: String) {
        repo.cancelExamination(examinationID)
    }
}
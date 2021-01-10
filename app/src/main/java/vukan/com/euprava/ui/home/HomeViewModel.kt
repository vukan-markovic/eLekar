package vukan.com.euprava.ui.home

import androidx.lifecycle.ViewModel
import vukan.com.euprava.data.Repository

class HomeViewModel : ViewModel() {
    private val repo: Repository = Repository()

    fun getExaminations() {
        repo.getExaminations()
    }

    fun cancelExamination(examinationID: String) {
        repo.cancelExamination(examinationID)
    }
}
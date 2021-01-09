package vukan.com.euprava.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vukan.com.euprava.data.Repository

class HomeViewModel : ViewModel() {
    private val repo: Repository = Repository()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getExaminations() {
        repo.getExaminations()
    }

    fun cancelExamination() {
        repo.cancelExamination()
    }
}
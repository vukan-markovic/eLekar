package vukan.com.euprava.ui.login.firebase

import androidx.lifecycle.ViewModel
import vukan.com.euprava.data.Repository

class LoginFirebaseViewModel : ViewModel() {
    private val repo: Repository = Repository()

    fun addUser(lboBzk: Array<String>) {
        repo.addUser(lboBzk)
    }
}
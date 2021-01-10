package vukan.com.euprava.ui.login.firebase

import androidx.lifecycle.ViewModel
import vukan.com.euprava.data.Repository

class LoginFirebaseViewModel : ViewModel() {
    private val repo: Repository = Repository()

    fun addUser() {
        repo.addUser()
    }

    fun deleteUser(userID: String) {
        repo.deleteUser(userID)
    }
}
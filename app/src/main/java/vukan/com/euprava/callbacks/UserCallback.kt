package vukan.com.euprava.callbacks

import vukan.com.euprava.data.model.User

interface UserCallback {
    fun onCallback(user: User)
}
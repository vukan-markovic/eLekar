package vukan.com.euprava.data.model

data class User(
    val userID: String,
    var name: String,
    val surname: String,
    val lbo: String,
    val bzk: String
)
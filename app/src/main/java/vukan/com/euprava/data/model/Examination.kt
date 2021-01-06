package vukan.com.euprava.data.model

import java.util.*

data class Examination(
    val dateTime: Date,
    val doctorID: String,
    val userID: String,
    val status: Boolean
)
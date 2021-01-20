package vukan.com.euprava.data.model

import com.google.firebase.Timestamp

data class Examination(
    val examinationID: String,
    val dateTime: Timestamp?,
    val doctorName: String,
    val doctorID: String,
    val userID: String
)
package vukan.com.euprava.data.model

import java.util.*

data class Examination(
    val examinationID: String,
    val dateTime: Date,
    val additionalNotes: String,
    val status: Boolean,
    val doctorID: String,
    val userID: String
)
package vukan.com.euprava.data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import vukan.com.euprava.data.model.Examination


class Repository {
    private val database: Database = Database()
    private val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private val examinations: MutableLiveData<List<Examination>> = MutableLiveData()

    fun addUser() {
        database.addUser(user)
    }

    fun deleteUser(userID: String) {
        database.deleteUser(userID)
    }

    fun addExamination(doctorID: String, dateTime: Timestamp) {
        database.addExamination(doctorID, dateTime)

    }

    fun getExaminations(): MutableLiveData<List<Examination>> {
//        database.getExaminations(examinations::setValue)
        return examinations
    }

    fun getDoctors(userID: String) {
        database.getDoctors(userID)
    }

    fun getInstitution(institutionID: String) {
        database.getInstitution(institutionID)
    }

    fun cancelExamination(examinationID: String) {
        database.cancelExamination(examinationID)
    }
}
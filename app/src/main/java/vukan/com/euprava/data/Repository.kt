package vukan.com.euprava.data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import vukan.com.euprava.data.model.Doctor
import vukan.com.euprava.data.model.Examination
import vukan.com.euprava.data.model.Institution
import vukan.com.euprava.data.model.User

class Repository {
    private val database: Database = Database()
    private val userID: String = FirebaseAuth.getInstance().currentUser?.uid.toString()
    private val examinations: MutableLiveData<List<Examination>> = MutableLiveData()
    private val doctor: MutableLiveData<Doctor> = MutableLiveData()
    private val databaseUser: MutableLiveData<User> = MutableLiveData()
    private val doctors: MutableLiveData<List<Doctor>> = MutableLiveData()
    private val institution: MutableLiveData<Institution> = MutableLiveData()

    fun addUser(lboBzk: Array<String>) {
        database.addUser(userID, lboBzk)
    }

    fun addExamination(doctorID: String, dateTime: Timestamp) {
        database.addExamination(doctorID, userID, dateTime)

    }

    fun getExaminations(): MutableLiveData<List<Examination>> {
        database.getExaminations(userID, examinations::setValue)
        return examinations
    }

    fun getDoctors(lboBzk: Array<String>): MutableLiveData<List<Doctor>> {
        database.getDoctors(lboBzk, doctors::setValue)
        return doctors
    }

    fun getDoctorName(doctorID: String): String? {
        database.getDoctor(doctorID, doctor::setValue)
        return doctor.value?.name
    }

    fun getDoctorSpecialization(doctorID: String): String? {
        database.getDoctor(doctorID, doctor::setValue)
        return doctor.value?.specialization
    }

    fun getInstitution(institutionID: String): MutableLiveData<Institution> {
        database.getInstitution(institutionID, institution::setValue)
        return institution
    }

    fun getInstitutionName(institutionID: String): String? {
        database.getInstitution(institutionID, institution::setValue)
        return institution.value?.name
    }

    fun getUser(): MutableLiveData<User> {
        database.getUser(userID, databaseUser::setValue)
        return databaseUser
    }

    fun cancelExamination(examinationID: String) {
        database.cancelExamination(examinationID)
    }
}
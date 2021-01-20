package vukan.com.euprava.data

import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions
import vukan.com.euprava.data.model.Doctor
import vukan.com.euprava.data.model.Examination
import vukan.com.euprava.data.model.Institution
import vukan.com.euprava.data.model.User
import kotlin.reflect.KFunction1


class Database {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun addUser(userID: String, lboBzk: Array<String>) {
        val doc = firestore.collection("users").document(userID)

        firestore.runTransaction { transaction ->
            val snapshot: DocumentSnapshot = transaction.get(doc)

            if (!snapshot.exists()) {
                firestore.collection("users").document(userID).set(
                    User(userID, "Petar", "Petrovic", lboBzk[0], lboBzk[1]),
                    SetOptions.merge()
                )
            }
        }
    }

    fun getUser(userID: String, callback: KFunction1<User, Unit>) {
        firestore.collection("users").whereEqualTo("userID", userID).get()
            .addOnCompleteListener { task: Task<QuerySnapshot?> ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        callback(
                            User(
                                userID = userID,
                                name = document.getString("name").toString(),
                                surname = document.getString("surname").toString(),
                                lbo = document.getString("lbo").toString(),
                                bzk = document.getString("bzk").toString()
                            )
                        )
                    }
                }
            }
    }

    fun addExamination(doctorID: String, userID: String, dateTime: Timestamp) {
        val doc = firestore.collection("examination").document()

        doc.set(
            Examination(
                examinationID = doc.id,
                dateTime = dateTime,
                status = true,
                doctorID = doctorID,
                userID = userID
            ), SetOptions.merge()
        )
    }

    fun getExaminations(userID: String, callback: KFunction1<List<Examination>, Unit>) {
        val examinations = ArrayList<Examination>()

        firestore.collection("examination").whereEqualTo("userID", userID)
            .whereEqualTo("status", true).get()
            .addOnCompleteListener { task: Task<QuerySnapshot?> ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        examinations.add(
                            Examination(
                                examinationID = document.getString("examinationID").toString(),
                                dateTime = document.getTimestamp("dateTime"),
                                status = true,
                                doctorID = document.getString("doctorID").toString(),
                                userID = userID
                            )
                        )
                    }

                    callback(examinations)
                }
            }
    }

    fun getDoctorExaminations(doctorID: String, callback: KFunction1<ArrayList<Examination>, Unit>) {
        val examinations = ArrayList<Examination>()

        firestore.collection("examination").whereEqualTo("status", true)
            .whereEqualTo("doctorID", doctorID).get()
            .addOnCompleteListener { task: Task<QuerySnapshot?> ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        examinations.add(
                            Examination(
                                examinationID = document.getString("examinationID").toString(),
                                dateTime = document.getTimestamp("dateTime"),
                                status = true,
                                doctorID = doctorID,
                                userID = document.getString("userID").toString()
                            )
                        )
                    }

                    callback(examinations)
                }
            }
    }

    fun cancelExamination(examinationID: String) {
        val doc = firestore.collection("examination").document(examinationID)

        firestore.runTransaction { transaction ->
            transaction.update(doc, "status", false)
        }
    }

    fun getDoctor(doctorID: String, callback: KFunction1<Doctor, Unit>) {
        firestore.collection("doctors").whereEqualTo("doctorID", doctorID).get()
            .addOnCompleteListener { task: Task<QuerySnapshot?> ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        callback(
                            Doctor(
                                doctorID = doctorID,
                                name = document.getString("name").toString(),
                                surname = document.getString("surname").toString(),
                                specialization = document.getString("specialization").toString(),
                                institutionID = document.getString("institutionID").toString()
                            )
                        )
                    }
                }
            }
    }

    fun getDoctors(lboBzk: Array<String>, callback: KFunction1<List<Doctor>, Unit>) {
        val doctors = ArrayList<Doctor>()
        val lbo = lboBzk[0]
        val bzk = lboBzk[1]

        firestore.collection("doctors")
            .whereIn("doctorID", listOf(lbo[lbo.length - 1].toString(), bzk[bzk.length - 1].toString())).get()
            .addOnCompleteListener { task: Task<QuerySnapshot?> ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        doctors.add(
                            Doctor(
                                doctorID = document.getString("doctorID").toString(),
                                name = document.getString("name").toString(),
                                surname = document.getString("surname").toString(),
                                specialization = document.getString("specialization").toString(),
                                institutionID = document.getString("institutionID").toString()
                            )
                        )

                    }

                    callback(doctors)
                }
            }
    }

    fun getInstitution(institutionID: String, callback: KFunction1<Institution, Unit>) {
        firestore.collection("institution").whereEqualTo("institutionID", institutionID).get()
            .addOnCompleteListener { task: Task<QuerySnapshot?> ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        callback(
                            Institution(
                                institutionID = institutionID,
                                name = document.getString("name").toString(),
                                address = document.getString("address").toString(),
                                place = document.getString("place").toString(),
                                workingTime = document.getString("workingTime").toString()
                            )
                        )
                    }
                }
            }
    }
}
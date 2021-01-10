package vukan.com.euprava.data

import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import vukan.com.euprava.callbacks.ExaminationCallback
import vukan.com.euprava.callbacks.UserCallback
import vukan.com.euprava.data.model.User


class Database {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    fun addUser(user: FirebaseUser?) {
    }

    fun deleteUser(userID: String) {
        firestore.collection("products").whereEqualTo("userID", userID).get()
            .addOnCompleteListener { task: Task<QuerySnapshot?> ->
                if (task.isSuccessful) {
                    for (document in task.result!!) document.reference
                        .delete()
                }
            }
    }

    fun getUser(userID: String, callback: UserCallback) {
        firestore.collection("users").whereEqualTo("userID", userID).get()
            .addOnCompleteListener { task: Task<QuerySnapshot?> ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        callback.onCallback(
                            User(
                                userID = document.getString("userID").toString(),
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

//    fun isFavourite(productID: String, userID: String, callback: ExaminationCallback) {
//        firestore.collection("favouriteProducts").document(productID + userID).get()
//            .addOnCompleteListener { task: Task<DocumentSnapshot?> ->
//                if (task.isSuccessful) {
//                    if (task.result?.exists() == true)
//                        callback.onCallback() else callback.onCallback(false)
//                }
//            }
//    }

    fun addExamination(doctorID: String, dateTime: Timestamp) {
//        firestore.collection("favouriteProducts").document(productID + userID).set(product);
    }

    fun getExaminations(callback: ExaminationCallback) {

    }

    fun getDoctors(userID: String) {

    }

    fun getInstitution(institutionID: String) {

    }

    fun cancelExamination(examinationID: String) {

    }
}
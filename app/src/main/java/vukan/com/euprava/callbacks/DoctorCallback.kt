package vukan.com.euprava.callbacks

import vukan.com.euprava.data.model.Doctor

interface DoctorCallback {
    fun onCallback(doctor: Doctor)
}
package vukan.com.euprava.callbacks

import vukan.com.euprava.data.model.Doctor

interface InstitutionCallback {
    fun onCallback(doctor: Doctor)
}
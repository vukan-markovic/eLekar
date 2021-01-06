package vukan.com.euprava.callbacks

import vukan.com.euprava.data.model.Examination

interface ExaminationCallback {
    fun onCallback(examination: Examination)
}
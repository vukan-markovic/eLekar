package vukan.com.euprava.ui.examination

data class ExaminationState(
    val dateError: Int? = null,
    val timeError: Int? = null,
    val isDataValid: Boolean = false
)
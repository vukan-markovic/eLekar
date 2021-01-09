package vukan.com.euprava.data

class Repository {
    private val database: Database = Database()

    fun addUser() {
        database.addUser()
    }

    fun deleteUser() {
        database.deleteUser()
    }

    fun addExamination() {
        database.addExamination()
    }

    fun getExaminations() {
        database.getExaminations()
    }

    fun getDoctors() {
        database.getDoctors()
    }

    fun getInstitution() {
        database.getInstitution()
    }

    fun cancelExamination() {
        database.cancelExamination()
    }
}
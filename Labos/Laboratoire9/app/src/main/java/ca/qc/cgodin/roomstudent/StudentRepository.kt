package ca.qc.cgodin.roomstudent

import androidx.lifecycle.LiveData

class StudentRepository(private val studentDao: StudentDao) {
    // Room execute toutes les requêtes dans un thread séparé. // Observed LiveData will notify the observer when the data has changed.
    val allStudents: LiveData<List<Student>> = studentDao.getStudents()

    suspend fun insert(student: Student) {
        studentDao.insert(student)
    }
    suspend fun delete(student: Int) {
        studentDao.deleteStudent(student)
    }
    suspend fun getStudentByPhoneNumber(phone:String){
        studentDao.getStudentByPhoneNumber(phone)
    }
    suspend fun updateStudent(student: Student){
        studentDao.updateStudent(student)
    }

     fun searchStudent(name:String): LiveData<List<Student>> {
        return studentDao.getStudentByName(name)
    }
}
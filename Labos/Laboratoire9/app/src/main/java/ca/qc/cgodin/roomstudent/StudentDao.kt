package ca.qc.cgodin.roomstudent

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
    @Query("SELECT * from student_table ORDER BY firstName ASC")
    fun getStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM student_table WHERE id=(:id)")
    fun getStudent(id: Int): LiveData<Student?>

    @Query("SELECT * FROM student_table WHERE firstName=(:name)")
    fun getStudentByName(name: String):  LiveData<List<Student>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Query("DELETE FROM student_table")
    fun deleteAll()

    @Query("DELETE FROM student_table WHERE id=(:id)")
    fun deleteStudent(id: Int)

    @Query("SELECT * FROM student_table WHERE phoneNumber=(:phoneNumber)")
    fun getStudentByPhoneNumber(phoneNumber: String): LiveData<Student?>
}
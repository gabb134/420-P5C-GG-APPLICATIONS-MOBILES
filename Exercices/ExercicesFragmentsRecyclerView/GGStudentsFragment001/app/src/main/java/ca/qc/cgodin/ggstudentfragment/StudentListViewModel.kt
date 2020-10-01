package ca.qc.cgodin.ggstudentfragment

import androidx.lifecycle.ViewModel

class StudentListViewModel: ViewModel() {
    val students = mutableListOf<Student>()

    init {
        for(i in 0 until 100){
            students += Student("Student #$i", i%2 == 0,"Gab$i ","Marrero$i")

        }
    }
}
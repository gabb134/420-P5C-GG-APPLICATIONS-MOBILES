package ca.qc.cgodin.ggstudentfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StudentViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StudentListViewModel::class.java)){
            return StudentListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
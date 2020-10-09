package ca.qc.cgodin.database

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class StudentDBManagementActivity : Activity() {
    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        val list = findViewById<View>(R.id.studentList2) as RecyclerView
        val student1 = Student("Justin", "Trudeau", "jtrudeau@cgodin.qc.ca", "5141111111", "jtrudeau", "test")
        val student2 = Student("Stephen", "Harper", "sharper@cgodin.qc.ca", "5142222222", "sharper", "test")
        val student3 = Student("Philippe", "Couillard", "pcouillard@cgodin.qc.ca", "5143333333", "pcouillard", "test")
        val student4 = Student("Pauline", "Marois", "pmarois@cgodin.qc.ca", "5144444444", "pmarois", "test")
        val student5 = Student("Jean", "Charest", "jcharest@cgodin.qc.ca", "5145555555", "jcharest", "test")
        val student6 = Student("Francois", "Legault", "flegault@cgodin.qc.ca", "5146666666", "flegault", "test")
        val studentBdd = StudentBDD(this)
        studentBdd.openForWrite()
        var studentList = studentBdd.allStudents
        if (studentList == null) {
                studentBdd.insertStudent(student1)
                studentBdd.insertStudent(student2)
                studentBdd.insertStudent(student3)
                studentBdd.insertStudent(student4)
                studentBdd.insertStudent(student5)
                studentBdd.insertStudent(student6)
                studentList = studentBdd.allStudents
        }

        studentBdd.close()
        if (studentList != null) {
            list.adapter = StudentRecyclerViewAdapter(studentList.toList())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnAjoutStudent ->{
             //   Toast.makeText(this, "Vous avez cliqué sur l’icône de la barre d’outils", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@StudentDBManagementActivity, StudentAdd::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.student_menu, menu)
        return true
    }
}
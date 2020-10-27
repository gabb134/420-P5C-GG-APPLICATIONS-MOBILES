package ca.qc.cgodin.roomstudent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recyclerview_item.*

class MainActivity : AppCompatActivity() {

    private val studentViewModel: StudentViewModel by lazy {
        ViewModelProvider(this, StudentViewModelFactory(application)).get(StudentViewModel::class.java)
    }
    private val newStudentActivityRequestCode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java) //demander au prof
        // on récupère le id de l'étudiant du StudentListAdapter, car on  a pas trouver de moyen de l'éffacer dans l'autre classe(Student»ListAdapter)
        val intent = getIntent()

        val idStudentToDelete = intent.getIntExtra("id",-1)

        if(idStudentToDelete!=null|| idStudentToDelete==-1) {
            Toast.makeText(this, "L'étudiant a été supprimé, son id est : ${idStudentToDelete}", Toast.LENGTH_SHORT).show()
            studentViewModel.delete(idStudentToDelete)

        }

        val adapter = StudentListAdapter(this)
        recyclerview.adapter = adapter

        studentViewModel.allStudents.observe(
            this,
            Observer { students -> // Update the cached copy of the students in the adapter.
                students?.let { adapter.setStudents(it) }//demander au prof
            })

        fab.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                NewStudentActivity::class.java
            )
            startActivityForResult(intent, newStudentActivityRequestCode)
        }
        btnRecherche.setOnClickListener {

            val nomEtudiantrecherche = editTextStudent.text.toString()

            if(nomEtudiantrecherche!=""){

                //var studentSearchList : LiveData<List<Student>>

                //studentSearchList = studentViewModel.searchStudent(nomEtudiantrecherche)
    studentViewModel.searchStudent(nomEtudiantrecherche).observe(
        this, { students -> // Update the cached copy of the students in the adapter.
            students?.let { adapter.setStudents(it) }//demander au prof
        }
    )
               // Toast.makeText(this, "etudiant recherché : ${ studentViewModel.searchStudent(nomEtudiantrecherche).}", Toast.LENGTH_SHORT).show()


             /*   val intent =  Intent(
                    this, StudentSearch::class.java
                )
                startActivity(intent)*/
            }


        }



    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(
            requestCode,
            resultCode,
            intentData
        )
        if (requestCode == newStudentActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val student = Student(
                    0,
                    data.getStringExtra(NewStudentActivity.EXTRA_FIRSTNAME)!!,//demander au prof
                    data.getStringExtra(NewStudentActivity.EXTRA_LASTNAME)!!,
                    data.getStringExtra(NewStudentActivity.EXTRA_PHONENUMBER)!!,
                    data.getStringExtra(NewStudentActivity.EXTRA_EMAIL)!!

                    //"gmarrero@gmail.com"
                    //data.getStringExtra(NewStudentActivity.EXTRA_EMAIL)!!
                )
                Log.i("TAG", student.toString())
                studentViewModel.insert(student)
                Unit
            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }




}
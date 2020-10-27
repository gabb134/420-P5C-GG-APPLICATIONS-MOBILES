package ca.qc.cgodin.database

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class StudentAdd : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_add)
    }

    fun onClick(view: View) {
        val nom : EditText = findViewById(R.id.tvnom)
        val prenom : EditText = findViewById(R.id.tvPrenom)
        val courriel : EditText = findViewById(R.id.tvCourriel)
        val telehpone  : EditText = findViewById(R.id.tvTelephone)
        val usager  : EditText = findViewById(R.id.tvNomUsager)
        val motDePasse  : EditText =  findViewById(R.id.tvMotDePasse)

        try {
            val studentBD = Student(nom.text.toString(), prenom.text.toString(), courriel.text.toString(), telehpone.text.toString(), usager.text.toString(), motDePasse.text.toString())
            val studentBdd = StudentBDD(this)
            studentBdd.openForWrite()
         //   var studentList = studentBdd.allStudents
            studentBdd.insertStudent(studentBD)
           // studentList = studentBdd.allStudents
            studentBdd.close()
            val intent = Intent(this@StudentAdd, StudentDBManagementActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "L'étudiant a été ajouté dans la base de données!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Données non insérées", Toast.LENGTH_SHORT).show()
        }


    }

}
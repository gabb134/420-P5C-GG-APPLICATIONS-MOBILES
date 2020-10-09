package ca.qc.cgodin.database

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class StudentAdd : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_add)
    }

    fun onClick(view: View) {
        val nom = findViewById<View>(R.id.tvnom).toString()
        val prenom = findViewById<View>(R.id.tvPrenom).toString()
        val courriel = findViewById<View>(R.id.tvCourriel).toString()
        val telehpone = findViewById<View>(R.id.tvTelephone).toString()
        val usager = findViewById<View>(R.id.tvNomUsager).toString()
        val motDePasse = findViewById<View>(R.id.tvMotDePasse).toString()

        try {
            val studentBD = Student(nom, prenom, courriel, telehpone, usager, motDePasse)
            val studentBdd = StudentBDD(this)
            studentBdd.openForWrite()
            var studentList = studentBdd.allStudents
            studentBdd.insertStudent(studentBD)
            studentList = studentBdd.allStudents
            studentBdd.close()


            Toast.makeText(this, "L'étudiant a été ajouté dans la base de données!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Données non insérées", Toast.LENGTH_SHORT).show()
        }


    }

}
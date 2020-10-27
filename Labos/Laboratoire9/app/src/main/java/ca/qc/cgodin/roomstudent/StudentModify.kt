package ca.qc.cgodin.roomstudent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_new_student.*

class StudentModify : AppCompatActivity() {

    var Id : Int = 0

    private val studentViewModel: StudentViewModel by lazy {
        ViewModelProvider(this, StudentViewModelFactory(application)).get(StudentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_modify)

        val intent = getIntent()


        val nom = intent.getStringExtra("firstName")



        //val bundle: Bundle? = intent.extras
        //val firtName: String? = intent.getStringExtra("firstName")

      //  val firtName1: String? = bundle?.getString("firstName")
        //Toast.makeText(this, "Nom : ${firtName1}", Toast.LENGTH_SHORT).show()
        Id = intent.getIntExtra("id",-1)
         editFirstNameView.setText(intent.getStringExtra("firstName"))
        editLastNameView.setText(intent.getStringExtra("lastName"))
        editPhoneNumberView.setText(intent.getStringExtra("phoneNumber"))
        editEmailView.setText(intent.getStringExtra("email"))


         //Toast.makeText(this, "Id : ${ intent.getIntExtra("id",-1)}", Toast.LENGTH_SHORT).show()

    }
    fun updateInfoStudent(view: View){

            val student = Student(
                Id,
                editFirstNameView.text.toString(),
                editLastNameView.text.toString(),
                editPhoneNumberView.text.toString(),
                editEmailView.text.toString()
            )

        studentViewModel.updateStudent(student)

        val intent2 = Intent( this, MainActivity::class.java )
        startActivity(intent2)




    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(
            requestCode,
            resultCode,
            intentData
        )
        if (resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val student = Student(
                    0,
                    data.getStringExtra(StudentListAdapter.)!!,//demander au prof
                    data.getStringExtra(StudentListAdapter.EXTRA_LASTNAME)!!,
                    data.getStringExtra(StudentListAdapter.EXTRA_PHONENUMBER)!!,
                    data.getStringExtra(StudentListAdapter.EXTRA_EMAIL)!!

                    //"gmarrero@gmail.com"
                    //data.getStringExtra(NewStudentActivity.EXTRA_EMAIL)!!
                )

            }


            Toast.makeText(this, "Dans le modify", Toast.LENGTH_SHORT).show()

        }

    }*/

}
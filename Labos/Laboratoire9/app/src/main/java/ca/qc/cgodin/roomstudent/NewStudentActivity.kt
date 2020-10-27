package ca.qc.cgodin.roomstudent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_new_student.*

class NewStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)


        button_save.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editFirstNameView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {

                val firstName =
                    editFirstNameView.text.toString()
                replyIntent.putExtra(EXTRA_FIRSTNAME, firstName)

                val lastName =
                    editLastNameView.text.toString()
                replyIntent.putExtra(EXTRA_LASTNAME, lastName)

                val phoneNumber =
                    editPhoneNumberView.text.toString()
                replyIntent.putExtra(EXTRA_PHONENUMBER, phoneNumber)

                val email =
                    editEmailView.text.toString()
                replyIntent.putExtra(EXTRA_EMAIL, email)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_FIRSTNAME = "FIRSTNAME"
        const val EXTRA_LASTNAME = "LASTNAME"
        const val EXTRA_PHONENUMBER = "PHONENUMBER"
        const val EXTRA_EMAIL = "EMAIL"
    }


}



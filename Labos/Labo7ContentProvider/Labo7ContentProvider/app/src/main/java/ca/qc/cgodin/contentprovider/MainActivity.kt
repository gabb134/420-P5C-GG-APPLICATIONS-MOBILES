package ca.qc.cgodin.contentprovider

import android.Manifest
import android.app.AlertDialog
import android.content.ContentProviderOperation
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onAjouterClick(view: View){
        val builder = AlertDialog.Builder(this)

        builder.setItems(
            arrayOf(" Utiliser une intention implicite", "Ajouter directement à la Base de Données")
        ) { _, item ->
            Toast.makeText(
                applicationContext,
                "item choisi : $item",
                Toast.LENGTH_LONG
            ).show()
            if (item == 0){
                val intent = Intent(ContactsContract.Intents.Insert.ACTION).apply {
                    type = ContactsContract.RawContacts.CONTENT_TYPE
                }
                intent.apply {
                    putExtra(ContactsContract.Intents.Insert.EMAIL, emailAddress.text.toString())
                    putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, emailType.text.toString())
                    putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber.text.toString())
                    putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MAIN)
                    putExtra(ContactsContract.Intents.Insert.NAME, prenom.text.toString() + " " + nom.text.toString())
                }
                startActivity(intent)
            } else {
                val hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_CONTACTS)
                if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                        arrayOf(Manifest.permission.WRITE_CONTACTS),123)
                } else {

                    val ops = ArrayList<ContentProviderOperation>()

                    var op = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                    ops.add(op.build())

                    op = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, prenom.text.toString() + " " + nom.text.toString())
                        .withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, nom.text.toString())
                        .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, prenom.text.toString())
                    ops.add(op.build())

                    op = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Email.ADDRESS, emailAddress.text.toString())
                        .withValue(ContactsContract.CommonDataKinds.Email.TYPE, emailType.text.toString())
                    ops.add(op.build())

                    op = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber.text.toString())
                        .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MAIN)
                    ops.add(op.build())

                    contentResolver.applyBatch(ContactsContract.AUTHORITY, ops)

                }
            }
        }
        builder.create().show()
    }

    fun onRechercherClick(view: View){
        //Todo
    }

    fun onSupprimerClick(view: View) {
        //Todo
    }

    fun onModifierClick(view: View) {
        //Todo
    }
}

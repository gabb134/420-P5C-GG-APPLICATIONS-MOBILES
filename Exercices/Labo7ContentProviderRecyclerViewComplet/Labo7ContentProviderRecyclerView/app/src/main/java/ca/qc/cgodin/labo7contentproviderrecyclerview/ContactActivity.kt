package ca.qc.cgodin.labo7contentproviderrecyclerview

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class ContactActivity : AppCompatActivity() {
    val contactList = ArrayList<Contact>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDisplayContacts.setOnClickListener {
            Log.i("TAGG", "${contactList.size}")
            val hasReadContactsPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS)
                if (hasReadContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                        arrayOf(Manifest.permission.READ_CONTACTS), 123
                    )
                } else {

                val contactCursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    null,
                    null,
                    null
                )
                if (contactCursor != null) {
                    while (contactCursor.moveToNext()) {
                        val fullName =
                            contactCursor.getString(
                                contactCursor.getColumnIndexOrThrow(
                                    ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME_PRIMARY
                                )
                            )
                        val phoneNumber =
                            contactCursor.getString(
                                contactCursor.getColumnIndexOrThrow(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER
                                )
                            )
                        val contact = Contact(fullName, phoneNumber)

                        contactList.add(contact)
                    }
                }
                contactCursor?.close()

                // specify an viewAdapter (see also next example)
                contactRecyclerView.adapter = ContactAdapter(contactList, this@ContactActivity)

                Log.i("TAG", "${contactList.size}")

                for (element in contactList){
                    Log.i("TAGLIST", "${element.fullName} ${element.phoneNumber}" )
                }
            }

        }
    }
}
package ca.qc.cgodin.labo7contentproviderrecyclerview

import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.CursorLoader
import kotlinx.android.synthetic.main.activity_media.*


private const val TAG = "MediaActivity"

class MediaActivity : AppCompatActivity() {


    data class Audio(
        val name: String,
        val size: Int
    )

    val mediaList = ArrayList<Audio>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)

        try {
            val PROJECTION = arrayOf(
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION
            )
            val loader = CursorLoader(
                this,
                MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
                //MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                PROJECTION, null, null, null
            )
            val cur: Cursor = loader.loadInBackground()!!
            Log.d(TAG, "Audio files: " + cur.count)
            Log.d(TAG, "Columns: " + cur.columnCount)
            val name: Int = cur.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val size: Int = cur.getColumnIndex(MediaStore.Audio.Media.DURATION)
            cur.moveToFirst()
            while (!cur.isAfterLast) {
                Log.d(TAG, "Title" + cur.getString(name))
                Log.d(TAG, "Length: " + (cur.getInt(size) / 1000).toString() + " seconds")


                val audio = Audio(cur.getString(name),(cur.getInt(size) / 1000))
                mediaList.add(audio)

              //  mediaList+= Audio(cur.getString(name),(cur.getInt(size) / 1000))

                cur.moveToNext()
            }
            mediaRecyclerView.adapter = MediaAdapter(mediaList, this@MediaActivity)





        } catch (e: Exception) {
            Log.e(TAG, "Failed: ", e)
        }
    }
}
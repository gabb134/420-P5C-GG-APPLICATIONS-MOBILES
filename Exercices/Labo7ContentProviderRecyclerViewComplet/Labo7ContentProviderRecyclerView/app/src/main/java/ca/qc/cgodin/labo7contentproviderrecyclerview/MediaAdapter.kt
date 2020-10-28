package ca.qc.cgodin.labo7contentproviderrecyclerview


import android.content.ContentProviderOperation
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.io.File


class MediaAdapter(val items: ArrayList<MediaActivity.Audio>, val context: Context): RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.media_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvFileName.text = items[position].name
        holder.tvFileSize.text ="Durée:" +items[position].size.toString()

        val btnSupprimer = holder.itemView.findViewById<Button>(R.id.btnSupprimer)

        btnSupprimer.setOnClickListener {


            val selectionArgs = arrayOf(holder.tvFileName?.text.toString())
            val selectionClause = "${MediaStore.Audio.Media.TITLE} = ?"

            val ops = ArrayList<ContentProviderOperation>()
            val op = ContentProviderOperation.newDelete(MediaStore.Audio.Media.INTERNAL_CONTENT_URI).withSelection(
                selectionClause,
                selectionArgs
            )
            ops.add(op.build())

            //val whereVal = arrayOf<String>(audioId1)

           // Toast.makeText(context, "click sur la chanson: ${selectionArgs}", Toast.LENGTH_SHORT).show()

              //context.contentResolver.delete(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, selectionClause.toString(),selectionArgs)
            //contentResolver

         //   context.contentResolver.applyBatch(MediaStore.Audio.Media.INTERNAL_CONTENT_URI.authority.toString(),ops)

            //val arrTest = ArrayList<String>()


            /*context.contentResolver.query(uri, arrayOf(MediaStore.Files.FileColumns.DATA), null, null, null
            )?.apply {
                **val columnIndex = getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)**
                moveToFirst()
                path = getString(columnIndex)
                close()
            }*/


           val filedir = File(getRealPathFromURI(MediaStore.Audio.Media.INTERNAL_CONTENT_URI).toString())
            filedir.delete()
            Toast.makeText(context, "path de la chanson: ${getRealPathFromURI(MediaStore.Audio.Media.INTERNAL_CONTENT_URI).toString()}", Toast.LENGTH_SHORT).show()

           // val tempUri: Uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI.getre




        }




    }
    fun getRealPathFromURI(contentUri: Uri?): String? {
        var path: String? = null
        val proj = arrayOf(MediaStore.Audio.Media.DATA)
        val cursor: Cursor? =
            contentUri?.let { context.contentResolver.query(it, proj, null, null, null) }
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
                path = cursor.getString(column_index)
            }
        }
        if (cursor != null) {
            cursor.close()
        }
        return path
    }


    override fun getItemCount(): Int {
        return  items.size
    }


}

/*class ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val tvFullName = view.tvFullName
    val tvPhoneNumber = view.tvPhoneNumber
}*/

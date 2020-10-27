package ca.qc.cgodin.labo7contentproviderrecyclerview


import android.content.ContentProviderOperation
import android.content.Context
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class MediaAdapter(val items: ArrayList<MediaActivity.Audio>, val context: Context): RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.media_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvFileName.text = items[position].name
        holder.tvFileSize.text = items[position].size.toString()

        val btnSupprimer = holder.itemView.findViewById<Button>(R.id.btnSupprimer)

        btnSupprimer.setOnClickListener {
            Toast.makeText(context  , "click sur le button supprimer", Toast.LENGTH_SHORT).show()


            val selectionArgs = arrayOf(holder.tvFileName?.text.toString())
            val selectionClause = "${MediaStore.Audio.Media._ID} = ?"
            val ops = ArrayList<ContentProviderOperation>()
            val op = ContentProviderOperation.newDelete(MediaStore.Audio.Media.INTERNAL_CONTENT_URI).withSelection(selectionClause,selectionArgs)
            ops.add(op.build())

            //contentResolver

//            context.contentResolver.applyBatch(MediaStore.Audio.Media.INTERNAL_CONTENT_URI.authority.toString()  ,ops)

            val arrTest = ArrayList<String>()
            context.contentResolver.delete(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,arrTest)


        }


    }

    override fun getItemCount(): Int {
        return  items.size
    }


}

/*class ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val tvFullName = view.tvFullName
    val tvPhoneNumber = view.tvPhoneNumber
}*/

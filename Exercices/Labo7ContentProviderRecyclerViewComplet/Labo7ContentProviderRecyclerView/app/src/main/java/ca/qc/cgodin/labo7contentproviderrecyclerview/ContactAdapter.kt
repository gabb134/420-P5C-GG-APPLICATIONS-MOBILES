package ca.qc.cgodin.labo7contentproviderrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_item.view.*
import kotlinx.android.synthetic.main.media_item.view.*

class ContactAdapter(val items: ArrayList<Contact>, val context: Context):RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvFullName.text = items[position].fullName.toString()
        holder.tvPhoneNumber.text = items[position].phoneNumber.toString()
    }

    override fun getItemCount(): Int {
        return  items.size
    }


}

class ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val tvFullName = view.tvFullName
    val tvPhoneNumber = view.tvPhoneNumber

    val tvFileName = view.tvFileName
    val tvFileSize = view.tvSongSize
}

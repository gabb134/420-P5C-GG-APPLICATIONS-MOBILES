package ca.qc.cgodin.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentRecyclerViewAdapter(private val values: List<Student>) :RecyclerView.Adapter<StudentRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.student_item, parent, false) //cree un fichier xml , interpret et le convertio en element de la class auquel ils aprtiennent
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentRecyclerViewAdapter.ViewHolder, position: Int) { //liaison entre les vues et le donnees
        val student = values[position]
        holder.fullnameView.text = "${student.firstName} ${student.lastName}"
        holder.phoneNumberView.text = student.phoneNumber.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) { // il contient les vues, pour ne pas les recree a chaque fois
        val fullnameView: TextView = view.findViewById(R.id.tvFullname)
        val phoneNumberView: TextView = view.findViewById(R.id.tvPhoneNumber)

        override fun toString(): String {
            return super.toString() + " '" + fullnameView.text + "'"
        }
    }
}
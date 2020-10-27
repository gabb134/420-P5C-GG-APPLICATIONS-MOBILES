package ca.qc.cgodin.roomstudent

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item.*
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class StudentListAdapter constructor(context: Context
) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var students = emptyList<Student>() // Cached copy of students
    private lateinit var context: Context



    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private lateinit var student: Student
        val fullNameItemView: TextView = itemView.findViewById(R.id.fullNameView)
        val phoneNumberItemView: TextView = itemView.findViewById(R.id.phoneNumberView)

        val btnSupprimer : Button = itemView.findViewById(R.id.btnSupprimerRec)


        /*init {
            fullNameItemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {

        //    Toast.makeText(context, "${student.firstName} pressed!", Toast.LENGTH_SHORT).show()

            val intent = Intent(
                p0?.context,
                StudentModify::class.java
            )


            Log.i("StudentListAdapter ", "dans ${fullNameItemView.text.toString()}}")
        }*/

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView =
            inflater.inflate(R.layout.recyclerview_item, parent, false)



        return StudentViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val current = students[position]
        holder.fullNameItemView.text = "${current.firstName} ${current.lastName}"
        holder.phoneNumberItemView.text = "${current.phoneNumber}"
    // ajouter le bouton , on v aavoir acces a la possition ou l'étudiant s etrouve

        //2 listener 1 pour le btn delete et on autrre pour la modification

        //modification
        holder.fullNameItemView.setOnClickListener {
            val intent = Intent(holder.fullNameItemView.context,
                StudentModify::class.java
            )


            intent.putExtra("id",current.id)
            intent.putExtra("firstName", current.firstName)
            intent.putExtra("lastName",current.lastName)
            intent.putExtra("phoneNumber",current.phoneNumber)
            intent.putExtra("email",current.email)

            //Toast.makeText(holder.fullNameItemView.context, "Id : ${current.id}", Toast.LENGTH_SHORT).show()
            holder.fullNameItemView.context.startActivity(intent)
        }
        //suppression
        holder.btnSupprimer.setOnClickListener {
            val intent = Intent(holder.fullNameItemView.context,
                MainActivity::class.java)
            intent.putExtra("id",current.id)

            holder.fullNameItemView.context.startActivity(intent)

        }





    }



    fun setStudents(students: List<Student>) {
        this.students = students
        notifyDataSetChanged()
    }

    override fun getItemCount() = students.size
}

package ca.qc.cgodin.ggstudentfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val TAG = "CrimeListFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [StudentListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var studentListViewModel: StudentListViewModel
    private lateinit var studentViewModelFactory: StudentViewModelFactory

    private lateinit var studentRecyclerView: RecyclerView
    private var adapter: StudentAdapter? = null

//    private val crimeListViewModel: CrimeListViewModel by lazy {
//        ViewModelProvider.Factory
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        studentViewModelFactory = StudentViewModelFactory()

        studentListViewModel = ViewModelProvider(this, studentViewModelFactory).get(StudentListViewModel::class.java)
        val view: View  = inflater.inflate(R.layout.fragment_student_list, container, false)

        studentRecyclerView = view.findViewById(R.id.student_recycle_view)
        updateUI()

        return view
    }

    private fun updateUI(){
        val students = studentListViewModel.students
        adapter = StudentAdapter(students)
        studentRecyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG, "Total students: ${studentListViewModel.students.size}")
    }

    private inner class StudentViewHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var student: Student
        val titleTextView: TextView = view.findViewById(R.id.student_title)
        val dateTextView: TextView = view.findViewById(R.id.student_birthdate)
        private val studentImageView: ImageView = view.findViewById(R.id.good_student)

        init {
            view.setOnClickListener(this)
        }
        fun bind(student: Student){
            this.student = student
            //titleTextView.text = this.student.title
            titleTextView.text = this.student.firstName +" " +this.student.lastName
          //  titleTextView.text = this.student.lastName
            dateTextView.text = this.student.birthDate.toString()
            studentImageView.visibility = if(student.isGoodStudent){
                View.VISIBLE
            }else{
                View.GONE
            }
        }

        override fun onClick(p0: View?) {
            Toast.makeText(context, "${student.title} pressed!", Toast.LENGTH_SHORT).show()
        }
    }

    private inner class StudentAdapter(var students: List<Student>):RecyclerView.Adapter<StudentViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val view = layoutInflater.inflate(R.layout.list_item_student, parent, false)
            return StudentViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: StudentViewHolder, position: Int) {
            val student = students[position]
            viewHolder.bind(student)
//            viewHolder.apply {
//                titleTextView.text = student.title
//                dateTextView.text = student.birthDate.toString()
//            }
        }

//        override fun onBindViewHolder(viewHolder: StudentViewHolder, position: Int) {
//            val student = students[position]
//            viewHolder.apply {
//                titleTextView.text = student.title
//                dateTextView.text = student.birthDate.toString()
//            }
//        }

        override fun getItemCount() = students.size

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CrimeListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
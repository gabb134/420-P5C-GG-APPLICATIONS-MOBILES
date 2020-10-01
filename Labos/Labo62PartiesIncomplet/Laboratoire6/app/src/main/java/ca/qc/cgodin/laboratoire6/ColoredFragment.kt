package ca.qc.cgodin.laboratoire6

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ColoredFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ColoredFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mListener2: OnColoredFragmentInteractionListener
    private val TAG = ColoredFragment::class.java.canonicalName

    private lateinit var coloredFragmentLayout: View

    private lateinit var coloredFragmentLayout1: FrameLayout

    private lateinit var btnRemerciements : Button

    interface OnColoredFragmentInteractionListener {
        fun onSendTextFragmentInteraction(messageRemerciements: String)
    }


    override fun onAttach(context: Context) {
        Log.i("ColoredFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onAttach(context)

        try {
            // Set the OnColoringFragmentInteractionListener for communicating
           // with the hosting Activity
                    mListener2 = context as OnColoredFragmentInteractionListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                "$context must implement OnColoredFragmentInteractionListener ")
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.i("ColoredFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.i("ColoredFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")


        coloredFragmentLayout = inflater.inflate(
            R.layout.fragment_colored,
            container, false
        )
        btnRemerciements  =coloredFragmentLayout.findViewById<Button>(R.id.btnRemerciements)
        btnRemerciements.isEnabled = false
        btnRemerciements.setOnClickListener {

            mListener2.onSendTextFragmentInteraction("Merci de m’avoir coloré")
            Toast.makeText(activity, "boutton remerciments séléctionné!",Toast.LENGTH_LONG).show()
        }
        return coloredFragmentLayout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i("ColoredFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.i("ColoredFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onStart()
    }

    override fun onPause() {
        Log.i("ColoredFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onPause()
    }

    override fun onStop() {
        Log.i("ColoredFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.i("ColoredFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i("ColoredFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("ColoredFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onDetach()
    }

    fun setLayoutBackgroundColor(color: Int) {
        coloredFragmentLayout.setBackgroundColor(color)
        btnRemerciements.isEnabled = true
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ColoredFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ColoredFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
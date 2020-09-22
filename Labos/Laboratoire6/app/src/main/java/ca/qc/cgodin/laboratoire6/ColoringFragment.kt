package ca.qc.cgodin.laboratoire6

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ColoringFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ColoringFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mListener: OnColoringFragmentInteractionListener
    private val TAG = ColoringFragment::class.java.canonicalName
    private lateinit var coloringFragmentLayout: FrameLayout

    private lateinit var coloringFragmentLayout1: View

    private lateinit var spinner: Spinner

    private lateinit var btnReplace: Button

    var tabCouleurs = arrayOf("Rouge", "Bleu", "Vert", "Jaune", "Gris", "Cyan", "Noir")
//    val couleurs = resources.getStringArray(R.array.Couleurs)

    val NEW_SPINNER_ID = 1

    interface OnColoringFragmentInteractionListener {
        fun onSendColorFragmentInteraction(couleur: String)
        fun onChangeFragment()
    }

    fun enableReplaceFragmentButton() {
        btnReplace.visibility = View.VISIBLE
    }


    override fun onAttach(context: Context) {
        Log.i("ColoringFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onAttach(context)
        try {
            // Set the OnColoringFragmentInteractionListener for communicating
            // with the hosting Activity
            mListener = context as OnColoringFragmentInteractionListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement OnColoringFragmentInteractionListener")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.i("ColoringFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.i("ColoringFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")

        val coloringFragmentLayout = inflater.inflate(
            R.layout.fragment_coloring,
            container, false
        )
        /*coloringFragmentLayout.setOnClickListener {
            mListener.onSendColorFragmentInteraction(Random.nextInt())
        }*/
        //spinner
        spinner = coloringFragmentLayout.findViewById(R.id.mySpinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                activity!!.applicationContext,
                android.R.layout.simple_spinner_item,
                tabCouleurs
            )

            spinner.adapter = adapter;
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        activity,
                        getString(R.string.selected_item) + " " +
                                "" + tabCouleurs[position], Toast.LENGTH_SHORT
                    ).show()

                    //on va mettre  mListener.onSendColorFragmentInteraction(Random.nextInt()), et on va envoyer un string(le nom de la couleur)
                    // dans mainActivity on va le recuperer et a l'aide d;unn switch on va changer les couleurs selon le spinner choisi
                    mListener.onSendColorFragmentInteraction(tabCouleurs[position].toString())
                }
            }
        }
        btnReplace = coloringFragmentLayout.findViewById<Button>(R.id.btnReplaceFragment)

        btnReplace.setOnClickListener {
            mListener.onChangeFragment()
            btnReplace.isEnabled = false
        }


        coloringFragmentLayout1 = inflater.inflate(
            R.layout.fragment_coloring,
            container, false
        )

        return coloringFragmentLayout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i("ColoringFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.i("ColoringFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onStart()
    }

    override fun onPause() {
        Log.i("ColoringFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onPause()
    }

    override fun onStop() {
        Log.i("ColoringFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.i("ColoringFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i("ColoringFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("ColoringFragment ", "dans ${object {}.javaClass.enclosingMethod.name}")
        super.onDetach()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ColoringFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ColoringFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
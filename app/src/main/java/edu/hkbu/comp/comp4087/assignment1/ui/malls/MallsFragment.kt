package edu.hkbu.comp.comp4087.assignment1.ui.malls

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.hkbu.comp.comp4087.assignment1.R
import edu.hkbu.comp.comp4087.assignment1.data.malls
import edu.hkbu.comp.comp4087.assignment1.ui.malls.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class MallsFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_malls_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                val mallsImage = resources.getStringArray(R.array.mallsImage)
                val mallsTitle = resources.getStringArray(R.array.mallsTitle)
                val mallsDetail = resources.getStringArray(R.array.mallsDetail)
                val mallslist = mutableListOf<malls>()
                for (i in 0..(mallsDetail.size - 1))
                    mallslist.add(malls(mallsImage[i], mallsTitle[i], mallsDetail[i]))
                adapter = MallsRecyclerViewAdapter(mallslist)
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MallsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
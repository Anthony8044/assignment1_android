package edu.hkbu.comp.comp4087.assignment1.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import edu.hkbu.comp.comp4087.assignment1.R
import edu.hkbu.comp.comp4087.assignment1.data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {

                val mallName = arguments?.getString("mallName")
                val mallid = mallName.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val dao = AppDatabase.getInstance(context).couponDao()
                    val filteredmall = dao.findMoreThan()
                    CoroutineScope(Dispatchers.Main).launch {
                        adapter = DetailsRecyclerViewAdapter(filteredmall)
                    }
                }

                (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
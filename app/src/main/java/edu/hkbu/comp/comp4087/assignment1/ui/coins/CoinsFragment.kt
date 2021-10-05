package edu.hkbu.comp.comp4087.assignment1.ui.coins

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import edu.hkbu.comp.comp4087.assignment1.R
import edu.hkbu.comp.comp4087.assignment1.data.AppDatabase
import edu.hkbu.comp.comp4087.assignment1.data.SampleData
import edu.hkbu.comp.comp4087.assignment1.ui.malls.MallsOneRecyclerViewAdapter
import edu.hkbu.comp.comp4087.assignment1.ui.malls.MallsTwoRecyclerViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
class CoinsFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_coins_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
//                adapter = CoinsRecyclerViewAdapter(PlaceholderContent.ITEMS)
                val ranges = arguments?.getString("ranges")
                val thisrange = ranges.toString()
                if (ranges == null)
                    adapter = CoinsOneRecyclerViewAdapter(SampleData.COINRANGES)
                else if(thisrange == "Coins <= 300"){
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = AppDatabase.getInstance(context).couponDao()
                        val filteredRanges = dao.findLessThan()
                        CoroutineScope(Dispatchers.Main).launch {
                            adapter = CoinsTwoRecyclerViewAdapter(filteredRanges)
                        }
                    }
                    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }else if(thisrange == "300 < Coins < 600"){
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = AppDatabase.getInstance(context).couponDao()
                        val filteredRanges = dao.findInBetween()
                        CoroutineScope(Dispatchers.Main).launch {
                            adapter = CoinsTwoRecyclerViewAdapter(filteredRanges)
                        }
                    }
                    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
                } else{
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = AppDatabase.getInstance(context).couponDao()
                        val filteredRanges = dao.findMoreThan()
                        CoroutineScope(Dispatchers.Main).launch {
                            adapter = CoinsTwoRecyclerViewAdapter(filteredRanges)
                        }
                    }
                    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }
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
            CoinsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
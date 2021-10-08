package edu.hkbu.comp.comp4087.assignment1.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.hkbu.comp.comp4087.assignment1.R
import edu.hkbu.comp.comp4087.assignment1.data.AllCoupons
import edu.hkbu.comp.comp4087.assignment1.data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * A fragment representing a list of Items.
 */
class HomeFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val recyclerView =
//            inflater.inflate(R.layout.fragment_home_list, container, false) as RecyclerView
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        val swipeLayout = SwipeRefreshLayout(requireContext())
//        swipeLayout.addView(recyclerView)
//        swipeLayout.setOnRefreshListener {
//            swipeLayout.isRefreshing = true
//            reloadData(recyclerView)
//            swipeLayout.isRefreshing = false
//        }
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        reloadData(recyclerView)
//
//        return swipeLayout
//
//    }
//
//    private fun reloadData(recyclerView: RecyclerView) {
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val dao = context?.let { AppDatabase.getInstance(it).couponDao() }
//                val events = dao?.findAll()
//                CoroutineScope(Dispatchers.Main).launch {
//                    recyclerView.adapter = events?.let { HomeRecyclerViewAdapter(it) }
//                }
//
//            } catch (e: Exception){
//                val emptyCoupons = listOf(
//                    AllCoupons(
//                        "",
//                        "Cannot fetch Coupon data",
//                        "Please check your network connection,",
//                        "",
//                        "",
//                        "",
//                        "",
//                        ""
//                    )
//                )
//                CoroutineScope(Dispatchers.Main).launch {
//                    recyclerView.adapter = HomeRecyclerViewAdapter(emptyCoupons)
//                }
//
//            }
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {

                val mallName = arguments?.getString("mallName")
                if (mallName == null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val dao = AppDatabase.getInstance(context).couponDao()
                            val events = dao.findAll()
                            CoroutineScope(Dispatchers.Main).launch {
                                adapter = HomeRecyclerViewAdapter(events)
                            }

                        } catch (e: Exception) {
                            val emptyCoupons = listOf(
                                AllCoupons(
                                    "",
                                    "Cannot fetch Coupon data",
                                    "Please check your network connection,",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    ""
                                )
                            )
                            CoroutineScope(Dispatchers.Main).launch {
                                adapter = HomeRecyclerViewAdapter(emptyCoupons)
                            }

                        }
                    }
                }else {
//                    CoroutineScope(Dispatchers.IO).launch {
//                        val dao = AppDatabase.getInstance(context).couponDao()
//                        val events = dao.findid(mallName)
//                        CoroutineScope(Dispatchers.Main).launch {
//                            adapter = DetailsRecyclerViewAdapter(events)
//                        }
//
//                    }

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
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
package edu.hkbu.comp.comp4087.assignment1.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.hkbu.comp.comp4087.assignment1.Network
import edu.hkbu.comp.comp4087.assignment1.R
import edu.hkbu.comp.comp4087.assignment1.data.AllCoupons
import edu.hkbu.comp.comp4087.assignment1.data.AppDatabase
import edu.hkbu.comp.comp4087.assignment1.data.CouponDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
//        return swipeLayout
////        reloadData(recyclerView)
////        return recyclerView
//    }
//
//    private fun reloadData(recyclerView: RecyclerView) {
//        val deptID = arguments?.getString("dept_id")
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val dao = AppDatabase.getInstance(context).couponDao()
//                val events = deptID?.let { dao.findEventsByDeptID(it) }
//                CoroutineScope(Dispatchers.Main).launch {
//                    recyclerView.adapter = events?.let { HomeRecyclerViewAdapter(it) }
//                }
//            } catch (e: Exception) {
//                Log.d("HomeListFragment", "reloadData: ${e}")
//                val news = listOf(
//                    AllCoupons(
//                        0,
//                        "Cannot fetch news",
//                        "Please check your network connection,",
//                        "",
//                        "",
//                        0,
//                        ""
//                    )
//                )
//                CoroutineScope(Dispatchers.Main).launch {
//                    recyclerView.adapter = HomeRecyclerViewAdapter(news)
//                }
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

                //val dept_id = arguments?.getString("dept_id")
                CoroutineScope(Dispatchers.IO).launch {
                    val dao = AppDatabase.getInstance(context).couponDao()
                    val events = dao.findAll()
                    CoroutineScope(Dispatchers.Main).launch {
                        adapter = HomeRecyclerViewAdapter(events)
                    }
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
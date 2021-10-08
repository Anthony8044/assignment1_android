package edu.hkbu.comp.comp4087.assignment1

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import edu.hkbu.comp.comp4087.assignment1.data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.squareup.picasso.Picasso
import edu.hkbu.comp.comp4087.assignment1.ui.home.HomeRecyclerViewAdapter
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CouponDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view = inflater.inflate(
            R.layout.fragment_details, container, false
        )

        val id = arguments?.getString("id")
        val str = id.toString()
        val titletextview2 = view.findViewById<TextView>(R.id.titleTextView2)
        val couponImageView2 = view.findViewById<ImageView>(R.id.couponImageView2)
        val detailsTextView3 = view.findViewById<TextView>(R.id.detailsTextView3)
        val mallTextView2 = view.findViewById<TextView>(R.id.mallTextView2)
        val quotaTextView = view.findViewById<TextView>(R.id.quotaTextView)
        val validTextView = view.findViewById<TextView>(R.id.validTextView)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val dao = AppDatabase.getInstance(view.context).couponDao()
                val coupon = dao.findid(str)
                CoroutineScope(Dispatchers.Main).launch {
                    Picasso.get().load(coupon[0].image).into(couponImageView2);
                }
                titletextview2.setText(coupon[0].restaurant)
                detailsTextView3.setText(coupon[0].detail)
                mallTextView2.setText(coupon[0].mall)
                quotaTextView.setText(coupon[0].quota)
                validTextView.setText(coupon[0].expirydate)

            }catch (e: Exception){
                val error = e.printStackTrace().toString()
                titletextview2.setText(error)
            }
        }

        return view
        //return inflater.inflate(R.layout.fragment_coupon_details, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CouponDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
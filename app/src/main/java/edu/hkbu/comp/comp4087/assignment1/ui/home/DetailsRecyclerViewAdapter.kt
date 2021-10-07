package edu.hkbu.comp.comp4087.assignment1.ui.home

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.squareup.picasso.Picasso
import edu.hkbu.comp.comp4087.assignment1.R
import edu.hkbu.comp.comp4087.assignment1.data.AllCoupons
import edu.hkbu.comp.comp4087.assignment1.data.AppDatabase
import edu.hkbu.comp.comp4087.assignment1.databinding.FragmentDetailsBinding
import edu.hkbu.comp.comp4087.assignment1.databinding.FragmentHomeItemBinding
import edu.hkbu.comp.comp4087.assignment1.ui.home.placeholder.PlaceholderContent.PlaceholderItem
import edu.hkbu.comp.comp4087.assignment1.ui.malls.MallsTwoRecyclerViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class DetailsRecyclerViewAdapter(
    private val values: List<AllCoupons>
) : RecyclerView.Adapter<DetailsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentDetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleTextView.text = item.restaurant
        holder.detailTextView.text = item.title
        holder.mallTextView.text = item.mall
        holder.dateTextView3.text = item.title
        holder.coinsTextView.text = item.coins
        if (item.image != "")
            Picasso.get().load(item.image).into(holder.mallsImageView)
        else
            holder.mallsImageView.setImageDrawable(holder.itemView
                .context.getDrawable(R.drawable.ic_baseline_hide_image_24))

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val mallsImageView: ImageView = binding.mallsImageView
        val titleTextView: TextView = binding.titleTextView
        val detailTextView: TextView = binding.detailTextView
        val mallTextView: TextView = binding.mallTextView
        val dateTextView3: TextView = binding.dateTextView3
        val coinsTextView: TextView = binding.coinsTextView

            override fun toString(): String {
                return super.toString() + " '" + titleTextView.text + "'"
            }

    }

}
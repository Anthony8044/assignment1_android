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
class HomeRecyclerViewAdapter(
    private val values: List<AllCoupons>
) : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentHomeItemBinding.inflate(
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
        holder.coinsTextView.text = item.coins
        holder.mallidTextView.text = item.id
        if (item.image != "")
            Picasso.get().load(item.image).into(holder.mallsImageView)
        else
            holder.mallsImageView.setImageDrawable(holder.itemView
                .context.getDrawable(R.drawable.ic_baseline_hide_image_24))

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val mallsImageView: ImageView = binding.mallsImageView
        val titleTextView: TextView = binding.titleTextView
        val detailTextView: TextView = binding.detailTextView
        val coinsTextView: TextView = binding.coinsTextView
        val mallidTextView: TextView = binding.mallidTextView

        init {
            binding.root.setOnClickListener{
                it.findNavController().navigate(
                    R.id.action_navigation_home_to_detailsFragment,
                    bundleOf(Pair("id", mallidTextView.text.toString()))
                )
            }
        }

//        val idView: TextView = binding.itemNumber
//        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + titleTextView.text + "'"
        }

    }

}
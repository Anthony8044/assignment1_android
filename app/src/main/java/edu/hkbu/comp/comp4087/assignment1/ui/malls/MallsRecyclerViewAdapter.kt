package edu.hkbu.comp.comp4087.assignment1.ui.malls

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import edu.hkbu.comp.comp4087.assignment1.data.malls
import edu.hkbu.comp.comp4087.assignment1.databinding.FragmentMallsItemBinding
import edu.hkbu.comp.comp4087.assignment1.ui.malls.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MallsRecyclerViewAdapter(
    private val values: List<malls>
) : RecyclerView.Adapter<MallsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMallsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleTextView.text = item.title
        holder.detailTextView.text = item.detail
        Picasso.get().load(item.image).into(holder.mallsImageView)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentMallsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val mallsImageView: ImageView = binding.mallsImageView
        val titleTextView: TextView = binding.titleTextView
        val detailTextView: TextView = binding.detailTextView

        override fun toString(): String {
            return super.toString() + " '" + detailTextView.text + "'"
        }
    }

}
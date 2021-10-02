package edu.hkbu.comp.comp4087.assignment1.ui.coins

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.hkbu.comp.comp4087.assignment1.data.Inrange
import edu.hkbu.comp.comp4087.assignment1.databinding.FragmentCoinsItemBinding
import edu.hkbu.comp.comp4087.assignment1.ui.coins.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class CoinsRecyclerViewAdapter(
    private val values: List<Inrange>
) : RecyclerView.Adapter<CoinsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentCoinsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.title
        holder.contentView.text = item.deptId
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentCoinsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}
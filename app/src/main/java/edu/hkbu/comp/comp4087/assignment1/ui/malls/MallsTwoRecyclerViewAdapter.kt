package edu.hkbu.comp.comp4087.assignment1.ui.malls

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import edu.hkbu.comp.comp4087.assignment1.R
import edu.hkbu.comp.comp4087.assignment1.data.AllCoupons
import edu.hkbu.comp.comp4087.assignment1.data.CoinsTwo
import edu.hkbu.comp.comp4087.assignment1.data.MallsTwo
import edu.hkbu.comp.comp4087.assignment1.databinding.FragmentMallsItemBinding


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MallsTwoRecyclerViewAdapter(
    private val values: List<AllCoupons>
) : RecyclerView.Adapter<MallsTwoRecyclerViewAdapter.ViewHolder>() {

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
        holder.idView.text = item.restaurant
        holder.contentView.text = item.mall
        holder.mallidTextView2.text = item.id

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentMallsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val mallidTextView2: TextView = binding.mallidTextView2

        init {
            binding.root.setOnClickListener{
                it.findNavController().navigate(
                    R.id.action_mallsFragment_to_detailsFragment,
                    bundleOf(Pair("id", mallidTextView2.text.toString()))
                )
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}
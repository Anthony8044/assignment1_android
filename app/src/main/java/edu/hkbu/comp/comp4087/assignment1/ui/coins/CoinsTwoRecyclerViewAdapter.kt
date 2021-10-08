package edu.hkbu.comp.comp4087.assignment1.ui.coins

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import edu.hkbu.comp.comp4087.assignment1.R
import edu.hkbu.comp.comp4087.assignment1.data.AllCoupons
import edu.hkbu.comp.comp4087.assignment1.data.CoinsTwo
import edu.hkbu.comp.comp4087.assignment1.databinding.FragmentCoinsItemBinding
import edu.hkbu.comp.comp4087.assignment1.ui.coins.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class CoinsTwoRecyclerViewAdapter(
    private val values: List<AllCoupons>
) : RecyclerView.Adapter<CoinsTwoRecyclerViewAdapter.ViewHolder>() {

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
        holder.idView.text = item.restaurant
        holder.contentView.text = item.mall
        holder.contentView2.text = item.coins
        holder.mallidTextView3.text = item.id

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentCoinsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val contentView2: TextView = binding.content2
        val mallidTextView3: TextView = binding.mallidTextView3


        init {
            binding.root.setOnClickListener{
                it.findNavController().navigate(
                    R.id.action_coinsFragment_to_detailsFragment,
                    bundleOf(Pair("id", mallidTextView3.text.toString()))
                )
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}
package edu.hkbu.comp.comp4087.assignment1.ui.coins

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import edu.hkbu.comp.comp4087.assignment1.R
import edu.hkbu.comp.comp4087.assignment1.data.CoinsOne
import edu.hkbu.comp.comp4087.assignment1.databinding.FragmentCoinsItemBinding

class CoinsOneRecyclerViewAdapter(
    private val values: List<CoinsOne>
) : RecyclerView.Adapter<CoinsOneRecyclerViewAdapter.ViewHolder>() {

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
        holder.idView.text = item.Ranges
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentCoinsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        init {
            binding.root.setOnClickListener {
                it.findNavController().navigate(
                    R.id.action_coinsFragment_self,
                    bundleOf(Pair("ranges", idView.text.toString()))
                )
            } }
    }

}
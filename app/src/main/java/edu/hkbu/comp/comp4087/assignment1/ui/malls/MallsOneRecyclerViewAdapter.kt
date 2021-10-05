package edu.hkbu.comp.comp4087.assignment1.ui.malls

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import edu.hkbu.comp.comp4087.assignment1.R
import edu.hkbu.comp.comp4087.assignment1.data.CoinsOne
import edu.hkbu.comp.comp4087.assignment1.data.MallsOne
import edu.hkbu.comp.comp4087.assignment1.databinding.FragmentMallsItemBinding
import edu.hkbu.comp.comp4087.assignment1.ui.malls.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MallsOneRecyclerViewAdapter(
    private val values: List<MallsOne>
) : RecyclerView.Adapter<MallsOneRecyclerViewAdapter.ViewHolder>() {

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
        holder.idView.text = item.region
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentMallsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        init {
            binding.root.setOnClickListener {
                it.findNavController().navigate(
                    R.id.action_mallsFragment_self,
                    bundleOf(Pair("region", idView.text.toString()))
                )
            } }
    }

}
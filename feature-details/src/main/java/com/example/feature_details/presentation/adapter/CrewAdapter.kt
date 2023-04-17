package com.example.feature_details.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_details.databinding.ItemCrewBinding
import com.example.feature_details.domain.entity.Crew

class CrewAdapter :
    RecyclerView.Adapter<CrewAdapter.LaunchViewHolder>() {
    private val crews = mutableListOf<Crew>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val binding = ItemCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchViewHolder(binding)
    }

    override fun getItemCount() = crews.size

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(crews[position])
    }

    fun submitList(crews: List<Crew>) {
        val diffCallback = CrewDiffCallback(this.crews, crews)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.crews.clear()
        this.crews.addAll(crews)
        diffResult.dispatchUpdatesTo(this)
    }

    class LaunchViewHolder(private val binding: ItemCrewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(crew: Crew) {
            binding.tvName.text = crew.name
            binding.tvAgency.text = crew.agency
            binding.tvStatus.text = crew.status
        }
    }

    class CrewDiffCallback(
        private val oldList: List<Crew>,
        private val newList: List<Crew>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }
    }
}
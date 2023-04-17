package com.example.feature_main.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.core.presentation.date.DateConversion
import com.example.core.presentation.date.DateFormatter
import com.example.feature_main.databinding.ItemLaunchBinding
import com.example.core.domain.entity.Launch

class LaunchAdapter(private val onLaunchClick: (launchId: String) -> Unit) :
    RecyclerView.Adapter<LaunchAdapter.LaunchViewHolder>() {
    private val launches = mutableListOf<Launch>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val binding = ItemLaunchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchViewHolder(binding)
    }

    override fun getItemCount() = launches.size

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(launches[position], onLaunchClick)
    }

    fun submitList(launches: List<Launch>) {
        val diffCallback = LaunchDiffCallback(this.launches, launches)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.launches.clear()
        this.launches.addAll(launches)
        diffResult.dispatchUpdatesTo(this)
    }

    class LaunchViewHolder(private val binding: ItemLaunchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(launch: Launch, onLaunchClick: (launchId: String) -> Unit) {
            binding.tvName.text = launch.name
            binding.tvSuccess.text = if (launch.success) "Успешно" else "Провал"
            binding.tvLaunchDate.text =
                DateFormatter().format(launch.fireDateUtc, DateConversion.DISPLAY_DATE)
            binding.ivMissionPatch.load(launch.links.patch.small)
            binding.tvFlight.text = launch.cores.sumOf { it.flight }.toString()
            binding.root.setOnClickListener { onLaunchClick(launch.id) }
        }
    }

    class LaunchDiffCallback(
        private val oldList: List<Launch>,
        private val newList: List<Launch>
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
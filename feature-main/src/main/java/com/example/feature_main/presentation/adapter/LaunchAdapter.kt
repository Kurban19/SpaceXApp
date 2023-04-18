package com.example.feature_main.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.core.presentation.date.DateConversion
import com.example.core.presentation.date.DateFormatter
import com.example.feature_main.databinding.ItemLaunchBinding
import com.example.core.domain.entity.Launch

class LaunchAdapter(private val onLaunchClick: (launchId: String) -> Unit) :
    PagingDataAdapter<Launch, LaunchAdapter.LaunchViewHolder>(LaunchDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val binding = ItemLaunchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(getItem(position), onLaunchClick)
    }

    class LaunchViewHolder(private val binding: ItemLaunchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(launch: Launch?, onLaunchClick: (launchId: String) -> Unit) {
            launch ?: return
            binding.tvName.text = launch.name
            binding.tvSuccess.text = if (launch.success) "Успешно" else "Провал"
            if (launch.fireDateUtc.isNotBlank()) {
                binding.tvLaunchDate.text =
                    DateFormatter().format(launch.fireDateUtc, DateConversion.DISPLAY_DATE)
            }
            binding.ivMissionPatch.load(launch.links.patch.small)
            binding.tvFlight.text = launch.cores.sumOf { it.flight }.toString()
            binding.root.setOnClickListener { onLaunchClick(launch.id) }
        }
    }

    private object LaunchDiffCallback : DiffUtil.ItemCallback<Launch>() {

        override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name
        }

    }
}
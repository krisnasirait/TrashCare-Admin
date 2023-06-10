package com.trashcare.admin.presentation.adapter

import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.trashcare.admin.databinding.ItemTrashListBinding

class TrashListAdapter(
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<TrashListAdapter.TrashListViewHolder>() {


    inner class TrashListViewHolder(
        private val binding: ItemTrashListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrashListViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TrashListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}
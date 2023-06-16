package com.trashcare.admin.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.trashcare.admin.R
import com.trashcare.admin.TrashCareAdminApp
import com.trashcare.admin.data.model.response.usersubmission.UserSubmissionResponseItem
import com.trashcare.admin.databinding.ItemTrashListBinding

class TrashListAdapter(
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<TrashListAdapter.TrashListViewHolder>() {

    private val itemList = mutableListOf<UserSubmissionResponseItem?>()

    inner class TrashListViewHolder(
        private val binding: ItemTrashListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserSubmissionResponseItem) {
            binding.apply {
                tvName.text = item.nama
                tvIdUser.text = item.userID
                tvStatus.text = item.status

                if (item.status == "Verified") {
                    tvStatus.setTextColor(ContextCompat.getColor(TrashCareAdminApp.context, R.color.green_600))
                } else if (item.status == "Rejected") {
                    tvStatus.setTextColor(ContextCompat.getColor(TrashCareAdminApp.context, R.color.red))
                }

                btnDetails.setOnClickListener {
                    itemClickListener.onItemPlaceListClicked(
                        item.trashID,
                        item.nama,
                        item.deskripsi,
                        item.status
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrashListViewHolder {
        return TrashListViewHolder(
            ItemTrashListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: TrashListViewHolder, position: Int) {
        itemList[position]?.let { holder.bind(it) }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<UserSubmissionResponseItem?>) {
        itemList.clear()
        itemList.addAll(data)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemPlaceListClicked(id: String, userName: String, descSampah: String, status: String)
    }


}
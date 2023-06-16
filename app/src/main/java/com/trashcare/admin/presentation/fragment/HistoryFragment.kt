package com.trashcare.admin.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.trashcare.admin.databinding.FragmentHistoryBinding
import com.trashcare.admin.presentation.activity.DetailTransactionActivity
import com.trashcare.admin.presentation.adapter.HistoryAdapter
import com.trashcare.admin.presentation.viewmodel.TrashCareAdminViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryFragment : Fragment(),
    HistoryAdapter.OnItemClickListener {

    private lateinit var binding: FragmentHistoryBinding
    private val trashCareAdminViewModel: TrashCareAdminViewModel by viewModel()
    private lateinit var historyAdapter: HistoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupViewModelObservers()
    }

    private fun setupRecyclerView() {
        historyAdapter = HistoryAdapter(this)
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = historyAdapter
        }
    }

    private fun setupViewModelObservers() {
        trashCareAdminViewModel.getUserSubmission("All")

        trashCareAdminViewModel.listSubmission.observe(viewLifecycleOwner) {
            historyAdapter.setData(it)
        }
    }

    override fun onResume() {
        super.onResume()
        trashCareAdminViewModel.getUserSubmission("All")
    }


    override fun onItemPlaceListClicked(
        id: String,
        userName: String,
        descSampah: String,
        status: String
    ) {
        val intent = Intent(requireContext(), DetailTransactionActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("userName", userName)
        intent.putExtra("descSampah", descSampah)
        intent.putExtra("status", status)
        startActivity(intent)
    }


}
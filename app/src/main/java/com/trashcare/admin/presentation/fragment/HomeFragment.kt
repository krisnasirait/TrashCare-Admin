package com.trashcare.admin.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.trashcare.admin.databinding.FragmentHomeBinding
import com.trashcare.admin.presentation.activity.DetailTransactionActivity
import com.trashcare.admin.presentation.adapter.TrashListAdapter
import com.trashcare.admin.presentation.viewmodel.TrashCareAdminViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(),
    TrashListAdapter.OnItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val trashCareAdminViewModel: TrashCareAdminViewModel by viewModel()
    private lateinit var trashListAdapter: TrashListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupViewModelObservers()
    }

    private fun setupRecyclerView() {
        trashListAdapter = TrashListAdapter(this)
        binding.rvTrashTranscation.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = trashListAdapter
        }
    }

    private fun setupViewModelObservers() {
        trashCareAdminViewModel.getUserSubmission("Pending")

        trashCareAdminViewModel.listSubmission.observe(viewLifecycleOwner) {
            trashListAdapter.setData(it)
        }
    }

    override fun onResume() {
        super.onResume()
        trashCareAdminViewModel.getUserSubmission("Pending")
    }



    override fun onItemPlaceListClicked(id: String, userName: String, descSampah: String, status: String) {
        val intent = Intent(requireContext(), DetailTransactionActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("userName", userName)
        intent.putExtra("descSampah", descSampah)
        intent.putExtra("status", status)
        startActivity(intent)
    }
}
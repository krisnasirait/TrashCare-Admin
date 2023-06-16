package com.trashcare.admin.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.trashcare.admin.data.model.request.verification.VerificationActionBody
import com.trashcare.admin.databinding.ActivityDetailTransactionBinding
import com.trashcare.admin.presentation.viewmodel.TrashCareAdminViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTransactionBinding
    private val trashCareAdminViewModel: TrashCareAdminViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val id = intent.getStringExtra("id")
        val userName = intent.getStringExtra("userName")
        val description = intent.getStringExtra("descSampah")
        val status = intent.getStringExtra("status")

        binding.etId.setText("$id")
        binding.etNamaUser.setText("$userName")
        binding.etListSampah.setText("$description")
        binding.etStatus.setText("$status")

        binding.btnTolak.setOnClickListener {
            trashCareAdminViewModel.verifUser(id.toString(), VerificationActionBody("Tolak"))
            Toast.makeText(this, "Transaction $id ditolak", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnVerify.setOnClickListener {
            trashCareAdminViewModel.verifUser(id.toString(), VerificationActionBody("Verify"))
            Toast.makeText(this, "Transaction $id disetujui", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
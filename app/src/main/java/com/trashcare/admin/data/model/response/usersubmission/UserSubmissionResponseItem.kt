package com.trashcare.admin.data.model.response.usersubmission


import com.google.gson.annotations.SerializedName

data class UserSubmissionResponseItem(
    @SerializedName("Deskripsi")
    val deskripsi: String,
    @SerializedName("Jumlah")
    val jumlah: Int,
    @SerializedName("Lokasi")
    val lokasi: String,
    @SerializedName("Nama")
    val nama: String,
    @SerializedName("Nama bank")
    val namaBank: String,
    @SerializedName("Nama rekening")
    val namaRekening: String,
    @SerializedName("Nomor rekening")
    val nomorRekening: String,
    @SerializedName("Status")
    val status: String,
    @SerializedName("Trash ID")
    val trashID: String,
    @SerializedName("User ID")
    val userID: String
)
package com.trashcare.admin.data.model.response.verficiation


import com.google.gson.annotations.SerializedName

data class VerificationResponse(
    @SerializedName("action")
    val action: String
)
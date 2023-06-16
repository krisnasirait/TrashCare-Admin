package com.trashcare.admin.data.repository

import com.trashcare.admin.data.model.request.verification.VerificationActionBody
import com.trashcare.admin.data.model.response.usersubmission.UserSubmissionResponse
import com.trashcare.admin.data.model.response.verficiation.VerificationResponse
import com.trashcare.admin.data.remote.ApiService
import retrofit2.Response

class TrashAdminRepository(
    private val apiService: ApiService
) {

    suspend fun getUserSubmission(status: String) : Response<UserSubmissionResponse> {
        return apiService.getUserSubmission(status)
    }

    suspend fun verifyTranscation(trashId: String, verificationActionBody: VerificationActionBody) : Response<VerificationResponse> {
        return apiService.verifyUserSubmission(trashId, verificationActionBody)
    }

}
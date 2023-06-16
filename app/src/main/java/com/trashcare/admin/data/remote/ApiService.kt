package com.trashcare.admin.data.remote

import com.trashcare.admin.data.model.request.verification.VerificationActionBody
import com.trashcare.admin.data.model.response.usersubmission.UserSubmissionResponse
import com.trashcare.admin.data.model.response.verficiation.VerificationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface ApiService {

    @GET("apiusersubmission/usersubmission")
    suspend fun getUserSubmission(
        @Header("Status") status: String
    ): Response<UserSubmissionResponse>

    @PUT("apiadminverification/verification")
    suspend fun verifyUserSubmission(
        @Header("trashId") trashId: String,
        @Body action: VerificationActionBody
    ): Response<VerificationResponse>


}
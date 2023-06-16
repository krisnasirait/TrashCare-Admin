package com.trashcare.admin.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trashcare.admin.data.model.request.verification.VerificationActionBody
import com.trashcare.admin.data.model.response.usersubmission.UserSubmissionResponseItem
import com.trashcare.admin.data.model.response.verficiation.VerificationResponse
import com.trashcare.admin.data.repository.TrashAdminRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrashCareAdminViewModel(
    private val trashAdminRepository: TrashAdminRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listSubmission = MutableLiveData<List<UserSubmissionResponseItem?>>()
    val listSubmission: LiveData<List<UserSubmissionResponseItem?>> = _listSubmission

    private val _verifTransac = MutableLiveData<VerificationResponse?>()
    val verifTransac: LiveData<VerificationResponse?> = _verifTransac

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getUserSubmission(status: String) {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val response = trashAdminRepository.getUserSubmission(status)
                response.body()
            }.onSuccess { userSub ->
                withContext(Dispatchers.Main) {
                    _listSubmission.value = userSub
                }
            }.onFailure { throwable ->
                withContext(Dispatchers.Main) {
                    _errorMessage.value = throwable.message
                }
            }.also {
                withContext(Dispatchers.Main) {
                    _isLoading.postValue(false)
                }
            }
        }
    }

    fun verifUser(trashId: String, verificationActionBody: VerificationActionBody) {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val response = trashAdminRepository.verifyTranscation(trashId, verificationActionBody)
                response.body()
            }.onSuccess { verif ->
                withContext(Dispatchers.Main) {
                    _verifTransac.value = verif
                }
            }.onFailure { throwable ->
                withContext(Dispatchers.Main) {
                    _errorMessage.value = throwable.message
                }
            }.also {
                withContext(Dispatchers.Main) {
                    _isLoading.postValue(false)
                }
            }
        }
    }



}
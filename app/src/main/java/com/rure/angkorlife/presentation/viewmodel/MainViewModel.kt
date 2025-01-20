package com.rure.angkorlife.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rure.angkorlife.domain.repository.RetrofitRepository
import com.rure.angkorlife.domain.repository.RetrofitResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: RetrofitRepository
): ViewModel() {

    private val tag = "MainViewModel"

    private var id: String = ""



    fun login(id: String) {
        this.id = id
    }

    fun vote(candidateId: String) {
        viewModelScope.launch {
            repository.vote(id, candidateId)
        }

        //TODO: 리스트 업데이트 하기
    }

    fun doTest() {
        CoroutineScope(Dispatchers.IO).launch {
            with(repository) {
                val voteResult = vote(userId = "hello!", "50")
                Log.d(tag, "voteResult: ${voteResult}")

                val inquiryResult = inquiryCandidate("48", "userA")
                Log.d(tag, "inquiryResult: ${if(inquiryResult is RetrofitResult.Success) "Success" else false}")

                val getListResult = getCandidateList()
                Log.d(tag, "getListResult: ${if(getListResult is RetrofitResult.Success) "Success" else false}")
            }
        }
    }
}
package com.rure.angkorlife.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rure.angkorlife.data.entity.CandidateDetail
import com.rure.angkorlife.data.entity.CandidateProfile
import com.rure.angkorlife.domain.repository.RetrofitRepository
import com.rure.angkorlife.domain.repository.RetrofitResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: RetrofitRepository
): ViewModel() {

    private val tag = "MainViewModel"

    private var id: String = ""

    private val _candidateDetails = MutableStateFlow(listOf<CandidateDetail>())
    val candidateDetails get() = _candidateDetails.asStateFlow()

    private val _candidateProfiles = MutableStateFlow(listOf<CandidateProfile>())
    val candidateProfiles get() = _candidateProfiles.asStateFlow()

    init {
        Log.d(tag, "Created!")
    }

    fun login(id: String) {
        this.id = id
        loadCandidate()
    }

    fun vote(candidateId: String) {
        viewModelScope.launch {
            repository.vote(id, candidateId)
            loadCandidate()
        }

        _candidateDetails.value = candidateDetails.value.map {
            if(it.id == candidateId.toInt()) {
                it.copy(voted = true)
            } else it
        }
    }

    fun getCandidateProfile(candidateId: String): CandidateProfile? {
        return candidateProfiles.value.firstOrNull { it.id == candidateId.toInt() }
    }

    fun isVoted(candidateId: Int) = (candidateDetails.value.any { (it.id == candidateId) && it.voted })

    private fun loadCandidate() {
        viewModelScope.launch {
            when(val profileResult = repository.getCandidateList()) {
                is RetrofitResult.Success -> {
                    _candidateProfiles.value = profileResult.value
                    val asyncTask = mutableListOf<Deferred<CandidateDetail?>>()
                    profileResult.value.forEach {
                        asyncTask.add(
                            async {
                                when(val result = repository.inquiryCandidate(candidateId = it.id.toString(), userId = id)) {
                                    is RetrofitResult.Success -> result.value
                                    else -> null
                                }
                            }
                        )
                    }

                    _candidateDetails.value = asyncTask.awaitAll().filterNotNull()
                    Log.d(tag, "detail: ${_candidateDetails.value.joinToString { it.name ?: "null" }}")
                    Log.d(tag, "profile: ${candidateProfiles.value.joinToString { it.name ?: "null" }}")
                }
                is RetrofitResult.Failure -> {
                    Log.e(tag, "loadCandidate has Failed: ${profileResult.error.message}")
                }
            }
        }
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

                val getVoted = getMyVoteList(id)
                Log.d(tag, "getVoted: ${if(getVoted is RetrofitResult.Success) "Success: ${getVoted.value.joinToString()}" else false}")
            }
        }
    }
}
package com.rure.angkorlife.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rure.angkorlife.data.entity.CandidateDetail
import com.rure.angkorlife.data.entity.CandidateProfile
import com.rure.angkorlife.domain.repository.RetrofitRepository
import com.rure.angkorlife.domain.repository.RetrofitResult
import com.rure.angkorlife.presentation.state.ProfileData
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

    private val _candidateProfileData = MutableStateFlow(listOf<ProfileData>())
    val candidateProfileData get() = _candidateProfileData.asStateFlow()

    private val voteCnt = mutableIntStateOf(0)

    fun login(id: String) {
        _candidateProfileData.value = listOf()

        this.id = id
        loadCandidate()
    }

    fun vote(candidateId: String) {
        if(voteCnt.intValue >= 3) return
        viewModelScope.launch {
            repository.vote(id, candidateId)
            loadCandidate()
        }
    }

    fun getCandidateProfile(candidateId: String): ProfileData? {
        return _candidateProfileData.value.firstOrNull { it.candidateId == candidateId.toInt() }
    }

    private fun loadCandidate() {
        viewModelScope.launch {
            when(val result = repository.getCandidateProfileData(userId = id)) {
                is RetrofitResult.Success -> {
                    _candidateProfileData.value = result.value
                }
                is RetrofitResult.Failure -> {
                    Log.i(tag, "loadCandidate is failed: ${result.error.message}")
                }
            }
        }
    }
}
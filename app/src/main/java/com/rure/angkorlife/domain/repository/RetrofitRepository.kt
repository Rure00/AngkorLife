package com.rure.angkorlife.domain.repository

import com.rure.angkorlife.data.dto.SortType
import com.rure.angkorlife.data.entity.CandidateDetail
import com.rure.angkorlife.data.entity.CandidateProfile
import com.rure.angkorlife.data.entity.PageCandidateList
import com.rure.angkorlife.presentation.state.ProfileData

interface RetrofitRepository {
    suspend fun vote(userId: String, candidateId: String): Boolean
    //suspend fun inquiryCandidate(candidateId: String, userId: String): RetrofitResult<CandidateDetail>
    //suspend fun getCandidateList(): RetrofitResult<List<CandidateProfile>>
    suspend fun getCandidateProfileData(userId: String): RetrofitResult<List<ProfileData>>
    suspend fun getMyVoteList(userId: String): RetrofitResult<List<Int>>
}
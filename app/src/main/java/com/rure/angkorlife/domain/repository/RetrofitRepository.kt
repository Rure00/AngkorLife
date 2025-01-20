package com.rure.angkorlife.domain.repository

import com.rure.angkorlife.data.dto.SortType
import com.rure.angkorlife.data.entity.CandidateDetail
import com.rure.angkorlife.data.entity.PageCandidateList

interface RetrofitRepository {
    suspend fun vote(userId: String, candidateId: String): Boolean
    suspend fun inquiryCandidate(candidateId: String, userId: String): RetrofitResult<CandidateDetail>
    suspend fun getCandidateList(
        page: Int = 0,
        size: Int = 20,
        sort: List<SortType> = listOf(SortType.Empty)
    ): RetrofitResult<PageCandidateList>
}
package com.rure.angkorlife.data

import android.util.Log
import com.rure.angkorlife.data.datasource.RetrofitDataSource
import com.rure.angkorlife.data.dto.CandidateListRequestDto
import com.rure.angkorlife.data.dto.SortType
import com.rure.angkorlife.data.dto.VoteRequestDto
import com.rure.angkorlife.data.entity.CandidateDetail
import com.rure.angkorlife.data.entity.PageCandidateList
import com.rure.angkorlife.domain.repository.RetrofitRepository
import com.rure.angkorlife.domain.repository.RetrofitResult
import javax.inject.Inject

class RetrofitRepositoryImpl @Inject constructor(
    private val dataSource: RetrofitDataSource
): RetrofitRepository  {

    private val tag = "RetrofitRepositoryImpl"


    override suspend fun vote(userId: String, candidateId: String): Boolean {
        val result = dataSource.vote(
            VoteRequestDto(userId = userId, id = candidateId)
        )

        return result.isSuccess
    }

    override suspend fun inquiryCandidate(
        candidateId: String,
        userId: String
    ): RetrofitResult<CandidateDetail> {
        val result = dataSource.inquiryCandidate(id = candidateId, userId = userId)
        val body = result.getOrNull()
        return if(result.isSuccess &&  body != null) {
            RetrofitResult.Success(body)
        } else {
            RetrofitResult.Failure(
                result.exceptionOrNull() ?: Exception("inquiryCandidate) Body is null $tag")
            )
        }
    }

    override suspend fun getCandidateList(
        page: Int,
        size: Int,
        sort: List<SortType>
    ): RetrofitResult<PageCandidateList> {
        val result = dataSource.getCandidateList(
            candidateListRequestDto = CandidateListRequestDto(
                page = page,
                size = size,
                sort = sort
            )
        )
        val body = result.getOrNull()

        return if(result.isSuccess && body != null) {
            RetrofitResult.Success(body)
        } else {
            RetrofitResult.Failure(
                result.exceptionOrNull() ?: Exception("getCandidateList) Body is null $tag")
            )
        }

    }
}
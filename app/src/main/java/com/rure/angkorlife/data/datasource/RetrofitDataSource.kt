package com.rure.angkorlife.data.datasource

import android.util.Log
import com.google.gson.Gson
import com.rure.angkorlife.data.RetrofitService
import com.rure.angkorlife.data.dto.BaseExceptionDto
import com.rure.angkorlife.data.dto.CandidateListRequestDto
import com.rure.angkorlife.data.dto.VoteRequestDto
import com.rure.angkorlife.data.entity.CandidateDetail
import com.rure.angkorlife.data.entity.PageCandidateList
import javax.inject.Inject


class RetrofitDataSource @Inject constructor(
    private val service: RetrofitService
) {
    private val tag = "RetrofitDataSource"

    suspend fun vote(voteRequestDto: VoteRequestDto): Result<Boolean> {
        val response = service.vote(voteRequestDto)
        return if(response.isSuccessful) {
            Result.success(true)
        } else {
            val error = parseErrorBody(response.errorBody()?.string())
            Log.i(tag, "vote failed(${error.errorCode}): ${error.errorMessage}")
            Result.failure(Exception(error.errorMessage))
        }
    }

    suspend fun inquiryCandidate(id: String, userId: String): Result<CandidateDetail> {
        val response = service.inquiryCandidate(id, userId)
        return if(response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            val error = parseErrorBody(response.errorBody()?.string())
            Log.i(tag, "inquiryCandidate failed(${error.errorCode}): ${error.errorMessage}")
            Result.failure(Exception(error.errorMessage))
        }
    }

    suspend fun getCandidateList(candidateListRequestDto: CandidateListRequestDto): Result<PageCandidateList> {
        val response = service.getCandidateList(candidateListRequestDto)
        return if(response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            val error = parseErrorBody(response.errorBody()?.string())
            Log.i(tag, "getCandidateList failed(${error.errorCode}): ${error.errorMessage}")
            Result.failure(Exception(error.errorMessage))
        }
    }

    private fun parseErrorBody(errorBody: String?): BaseExceptionDto {
        return try {
            Gson().fromJson(errorBody, BaseExceptionDto::class.java)
        } catch (e: Exception) {
            BaseExceptionDto(400, errorBody ?: "No error message available")
        }
    }
}
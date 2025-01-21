package com.rure.angkorlife.data

import com.rure.angkorlife.data.dto.CandidateListRequestDto
import com.rure.angkorlife.data.dto.VoteRequestDto
import com.rure.angkorlife.data.entity.CandidateDetail
import com.rure.angkorlife.data.entity.PageCandidateList
import com.rure.angkorlife.data.entity.PageableObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @POST("vote")
    suspend fun vote(
        @Body voteRequestDto: VoteRequestDto
    ): Response<Unit>

    @GET("vote/candidate/{id}")
    suspend fun inquiryCandidate(
        @Path("id") id: String,
        @Query("userId") userId: String
    ): Response<CandidateDetail>


    @GET("vote/candidate/list")
    suspend fun getCandidateList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: List<String>,
    ): Response<PageCandidateList>


    @GET("vote/voted/candidate/list")
    suspend fun getVoteHistory(
        @Query("userId") userId: String
    ): Response<List<Int>>
}
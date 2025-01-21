package com.rure.angkorlife.data

import com.rure.angkorlife.data.datasource.RetrofitDataSource
import com.rure.angkorlife.data.dto.CandidateListRequestDto
import com.rure.angkorlife.data.dto.SortType
import com.rure.angkorlife.data.dto.VoteRequestDto
import com.rure.angkorlife.data.entity.CandidateDetail
import com.rure.angkorlife.domain.repository.RetrofitRepository
import com.rure.angkorlife.domain.repository.RetrofitResult
import com.rure.angkorlife.presentation.state.ProfileData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
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

    override suspend fun getCandidateProfileData(userId: String): RetrofitResult<List<ProfileData>> {
        val listResult = dataSource.getCandidateList(candidateListRequestDto = CandidateListRequestDto(
                page = 0,
                size = 1000,
                sort = listOf(SortType.CandidateNumberASC)
            )
        )
        val listBody = listResult.getOrNull()
            ?: return RetrofitResult.Failure(
                listResult.exceptionOrNull() ?: Exception("getCandidateProfileData) Body is null $tag")
            )

        val task = mutableListOf<Deferred<CandidateDetail?>>()
        withContext(Dispatchers.IO) {
            listBody.content.forEach {
                task.add(
                    async { dataSource.inquiryCandidate(id = it.id.toString(),userId = userId).getOrNull() }
                )
            }
        }

        val list = task.awaitAll().filterNotNull()

        return if(list.isEmpty()) {
            RetrofitResult.Failure(Exception("getCandidateProfileData) Body is null $tag"))
        } else {
            RetrofitResult.Success(
                list.mapIndexed { index, item ->
                    ProfileData(
                        name = item.name,
                        candidateId = item.id,
                        candidateNumber = item.candidateNumber,
                        profileThumbnail = listBody.content[index].profileUrl,
                        profileUrls = item.profileInfoList.map { it.profileUrl },
                        voteCnt = listBody.content[index].voteCnt.toInt(),
                        voted = item.voted,
                        country = item.country,
                        education = item.education,
                        major = item.major,
                        hobby = item.hobby,
                        talent = item.talent,
                        ambition = item.ambition,
                        contents = item.contents,
                        regDt = item.regDt
                    )
                }
            )
        }
    }

    override suspend fun getMyVoteList(userId: String): RetrofitResult<List<Int>> {
        val result = dataSource.getVoted(userId = userId)
        val body = result.getOrNull()
        return if(result.isSuccess && body != null) {
            RetrofitResult.Success(body)
        } else {
            RetrofitResult.Failure(
                result.exceptionOrNull() ?: Exception("getMyVoteList) Body is null $tag")
            )
        }
    }

//    override suspend fun inquiryCandidate(
//        candidateId: String,
//        userId: String
//    ): RetrofitResult<CandidateDetail> {
//        val result = dataSource.inquiryCandidate(id = candidateId, userId = userId)
//        val body = result.getOrNull()
//        return if(result.isSuccess &&  body != null) {
//            RetrofitResult.Success(body)
//        } else {
//            RetrofitResult.Failure(
//                result.exceptionOrNull() ?: Exception("inquiryCandidate) Body is null $tag")
//            )
//        }
//    }
//
//    override suspend fun getCandidateList(): RetrofitResult<List<CandidateProfile>> {
//        val result = dataSource.getCandidateList(
//            candidateListRequestDto = CandidateListRequestDto(
//                page = 0,
//                size = 1000,
//                sort = listOf(SortType.CandidateNumberASC)
//            )
//        )
//        val body = result.getOrNull()
//
//        return if(result.isSuccess && body != null) {
//            RetrofitResult.Success(body.content)
//        } else {
//            RetrofitResult.Failure(
//                result.exceptionOrNull() ?: Exception("getCandidateList) Body is null $tag")
//            )
//        }
//
//    }


}
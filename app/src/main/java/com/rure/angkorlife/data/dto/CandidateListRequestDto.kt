package com.rure.angkorlife.data.dto

data class CandidateListRequestDto(
    val page: Int,
    val size: Int,
    val sort: List<SortType>
)

enum class SortType(val value: String) {
    Empty(""),

    NameASC("name,ASC"),
    NameDESC("name,DESC"),

    CandidateNumberASC("candidateNumber,ASC"),
    CandidateNumberDESC("candidateNumberDESC"),

    VoteCntASC("voteCnt,ASC"),
    VoteCntDESC("voteCnt,DESC")
}
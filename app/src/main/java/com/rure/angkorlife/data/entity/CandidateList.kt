package com.rure.angkorlife.data.entity

data class CandidateList(
    val id: Int, // 후보자 ID
    val candidateNumber: Int, // 후보자 등록 번호
    val name: String, // 후보자 이름
    val profileUrl: String, // 후보 프로필 이미지 URL
    val voteCnt: String // 후보자 득표 수
)

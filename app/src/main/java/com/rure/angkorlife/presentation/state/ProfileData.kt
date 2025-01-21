package com.rure.angkorlife.presentation.state

data class ProfileData(
    val name: String,
    val candidateId: Int,
    val candidateNumber: Int,
    val profileUrl: String,
    val voteCnt: Int,
    val voted: Boolean,

    val country: String, // 국가
    val education: String, // 학교 명
    val major: String, // 전공
    val hobby: String, // 취미
    val talent: String, // 장기
    val ambition: String, // 포부
    val contents: String, // 상세 내용
    val regDt: String, // 후보자 등록일
)

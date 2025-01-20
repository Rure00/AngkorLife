package com.rure.angkorlife.data.entity

data class CandidateDetail(
    val id: Int, // 후보자 ID
    val candidateNumber: Int, // 후보자 투표 등록 번호
    val name: String, // 후보자 이름
    val country: String, // 국가
    val education: String, // 학교 명
    val major: String, // 전공
    val hobby: String, // 취미
    val talent: String, // 장기
    val ambition: String, // 포부
    val contents: String, // 상세 내용
    val profileInfoList: List<ProfileInfo>, // 프로필 Info 리스트
    val regDt: String, // 후보자 등록일
    val voted: Boolean // 투표 여부
)


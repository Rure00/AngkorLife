package com.rure.angkorlife.data.entity

data class ProfileInfo(
    val fileArea: Int, // 파일 영역
    val displayOrder: Int, // 정렬 순서
    val profileUrl: String, // 프로필 URL
    val mimeType: String // 파일 타입
)
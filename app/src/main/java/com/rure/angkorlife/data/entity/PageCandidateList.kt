package com.rure.angkorlife.data.entity

data class PageCandidateList(
    val totalPages: Int, // 총 페이지 수
    val totalElements: Long, // 총 요소 수
    val size: Int, // 페이지 크기
    val content: List<CandidateList>, // 후보자 리스트
    val number: Int, // 현재 페이지 번호
    val sort: SortObject, // 정렬 정보
    val pageable: PageableObject, // 페이지 네이션 정보
    val numberOfElements: Int, // 현재 페이지의 요소 수
    val first: Boolean, // 첫 번째 페이지 여부
    val last: Boolean, // 마지막 페이지 여부
    val empty: Boolean // 페이지가 비어있는지 여부
)

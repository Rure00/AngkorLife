package com.rure.angkorlife.data.entity

data class PageableObject(
    val offset: Long, // 데이터 시작 위치
    val sort: SortObject, // 정렬 정보
    val pageNumber: Int, // 현재 페이지 번호
    val pageSize: Int, // 페이지 크기
    val paged: Boolean, // 페이징 처리 여부
    val unpaged: Boolean // 페이징 미적용 여부
)
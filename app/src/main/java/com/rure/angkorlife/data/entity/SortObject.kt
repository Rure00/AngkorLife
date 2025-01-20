package com.rure.angkorlife.data.entity

data class SortObject(
    val empty: Boolean, // 정렬이 비어있는지 여부
    val sorted: Boolean, // 정렬 여부
    val unsorted: Boolean // 정렬되지 않은 여부
)
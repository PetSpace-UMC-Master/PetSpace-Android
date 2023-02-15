package com.example.petsapce_week1.vo

data class ReviewGetData22(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: Result
)

data class Result(
    val content: List<Content>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: Pageable,
    val size: Int,
    val sort: SortX,
    val totalElements: Int,
    val totalPages: Int
)

data class Content(
    val content: String,
    val createdDate: String,
    val createdTime: String,
    val id: Int,
    val nickName: String,
    val reviewImage: List<ReviewImage>,
    val score: Int,
    val status: String
)

data class Pageable(
    val offset: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val paged: Boolean,
    val sort: SortX,
    val unpaged: Boolean
)

data class SortX(
    val empty: Boolean,
    val sorted: Boolean,
    val unsorted: Boolean
)

data class ReviewImage(
    val id: Int,
    val reviewImageUrl: String
)
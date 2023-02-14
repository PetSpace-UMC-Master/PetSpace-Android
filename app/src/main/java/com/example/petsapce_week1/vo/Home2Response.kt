package com.example.petsapce_week1.vo



data class Home2Response(
    val isSuccess: Boolean?,
    val responseCode: Int?,
    val responseMessage: String?,
    val result: List<searchResult>
){
    data class searchResult(
        val averageReviewScore: Float,
        val city: String,
        val district: String,
        val numberOfReview: Int,
        val price: Int,
        val roomId: Int,
        val roomImages: List<String>
    )
}

//curl --location --request GET 'http://localhost:8080/app/rooms/filtering?startDay=2022-01-01&endDay=2022-01-02&keyword=분' \
//--data-raw ''
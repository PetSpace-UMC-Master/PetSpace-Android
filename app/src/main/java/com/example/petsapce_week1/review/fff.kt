package com.example.petsapce_week1.review
/*

class fff {
}

fun postReview(request: PostReviewRequest){
    var fileToUpload = arrayListOf<MultipartBody.Part>()
    for(img in request.images){
        var fileName = "testingFromAndroid"+System.nanoTime().toString().subSequence(0, 5)+".png"
        var requestBody = img.asRequestBody("image/jpeg".toMediaTypeOrNull())
        fileToUpload.add(MultipartBody.Part.createFormData("images", fileName, requestBody))
    }

    val service = ApplicationClass.sRetrofit.create(ReviewRetrofitInterface::class.java)
    service.postReview(request.userId, request.title, request.content, request.point_Type,
        request.location, request.creature, request.point_date, fileToUpload).enqueue(object : Callback<ReviewResponse> {
        override fun onResponse(call: Call<ReviewResponse>, response: Response<ReviewResponse>) {
            reviewActivityInterface.onGetUserSuccess(response.body() as ReviewResponse)
        }

        override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
            reviewActivityInterface.onGetUserFailure(t.message ?: "통신 오류")
        }
    })
}

@Multipart
@POST("app/points")
fun postReview(
    @Part("userId") userId:String ?= "2646841646",
    @Part("title") title:String,
    @Part("content") content:String,
    @Part("point_type") point_type:String,
    @Part("location") location:String = "Testing Android",
    @Part("creature") creature:String,
    @Part("point_date") point_date:String,
    @Part images: List<MultipartBody.Part>?
) : Call<ReviewResponse>

data class PostReviewRequest(
    var userId:String ?= null,
    var title:String,
    var content:String,
    var point_Type:String,
    var location:String,
    var creature:String,
    var point_date:String,
    var images:ArrayList<File>
): Serializable {}*/

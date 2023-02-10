package com.example.petsapce_week1.review

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.petsapce_week1.TestMainActivity
import com.example.petsapce_week1.databinding.ReviewCreateBinding
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.network.ReviewAPI
import com.example.petsapce_week1.vo.ReviewPostData
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

// okhttp로 서버 연결 수정함 (retrofithelper)
class ReviewPostActivity_3 : AppCompatActivity() {
    private lateinit var binding: ReviewCreateBinding

    //private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api = RetrofitHelper.createBaseService(ReviewAPI::class.java)
//    var api: ReviewAPI = retrofit.create(ReviewAPI::class.java)


    private var success_review_id: Int? = null
    private var review_rate: Int? = null
    private var mediaPath: String? = null
    // private var bitmap: Bitmap? = null

    //비트맵 + 압축
/*    inner class BitmapRequestBody(private val bitmap: Bitmap) : RequestBody() {
        override fun contentType(): MediaType = "image/jpeg".toMediaType()
        override fun writeTo(sink: BufferedSink) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 99, sink.outputStream())
        }

    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ReviewCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이전 버튼
        initPrevious()
        // 별점 정수로 환산
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, _ ->
            ratingBar.rating = rating
            review_rate = rating.toInt()
        }
        // 디바이스 갤러리 오픈
        binding.openGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            activityResult.launch(intent)
        }

        // 리뷰 작성하기 버튼 누르면 통신 시작 !
        binding.btnReviewCreate.setOnClickListener {
            // 토큰
            var accessToken: String? = null
            val atpref = getSharedPreferences("accessToken", MODE_PRIVATE)
            if (atpref != null) {
                accessToken = atpref.getString("accessToken", "default")
            }
            val accessTokenPost = "Bearer $accessToken"
            Log.d("token", "$accessTokenPost")

            // 리뷰 내용
            val content = binding.reviewInput.text.toString()
            val contentRequestBody: RequestBody = content.toPlainRequestBody()
            Log.d("멀티파트에 담긴 content", "$contentRequestBody")

            // 리뷰 별점
            val reviewRateRequestBody: RequestBody = review_rate.toString().toPlainRequestBody()
            Log.d("멀티파트에 담긴 reviewRate", "$reviewRateRequestBody")

            // 리뷰 내용 + 별점 해쉬맵으로 묶기
            val textHashMap = hashMapOf<String, RequestBody>()
            textHashMap["content"] = contentRequestBody
            textHashMap["score"] = reviewRateRequestBody

            //리뷰 사진
            val file = File(mediaPath)
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
                //file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val image = MultipartBody.Part.createFormData("reviewImages", file.name, requestFile)
            Log.d("멀티파트에 담긴 reviewImages", image.body.toString())

            // 비트맵
            /*       var inputStream: InputStream? = null
                   try {
                       inputStream = openFileInput(file.name)
                   } catch (e: IOException) {
                       e.printStackTrace()
                   }
                   val bitmap = BitmapFactory.decodeStream(inputStream)

                   // 압축 후 멀티파트에 넣기
                   val bitmapRequestBody = bitmap?.let { BitmapRequestBody(it) }
                   val bitmapMultipartBody: MultipartBody.Part? =
                       if (bitmapRequestBody == null) null
                       else MultipartBody.Part.createFormData("reviewImages", file.name, bitmapRequestBody)*/

            // 통신 부분
            api.post_reviews(accessTokenPost, textHashMap, image)
                .enqueue(object : Callback<ReviewPostData> {
                    // 통신 성공
                    override fun onResponse(
                        call: Call<ReviewPostData>,
                        response: Response<ReviewPostData>
                    ) {
                        Log.d("리뷰 포스트 통신 성공", response.toString())
                        Log.d("리뷰 포스트 통신 성공", response.body().toString())
                        Log.d("통신 완료된 해쉬맵(내용, 별점)", textHashMap.toMap().toString())
                        Log.d("통신 완료된 사진", image.body.toString())
                       // Log.d("통신 완료된 사진", images.body.contentType().toString())
                        Log.d("파일 경로", "$mediaPath")

                        //Log.d("리뷰 사진사진 바디 - 컨텐츠 타입", bitmapMultipartBody.contentType().toString())

                        val body = response.body()
                        if (body != null) {
                            success_review_id = body.result.id
                            Log.d(
                                "응답 받은 리뷰ID (포스트 된 리뷰ID)",
                                "{${success_review_id.toString()}}"
                            )
                        }
                    }

                    // 통신 실패
                    override fun onFailure(call: Call<ReviewPostData>, t: Throwable) {
                        Log.d("리뷰 포스트 통신 실패", "failed")
                        Log.d("실패 원인", t.toString())
                    }
                })
        }
    } //oncreate 여기까지


    // URI로부터 이미지 경로 추출
    fun getRealPathFromURI(uri: Uri): String {
        val buildName = Build.MANUFACTURER
        if (buildName.equals("Xiaomi")) {
            return uri.path.toString()
        }
        var columnIndex = 0
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        var cursor = contentResolver.query(uri, proj, null, null, null)
        if (cursor!!.moveToFirst()) {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }
        return cursor.getString(columnIndex)
    }

    // 디바이스에서 사진 불러와서 띄우기
    val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            val uri = it.data!!.data
            mediaPath = getRealPathFromURI(it.data?.data!!)

            Glide.with(this)
                .load(uri)
                .into(binding.selectedImage)

            //fileToUpload()
        }
    }

    // 이전 화면
    fun initPrevious() {
        binding.apply {
            btnBack.setOnClickListener {
                val intent = Intent(this@ReviewPostActivity_3, TestMainActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun String?.toPlainRequestBody() =
        requireNotNull(this).toRequestBody("text/plain".toMediaTypeOrNull())


    // 작성하기 클릭 시 서버에 데이터 보내는 함수
    private fun sendReviewRequest() {
    }
}


/*
var inputStream: InputStream? = null
try {
    inputStream = getContext().getContentResolver().openInputStream(uri)
} catch (e: IOException) {
    e.printStackTrace()
}
val bitmap = BitmapFactory.decodeStream(inputStream)

val requestFile = file.BitmapRequestBody(bitmap)

val byteArrayOutputStream: ByteArrayOutputStream =
    ByteArrayOutputStream() bitmap.compress Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
var requestFile: ReviewPostActivity.BitmapRequestBody? = create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())
*/



/*
private fun <I> ActivityResultLauncher<I>.launch() {
    TODO("Not yet implemented")
}
private val requestPermissionLauncher =
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            //리뷰 사진
            val file = File(mediaPath)
            Log.d("Path", "$mediaPath")
            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            val images =
                MultipartBody.Part.createFormData("reviewImages", file.name, requestFile)
        } else {
            Log.d("권한 실패", "--");
        }
    }*/


/*           == 백엔드 통신 부분 ==
          val file: File = File(mediaPath)
          var inputStream: InputStream? = null try {
          inputStream = getContext().getContentResolver().openInputStream(photoUri)
      } catch (e: IOException) {
          e.printStackTrace()
      }
          val bitmap = BitmapFactory.decodeStream(inputStream)
          val byteArrayOutputStream: ByteArrayOutputStream =
              ByteArrayOutputStream() bitmap.compress Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)   var requestBody: RequestBody? = create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())   var uploadFile: Part? = createFormData.createFormData("postImg", file.getName(), requestBody)
          val content = binding.reviewInput.text.toString()

          /*val requestPermissionLauncher =
              registerForActivityResult(
                  ActivityResultContracts.RequestPermission()
              ) { isGranted: Boolean ->
                  if (isGranted) {
                      sendReviewRequest()

                       리뷰 사진
                      val file = File(mediaPath)
                      Log.d("Path", "$mediaPath")
                      val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                      val images = MultipartBody.Part.createFormData("reviewImages", file.name, requestFile)

                      api.post_reviews(accessTokenPost, textHashMap, images)
                          .enqueue(object : Callback<ReviewPostData> {
                              override fun onResponse(
                                  call: Call<ReviewPostData>,
                                  response: Response<ReviewPostData>
                              ) {
                                  Log.d("리뷰 포스트 통신 성공", response.toString())
                                  Log.d("리뷰 포스트 통신 성공", response.body().toString())
                                  Log.d("해쉬맵 내용", textHashMap.toMap().toString())
                                  Log.d("사진 내용", images.body.toString())
                                  //  Log.d("리뷰 사진사진 바디 - 컨텐츠 타입", bitmapMultipartBody.contentType().toString())

                                  //Log.d("점수", bitmapMultipartBody.toString())

                                  val body = response.body()
                                  if (body != null) {
                                      success_review_id = body.result.id
                                      Log.d(
                                          "응답 받은 리뷰ID (포스트 된 리뷰ID)",
                                          "{${success_review_id.toString()}}"
                                      )
                                  }
                              }

                              override fun onFailure(call: Call<ReviewPostData>, t: Throwable) {
                                  Log.d("리뷰 포스트 통신 실패", "failed")
                                  Log.d("ERROR", t.toString())
                              }
                          })

                  } else {
                      Log.d("권한 실패", "--");
                  }
              }
      }
  }




/*        val bitmapRequestBody = bitmap?.let { BitmapRequestBody(it)}
      val bitmapMultipartBody: MultipartBody.Part? =
          if (bitmapRequestBody == null) null
          else MultipartBody.Part.createFormData("reviewImages", file.name, bitmapRequestBody)*/


      //Log.d("리뷰 사진사진 바디 - 컨텐츠 타입", images.body.contentType().toString())
      // 서버에 보내기
      //val response = requestFile.let {




/*    private fun fileToUpload() {
      if (mediaPath != null) {
          val file = File(mediaPath)
          // image/jpeg 타입은 MIME 타입을 따르기 위함이다. 일반적인 String같은 경우 text/plain과 같이 쓴다.
          val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
          MultipartBody.Part.createFormData("images", file.name, requestBody)
          // 우리 프로젝트에서는 이미지 파일이 없으면 null로 넘겨주기로 약속했기 때문에 이미지 경로가 없으면 null처리 해준다.
      } else {
          null
      }
  }*/



//val response = api.addActivity(bitmapMultipartBody, textHashMap).awaitResponse()





/*    private fun openGallery() {
val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
intent.type = "image/*"
startActivity()
startActivityForResult(intent, OPEN_GALLERY)
}*/


          val data = ReviewDTO(content, review_rate, fileToUpload())*//*


          api.post_reviews(data, image).enqueue(object : Callback<ReviewData> {
              override fun onResponse(
                  call: Call<ReviewData>,
                  response: Response<ReviewData>
              ) {
                  Log.d("리뷰 포스트 통신 성공", response.toString())
                  Log.d("리뷰 포스트 성공", response.body().toString())
                  Log.d("내용", content)
                  Log.d("점수", review_rate.toString())

                  val body = response.body()
                  if (body != null) {
                      success_review_id = body.result.id
                      Log.d("포스트 된 리뷰 아이디", "{${success_review_id.toString()}}")
                  }
              }

              override fun onFailure(call: Call<ReviewData>, t: Throwable) {
                  Log.d("리뷰 포스트", "failed")
              }
          })*/
package com.example.petsapce_week1.review

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.*


//비트맵 방법

class ReviewPostActivity4 : AppCompatActivity() {
    private lateinit var binding: ReviewCreateBinding
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api: ReviewAPI = retrofit.create(ReviewAPI::class.java)
    private var success_review_id: Int? = null
    private var review_rate: Int? = null
    private var mediaPath: String? = null
    // private var bitmap: Bitmap? = null

     //비트맵 + 압축
    inner class BitmapRequestBody(private val bitmap: Bitmap) : RequestBody() {
        override fun contentType(): MediaType = "image/jpeg".toMediaType()
        override fun writeTo(sink: BufferedSink) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 99, sink.outputStream())
            Log.d("비트맵 압축", bitmap.toString())
        }
    }

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
            if (atpref != null) { accessToken = atpref.getString("accessToken", "default") }
            val accessTokenPost = "Bearer $accessToken"
            Log.d("token", "$accessTokenPost")

            // 리뷰 내용
            val content = binding.reviewInput.text.toString()
            val contentRequestBody: RequestBody = content.toPlainRequestBody()
            Log.d("contentRequestBody", "$contentRequestBody")

            // 리뷰 별점
            val reviewRateRequestBody: RequestBody = review_rate.toString().toPlainRequestBody()
            Log.d("reviewRateRequestBody", "$reviewRateRequestBody")

            // 리뷰 내용 + 별점 해쉬맵으로 묶기
            val textHashMap = hashMapOf<String, RequestBody>()
            textHashMap["content"] = contentRequestBody
            textHashMap["score"] = reviewRateRequestBody


                        //리뷰 사진 - 비트맵
                val file = File(mediaPath)

                Log.d("비트맵 압축1", mediaPath.toString())
                var inputStream: InputStream? = null
                Log.d("input stream0", inputStream.toString())
                try {
                    Log.d("파일 이름", file.name) //o
                    Log.d("파일 절대경로", file.absolutePath) //o
                    inputStream = FileInputStream(file.name)
                    Log.d("input stream1", inputStream.readBytes().toString()) //x

                } catch (e: Exception) { // o
                    e.printStackTrace()
                    Log.d("에러에러", e.toString())
                }
                val bitmap = BitmapFactory.decodeStream(inputStream)
                //Log.d("비트맵 압축2", bitmap.toString()) //x, bitmap 널인가봄


            // 압축 후 멀티파트에 넣기
                val bitmapRequestBody = bitmap?.let { BitmapRequestBody(it) }

                val bitmapMultipartBody: MultipartBody.Part? =
                    if (bitmapRequestBody == null) {
                        Log.d("비트맵 압축 실패", "nulll")
                        null
                    }
                    else {
                        Log.d("비트맵 압축 성공", "NOT nulll")
                        MultipartBody.Part.createFormData("reviewImages", file.name, bitmapRequestBody)
                    }
            //requestPermissionLauncher.launch()
            
            // 통신 부분

                api.post_reviews(accessTokenPost, textHashMap, bitmapMultipartBody)//bitmapMultipartBody
                    .enqueue(object : Callback<ReviewPostData> {
                        // 통신 성공
                        override fun onResponse(
                            call: Call<ReviewPostData>,
                            response: Response<ReviewPostData>
                        ) {
                            Log.d("리뷰 포스트 통신 성공", response.toString())
                            Log.d("리뷰 포스트 통신 성공", response.body().toString())
                            Log.d("통신 완료된 해쉬맵(내용, 별점)", textHashMap.toMap().toString())
                            Log.d("통신 완료된 사진", bitmapMultipartBody.toString())
                            Log.d("파일 경로", "$mediaPath")

                            //Log.d("리뷰 사진사진 바디 - 컨텐츠 타입", bitmapMultipartBody.contentType().toString())
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

                        // 통신 실패
                        override fun onFailure(call: Call<ReviewPostData>, t: Throwable) {
                            Log.d("리뷰 포스트 통신 실패", "failed")
                            Log.d("실패 원인", t.toString())
                        }
                    })
            }
        }
     //oncreate 여기까지



    // 후에 삭제할 임시 파일 경로
    var tempFileList: ArrayList<String> = ArrayList()

    /* 이미지 파일을 복사한 후, 그 파일의 절대 경로 반환하는 메소드 */
    fun createCopyAndReturnRealPath(uri: Uri?, fileName: String): String? {
        val contentResolver = contentResolver ?: return null
        // 내부 저장소 안에 위치하도록 파일 생성
        val filePath =
            applicationInfo.dataDir + File.separator + System.currentTimeMillis() + "." + fileName.substring(
                fileName.lastIndexOf(".") + 1
            )
        val file = File(filePath)
        // 서버에 이미지 업로드 후 삭제하기 위해 경로를 저장해둠
        tempFileList.add(filePath)
        try {
            // 매개변수로 받은 uri 를 통해  이미지에 필요한 데이터를 가져온다.
            val inputStream = contentResolver.openInputStream(uri!!) ?: return null
            // 가져온 이미지 데이터를 아까 생성한 파일에 저장한다.
            val outputStream: OutputStream = FileOutputStream(file)
            val buf = ByteArray(1024)
            var len: Int
            while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
            outputStream.close()
            inputStream.close()
        } catch (ignore: IOException) {
            return null
        }
        return file.absolutePath // 생성한 파일의 절대경로 반환
    }


 /*   *//* 서버에 이미지를 업로드하는 메소드(Volley+ 이용) *//*
    private fun uploadImage() {
        // 안드로이드에서 보낼 데이터를 받을 php 서버 주소
        val serverUrl = "https://www.sample.com/save_image.php"

        //파일 전송 요청 객체 생성
        val smpr =
            SimpleMultiPartRequest(Request.Method.POST, serverUrl, object : Listener<String?>() {
                fun onResponse(response: String?) {
                    try {
                        val jsonObject = JSONObject(response)
                        val success = jsonObject.getBoolean("success")
                        if (success) {
                            // 업로드 성공
                            Toast.makeText(
                                this@WritePostActivity,
                                "이미지가 업로드되었습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                            // 임시파일 삭제
                            for (i in 0 until tempFileList.size()) {
                                val tempFile = File(tempFileList[i])
                                if (tempFile.exists()) {
                                    tempFile.delete()
                                }
                            }
                        } else {
                            // 업로드 실패
                            Toast.makeText(
                                this@WritePostActivity,
                                "이미지 업로드를 실패하였습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }
                }
            }, object : ErrorListener() {
                fun onErrorResponse(error: VolleyError?) {
                    Toast.makeText(
                        this@WritePostActivity,
                        "서버와 통신 중 오류가 발생했습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        // 요청 객체를 서버로 보낼 객체 생성
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(smpr)
    }
*/


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

            val file = File(mediaPath)

            createCopyAndReturnRealPath(uri, file.name)

            Glide.with(this)
                .load(uri)
                .into(binding.selectedImage)


/*            val body: MultipartBody.Part =
                MultipartBody.Part.createFormData("reviewImages", file.name, requestFile)*/

            //fileToUpload()
        }
    }

    // 이전 화면
     fun initPrevious() {
        binding.apply {
            btnBack.setOnClickListener {
                val intent = Intent(this@ReviewPostActivity4, TestMainActivity::class.java)
                startActivity(intent)
            }
        }
    }

     fun String?.toPlainRequestBody() =
        requireNotNull(this).toRequestBody("text/plain".toMediaTypeOrNull())
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


  // 작성하기 클릭 시 서버에 데이터 보내는 함수
  //private fun sendReviewRequest() {
      // 토큰 저장 객체
/*        var accessToken: String? = null
      val atpref = getSharedPreferences("accessToken", MODE_PRIVATE)
      if (atpref != null) {
          accessToken = atpref.getString("accessToken", "default")
      }

      val accessTokenPost = "Bearer $accessToken"
      Log.d("token", "$accessTokenPost")

      // 리뷰 내용
      val content = binding.reviewInput.text.toString()
      val contentRequestBody: RequestBody = content.toPlainRequestBody()
      Log.d("contentRequestBody", "$contentRequestBody")

      // 리뷰 별점
      val reviewRateRequestBody: RequestBody = review_rate.toString().toPlainRequestBody()
      Log.d("reviewRateRequestBody", "$reviewRateRequestBody")

      val textHashMap = hashMapOf<String, RequestBody>()
      textHashMap["content"] = contentRequestBody
      textHashMap["score"] = reviewRateRequestBody*/



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
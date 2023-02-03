package com.example.petsapce_week1.review

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.petsapce_week1.TestMainActivity
import com.example.petsapce_week1.databinding.ReviewCreateBinding
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.network.ReviewAPI
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import retrofit2.Retrofit
import java.io.File


class ReviewPostActivity : AppCompatActivity() {
    private lateinit var binding: ReviewCreateBinding
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api: ReviewAPI = retrofit.create(ReviewAPI::class.java)
    private var success_review_id: Int? = null
    private var review_rate: Int? = null
    private var mediaPath: String ?= null

    val content = binding.reviewInput.text.toString()


    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK && it.data != null) {
            val uri = it.data!!.data
            mediaPath = getRealPathFromURI(it.data?.data!!)

            Glide.with(this)
                .load(uri)
                .into(binding.selectedImage)
            //val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            //val body: MultipartBody.Part = MultipartBody.Part.createFormData("photo", "photo", requestFile)

            //fileToUpload()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ReviewCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPrevious()

        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingBar.rating = rating
            review_rate = rating.toInt()
        }

        binding.openGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            activityResult.launch(intent)
        }



        binding.btnReviewCreate.setOnClickListener {

            sendReviewRequest()
            // == 백엔드 통신 부분 ==
            /*val file: File = File(mediaPath)
            var inputStream: InputStream? = null try {
            inputStream = getContext().getContentResolver().openInputStream(photoUri)
        } catch (e: IOException) {
            e.printStackTrace()
        }
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val byteArrayOutputStream: ByteArrayOutputStream =
                ByteArrayOutputStream() bitmap.compress Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)   var requestBody: RequestBody? = create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())   var uploadFile: Part? = createFormData.createFormData("postImg", file.getName(), requestBody)
            val content = binding.reviewInput.text.toString()

            val data = ReviewDTO(content, review_rate, fileToUpload())*/


/*            api.post_reviews(data, image).enqueue(object : Callback<ReviewData> {
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
        }
    }

    private fun initPrevious() {
        binding.apply {
            btnBack.setOnClickListener {
                val intent = Intent(this@ReviewPostActivity, TestMainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun getRealPathFromURI(uri: Uri): String {
        val buildName = Build.MANUFACTURER
        if(buildName.equals("Xiaomi")) {
            return uri.path.toString()
        }

        var columnIndex = 0
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        var cursor = contentResolver.query(uri, proj, null, null, null)

        if(cursor!!.moveToFirst()) {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }

        return cursor.getString(columnIndex)
    }


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

    fun sendReviewRequest() {
        val contentRequestBody : RequestBody = content.toPlainRequestBody()
        val reviewRateRequestBody: RequestBody = review_rate.toString().toPlainRequestBody()
        val textHashMap = hashMapOf<String, RequestBody>()
        textHashMap["content"] = contentRequestBody
        textHashMap["review_rate"] = reviewRateRequestBody

        val file = File(mediaPath)
        val bitmapRequestBody = bitmap?.let { BitmapRequestBody(it)}
        val bitmapMultipartBody: MultipartBody.Part? =
            if (bitmapRequestBody == null) null
            else MultipartBody.Part.createFormData("reviewImages", file.name, bitmapRequestBody)

        val response = api.addActivity(bitmapMultipartBody, textHashMap).awaitResponse()
    }

    inner class BitmapRequestBody(private val bitmap: Bitmap) : RequestBody() {
        override fun contentType(): MediaType = "image/jpeg".toMediaType()
        override fun writeTo(sink: BufferedSink) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 99, sink.outputStream())
        }
    }

    private fun String?.toPlainRequestBody() = requireNotNull(this).toRequestBody("text/plain".toMediaTypeOrNull())
}

/*    private fun openGallery() {
        val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivity()
        startActivityForResult(intent, OPEN_GALLERY)
    }*/
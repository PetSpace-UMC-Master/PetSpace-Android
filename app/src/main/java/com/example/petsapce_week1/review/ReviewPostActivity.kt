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
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

class ReviewPostActivity : AppCompatActivity() {
    private lateinit var binding: ReviewCreateBinding
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : ReviewAPI = retrofit.create(ReviewAPI::class.java)
    var localImgFile: File ?= null
    var images = arrayListOf<MultipartBody.Part>()
    var fileToUpload = arrayListOf<MultipartBody.Part>()
    private var success_review_id: Int? = null
    private var review_rate: Int? = null
    private var mediaPath: String? = null

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

            // 통신 부분
            api.post_reviews(accessTokenPost, textHashMap, fileToUpload)
                .enqueue(object : Callback<ReviewPostData> {
                    // 통신 성공
                    override fun onResponse(
                        call: Call<ReviewPostData>,
                        response: Response<ReviewPostData>
                    ) {
                        Log.d("리뷰 포스트 통신 성공", response.toString())
                        Log.d("리뷰 포스트 통신 성공", response.body().toString())
                        Log.d("통신 완료된 해쉬맵(내용, 별점)", textHashMap.toMap().toString())
                        Log.d("통신 완료된 사진", images.toString())
                        Log.d("파일 경로", "$mediaPath")
                        // 통신 응답 확인
                        val body = response.body()
                        if (body != null) {
                            success_review_id = body.result.id
                            Log.d("응답 받은 리뷰ID (포스트 된 리뷰ID)", "{${success_review_id.toString()}}"
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

    // 디바이스에서 사진 불러와서 띄우기 & 서버에 보낼 준비
    val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            val uri = it.data!!.data
            mediaPath = getRealPathFromURI(it.data?.data!!)

            Glide.with(this)
                .load(uri)
                .into(binding.selectedImage)

            // 사진 데이터 => 외부 저장소에 복제 후 멀티파트에 담기
            val i: InputStream? = uri?.let { it1 -> contentResolver.openInputStream(it1) } //src
            val extension: String = mediaPath!!.substring(mediaPath!!.lastIndexOf("."))
            localImgFile = File(applicationContext.filesDir, "localImgFile$extension")

            if (i != null) {
                try {
                    val out: OutputStream = FileOutputStream(localImgFile) //dst
                    try {
                        // Transfer bytes from in to out
                        val buf = ByteArray(1024)
                        var len: Int
                        while (i.read(buf).also { len = it } > 0) {
                            out.write(buf, 0, len)
                        }
                    } finally {
                        out.close()
                    }
                } finally {
                    i.close()
                }
            }
            val requestFile = localImgFile!!.asRequestBody("image/*".toMediaTypeOrNull())
            images.add(MultipartBody.Part.createFormData("reviewImages", localImgFile!!.name, requestFile))
            Log.d("멀티파트에 담긴 reviewImages", images.toString())
            Log.d("파일 경로", "$mediaPath")
        }
    }

    // 이전 화면
    fun initPrevious() {
        binding.apply {
            btnBack.setOnClickListener {
                val intent = Intent(this@ReviewPostActivity, TestMainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun String?.toPlainRequestBody() =
        requireNotNull(this).toRequestBody("text/plain".toMediaTypeOrNull())
}
package com.example.petsapce_week1.review

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.petsapce_week1.TestMainActivity
import com.example.petsapce_week1.databinding.ReviewCreateBinding
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.network.ReviewAPI
import com.example.petsapce_week1.vo.accomo_datamodel.ReviewDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class ReviewPostActivity : AppCompatActivity() {
    private lateinit var binding: ReviewCreateBinding
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api: ReviewAPI = retrofit.create(ReviewAPI::class.java)
    private var success_review_id: Int? = null
    private var review_rate: Int? = null

    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK && it.data != null) {
            val uri = it.data!!.data

            Glide.with(this)
                .load(uri)
                .into(binding.selectedImage)

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

            // == 백엔드 통신 부분 ==
            val content = binding.reviewInput.text.toString()

            val data = ReviewDTO(content, review_rate)


            api.post_reviews(data).enqueue(object : Callback<ReviewData> {
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
            })
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



}

/*    private fun openGallery() {
        val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivity()
        startActivityForResult(intent, OPEN_GALLERY)
    }*/
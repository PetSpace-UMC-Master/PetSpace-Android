package com.example.petsapce_week1.reviewrelated

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.R
import com.example.petsapce_week1.TermsActivity
import com.example.petsapce_week1.databinding.ActivityReviewPhotosBinding
import com.example.petsapce_week1.databinding.ActivityReviewReadMoreBinding


class ReviewReadMoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewReadMoreBinding
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReviewReadMoreBinding.inflate(layoutInflater)

        //testcode
        // 왼쪽 상단 x 버튼 클릭 시 이전 화면(manifest에 parent)
        binding.btnReviewClose.setOnClickListener {
            val intent = Intent(this, ReviewPhotosActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)

        //recyclerview 임의로 데이터 추가
        val itemList = ArrayList<ReviewItem>()
        val rvAdapter = ReviewAdapter(itemList)

        binding.rvReview.adapter = rvAdapter
        binding.rvReview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        itemList.add(ReviewItem("3주 전","멍멍이", "너무 좋아요 어쩌고 저쩌고", img = R.drawable.pic_1))
        itemList.add(ReviewItem("4주 전","야옹이", "너무 좋아요 너무 좋아요 Google 지도에서 방문한 장소에 대한 리뷰를 작성할 수 있습니다. 너무 좋아요 Google 지도에서 너무 좋아요 Google 지도에서 너무 좋아요 너무 좋아요 Google 지도에서Google 지도에서 방문한 장소에 대한 리뷰를 작성할 수 있습니다.Google 지도에서 방문한 장소에 대한 리뷰를 작성할 수 있습니다. 블로그 체험단, 인스타그램, 유튜브, 구매리뷰, 기자단, 바이럴 마케팅, 전문 컨설팅, 채널별 최적의 인플루언서가 함께하는 리얼리뷰", img = R.drawable.pic_2))
        itemList.add(ReviewItem("6주 전","집사", "너무 좋아요",img = R.drawable.pic_2))
        itemList.add(ReviewItem("10주 전","댕댕", "너무 좋아요 너무 좋아요 Google 지도에서 방문한 장소에 대한 리뷰를 작성할 수 있습니다.", img = R.drawable.pic_2))

        binding.btnReviewClose.setOnClickListener {
            val intent = Intent(this, ReviewPhotosActivity::class.java)
            startActivity(intent)
        }
        rvAdapter.itemList = itemList
        //변경됨을 어댑터에 알림
        rvAdapter.notifyDataSetChanged()

    }
}
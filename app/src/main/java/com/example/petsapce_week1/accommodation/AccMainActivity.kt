package com.example.petsapce_week1.accommodation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.petsapce_week1.ProfileMenuFragment
import com.example.petsapce_week1.R
import com.example.petsapce_week1.accommodation.scroll.AccFacilityMoreActivity
import com.example.petsapce_week1.accommodation.scroll.googleFragment
import com.example.petsapce_week1.accommodation.scroll.reviewFragment
import com.example.petsapce_week1.databinding.ActivityAccHostBinding
import com.example.petsapce_week1.databinding.ActivityAccMainBinding
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.LoginService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.FacilityData
import com.example.petsapce_week1.vo.FavoriteBackendResponse
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationRoomData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class AccMainActivity : AppCompatActivity() {
    lateinit var binding:ActivityAccMainBinding
    lateinit var bindingHostBinding: ActivityAccHostBinding
    lateinit var adapter: accImgaeSlideAdapter
    var imgdataList = ArrayList<imageSlideData>()
    val reviewList = mutableListOf<FacilityData>()
    var photos = mutableListOf<String>()

    // ========== 백엔드 연동 부분 ===========
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    // 기본 숙소 정보 불러올때 호출
    var api : AccomoService = retrofit.create(AccomoService::class.java)

    // 좋아요 버튼 눌렀을 때 호출
    var apiLike : AccomoService = retrofit.create(AccomoService::class.java)
    //============ 토큰 재발급 ==============
    var apiReissue : LoginService = retrofit.create(LoginService::class.java)

    private val MIN_SCALE = 0.85f // 뷰가 몇퍼센트로 줄어들 것인지
    private val MIN_ALPHA = 0.5f // 어두워지는 정도를 나타낸 듯 하다.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //홈화면 리사이클러뷰에서 넘어감
        val roomBeforeID  = intent.getIntExtra("content",-1)
        Log.d("roomBeforeID",roomBeforeID.toString())


        // ============ token ============
        //토큰 저장 객체
        var accessToken : String ?= null
        val atpref = getSharedPreferences("accessToken", MODE_PRIVATE)
        if(atpref != null){
            accessToken = atpref.getString("accessToken", "default")
        }
        val accessTokenPost = "Bearer $accessToken"

        // close btn
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, ProfileMenuFragment::class.java)
            startActivity(intent)
        }

        // .bind와 .inflate 차이 / layoutinflater , view 객체 차이
        val includeView: View = binding.frameHost.root
        bindingHostBinding = ActivityAccHostBinding.bind(includeView)

        // 이런식으로 간략하게 쳐도됨
        // bindingHostBinding = ActivityAccHostBinding.bind(binding.frameHost.root)


        initViewPager()

        val data = AccomodationRoomData(roomId = null)

        // =================== 백엔드 연동 부분 =====================
        //홈화면 연결 후 roomId 받아오면 반영!
        api.getRoomDetail( accessTokenPost, 1).enqueue(object : Callback<AccomodationData> {
            @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
            override fun onResponse(
                call: Call<AccomodationData>,
                response: Response<AccomodationData>
            ) {

                Log.d("숙소 세부 정보 통신 성공",response.toString())
                Log.d("숙소 세부 정보 통신 성공", response.body().toString())

                val body = response.body()
                if (body != null) {

                    //============ 좋아요 버튼 ==========
                    // like btn
                    var if1Checked = 0
                    if(response.body()!!.result.favorite){
                        if1Checked = 1
                        binding.btnHeartAfter.visibility = View.VISIBLE
                    }
                    else{
                        if1Checked = 0
                        binding.btnHeartAfter.visibility = View.INVISIBLE
                    }

                    binding.btnHeartBefore.setOnClickListener {
                        //if 로그인 정보 없으면, 로그인 화면으로 화면 이동
                        //있으면 밑에 if 문 돌리면 될듯

                        if (if1Checked == 0 ){
                            binding.btnHeartAfter.visibility = View.VISIBLE
                            if1Checked = 1
                            // 상진쓰랑 할것
                            api.postLikes(accessTokenPost).enqueue(object : Callback<AccomodationData>{
                                override fun onResponse(
                                    call: Call<AccomodationData>,
                                    response: Response<AccomodationData>
                                ) {
                                    Log.d("숙소 좋아요 표시", "했음")
                                }

                                override fun onFailure(call: Call<AccomodationData>, t: Throwable) {
                                    Log.d("숙소 좋아요 표시", "x했음")
                                }

                            })
                        }
                    }
                    binding.btnHeartAfter.setOnClickListener {
                        if(if1Checked == 1){
                            binding.btnHeartAfter.visibility = View.INVISIBLE
                            if1Checked = 0
                            // 상진쓰랑 할것
                            api.postLikes(accessTokenPost).enqueue(object : Callback<AccomodationData>{
                                override fun onResponse(
                                    call: Call<AccomodationData>,
                                    response: Response<AccomodationData>
                                ) {
                                    Log.d("숙소 좋아요 표시", "했음")
                                }

                                override fun onFailure(call: Call<AccomodationData>, t: Throwable) {
                                    Log.d("숙소 좋아요 표시", "x했음")
                                }

                            })
                        }
                    }


                    // ================ 맨 위 프레임 ==================
                    binding.tvHousename.text = body.result.roomName
                    binding.textAddress.text = body.result.address
                    binding.textPrice.text = "₩ ${body.result.price}/박"
                    binding.textStarscore.text = body.result.roomAverageScore.toString()
                    binding.textReviewcount.text = "${body.result.reviewCount}개"

                    for (item in body.result.roomImageUrls) {
                        imgdataList.apply {
                            add(
                                imageSlideData(
                                    imgSlide = item
                                )
                            )
                        }
                    }
                    for (i in 0 until imgdataList.size) {
                        imgdataList.add(
                            imageSlideData(
                                imgdataList[i].imgSlide
                            )
                        )
                    }

                    Log.d("숙소","${body.result.roomImageUrls}")
                    Log.d("숙소","$photos")
                    val imgUrlAdapter = accImgaeSlideAdapter(imgdataList)
                    binding.viewpager.adapter = imgUrlAdapter
                    imgUrlAdapter.notifyDataSetChanged()

                    // ================= frame host 호스트 ===================
                    binding.frameHost.textName.text = body.result.hostName
                    binding.frameHost.tvMaxpet.text = "· 최대 반려동물 ${body.result.maxPet}마리"
                    binding.frameHost.tvMaxguest.text = "최대 인원 ${body.result.maxGuest}명 "
                    binding.frameHost.textAbout.text = body.result.roomDecription

                }
                // ================ facility 편의시설 프레임 =====================
                response.takeIf { it.isSuccessful }
                    ?.body()
                    .let {
                        if (it != null) {
                            for (item in it.result.facilities) {
                                reviewList.apply {
                                    add(
                                        FacilityData(
                                            imgUrl = item.facilityImageUrl,
                                            facname = item.facilityName
                                        )
                                    )
                                }
                            }
                        }
                    }
                for (i in 0 until reviewList.size) {
                    reviewList.add (
                        FacilityData(
                            reviewList[i].imgUrl,
                            reviewList[i].facname
                        )
                    )
                }


                if(reviewList.isNotEmpty()){
                    Log.d("숙소 facility 리스트", "${reviewList}")
                    //binding.frameFacility.tvFac0.text = reviewList[0].facname
                    binding.frameFacility.tvFac1.text = reviewList[0].facname
                    Glide.with(this@AccMainActivity)
                        .load(reviewList[0].imgUrl)
                        .into(binding.frameFacility.imgFac1)
                    binding.frameFacility.tvFac2.text = reviewList[1].facname
                    Glide.with(this@AccMainActivity)
                        .load(reviewList[1].imgUrl)
                        .into(binding.frameFacility.imgFac2)
                    binding.frameFacility.tvFac3.text = reviewList[2].facname
                    Glide.with(this@AccMainActivity)
                        .load(reviewList[2].imgUrl)
                        .into(binding.frameFacility.imgFac3)
                    binding.frameFacility.tvFac4.text = reviewList[3].facname
                    Glide.with(this@AccMainActivity)
                        .load(reviewList[3].imgUrl)
                        .into(binding.frameFacility.imgFac4)
                    binding.frameFacility.tvFac5.text = reviewList[4].facname
                    Glide.with(this@AccMainActivity)
                        .load(reviewList[4].imgUrl)
                        .into(binding.frameFacility.imgFac5)
                    binding.frameFacility.tvFac6.text = reviewList[5].facname
                    Glide.with(this@AccMainActivity)
                        .load(reviewList[5].imgUrl)
                        .into(binding.frameFacility.imgFac6)
                }

            }

            override fun onFailure(call: Call<AccomodationData>, t: Throwable) {
                Log.d("숙소 시설 facility 세부 정보", "failed")
            }
        })

        supportFragmentManager
            .beginTransaction()
            .replace(binding.frameGoogle.id, googleFragment())
            .commitAllowingStateLoss()

        supportFragmentManager
            .beginTransaction()
            .add(binding.frameReview.id, reviewFragment())
            .commitAllowingStateLoss()

        binding.frameFacility.tvViewmore.setOnClickListener {
            val intent = Intent(this@AccMainActivity, AccFacilityMoreActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initViewPager() {

        //binding.viewpager.adapter = accImgaeSlideAdapter(imgdataList)

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin) // dimen 파일 안에 크기를 정의해두었다.
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth) // dimen 파일이 없으면 생성해야함
        val screenWidth = resources.displayMetrics.widthPixels // 스마트폰의 너비 길이를 가져옴
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.viewpager.apply {
            setPageTransformer { page, position ->
                page.translationX = position * -offsetPx
            }
            setPageTransformer(ZoomOutPageTransformer())
            offscreenPageLimit = 1 // 몇 개의 페이지를 미리 로드 해둘것인지
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        //기존 adapter(recyclerview adpater)
       /* binding.recyclerviewSlide.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false
        )
        adapter = accImgaeSlideAdapter(imgdataList)
        binding.recyclerviewSlide.adapter = adapter*/


       /* adapter.itemClickListener = object : accImgaeSlideAdapter.OnItemClickListener {
            override fun OnItemClick(data: imageSlideData) {
                *//*  Toast.makeText(getActivity(),"show", Toast.LENGTH_SHORT).show()
                   val intent = packageManager.getLaunchIntentForPackage(data.appackname)
                   startActivity(intent)*//*
                val intent = Intent(this@AccMainActivity, GifActivity::class.java)
                startActivity(intent)

                Log.d("test", "test")
            }


        }*/
    }

    inner class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        // Fade the page relative to its size.
                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }
}


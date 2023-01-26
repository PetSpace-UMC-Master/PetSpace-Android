package com.example.petsapce_week1.accommodation.scroll

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.GifActivity
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentReviewBinding
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.ReviewData
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData
import okhttp3.internal.delimiterOffset
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.time.format.DateTimeFormatter


class reviewFragment : Fragment() {

    private lateinit var binding:FragmentReviewBinding
    lateinit var adapter: reviewAdapter
    var dataList = ArrayList<reviewData>()
    //백엔드 서버 연동
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : AccomoService = retrofit.create(AccomoService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val reviewList = mutableListOf<ReviewData>()
        binding = FragmentReviewBinding.inflate(layoutInflater)

        initRecyclerView()

        // ============== 백엔드 연동 =================
        val roomId : Long = 1
        api.getRoomDetail(roomId = roomId).enqueue(object : Callback<AccomodationData> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<AccomodationData>,
                response: Response<AccomodationData>
            ) {
                Log.d("숙소 세부 정보 review 통신 성공",response.toString())
                Log.d("숙소 세부 정보 review 통신 성공", response.body().toString())
                val body = response.body()
                Log.d("숙소 리뷰 리스트으??",body.toString())
//                reviewList = body.result.reviewPreviews

                if (body != null) {
                    //frame host data
                    //binding.frameHost.textName.text = body.result.hostName
                    binding.textStarscore.text = body.result.roomAverageScore.toString()
                    binding.textReviewcount.text = body.result.reviewCount.toString()


                    response.takeIf { it.isSuccessful }
                        ?.body()
                        .let {
                            if (it != null) {
                                for (item in it.result.reviewPreviews) {
                                    reviewList.apply {
                                        add(
                                            ReviewData(
                                                createdAt = item.createdAt,
                                                description = item.description,
                                                nickname = item.nickname,
                                                userId = item.userId,
                                                score = item.score,
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    for (i in 0 until reviewList.size) {
                        val new_date = reviewList[i].createdAt.substring(0, reviewList[i].createdAt.indexOf("T"))
                        val new_date_list = new_date.split("-")
                        Log.d("Date1", "${new_date_list[0] + "년 " + new_date_list[1] + "월 " + new_date_list[2] + "일"}")
                        val date = "${new_date_list[0]}년 ${new_date_list[1]}월 ${new_date_list[2]}일"
                        dataList.add (
                            reviewData(
                                R.drawable.face,
                                reviewList[i].score, //.score,
                                reviewList[i].nickname,
                                date,
                                reviewList[i].description
                            )
                        )
                    }

                    Log.d("숙소 ===", dataList[0].toString())
                    val vreviewAdapter = reviewAdapter(dataList)
                    binding.recyclerview.adapter = vreviewAdapter
                }
            }
            override fun onFailure(call: Call<AccomodationData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
//        initAddData()


        // Inflate the layout for this fragment
        return binding.root
    }
//
//    private fun initAddData() {
//
//        dataList.add(
//            reviewData(
//                R.drawable.face,
//                5,
//                "김현아",
//                "3",
//                "품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가? 품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가?..."
//            )
//        )
//        dataList.add(
//            reviewData(
//                R.drawable.aircon,
//                5,
//                "김현아",
//                3,
//                "서만 되려니와, 방지하는 있는가? 품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가?..."
//            )
//        )
//        dataList.add(
//            reviewData(
//                R.drawable.star3,
//                5,
//                "김현아",
//                3,
//                "품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가? 품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가?..."
//            )
//        )
//        dataList.add(
//            reviewData(
//                R.drawable.star3,
//                5,
//                "김현아",
//                3,
//                "품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가? 품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가?..."
//            )
//        )
//    }

    private fun initRecyclerView() {

        //기존 adapter(recyclerview adpater)
        binding.recyclerview.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
        adapter = reviewAdapter(dataList)
        binding.recyclerview.adapter = adapter


        adapter.itemClickListener = object : reviewAdapter.OnItemClickListener {
            override fun OnItemClick(data: reviewData) {
                /*  Toast.makeText(getActivity(),"show", Toast.LENGTH_SHORT).show()
                   val intent = packageManager.getLaunchIntentForPackage(data.appackname)
                   startActivity(intent)*/
                val intent = Intent(context, GifActivity::class.java)
                startActivity(intent)

                Log.d("test", "test")
            }
        }
    }

}
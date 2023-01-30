package com.example.petsapce_week1.accommodation.scroll

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.media.AudioMetadata.createMap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.petsapce_week1.databinding.FragmentGoogleBinding
import com.example.petsapce_week1.loginrelated.LoginActivity
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class googleFragment : Fragment(), OnMapReadyCallback {

    private lateinit var viewModel: GoogleViewModel
    private lateinit var viewBinding:FragmentGoogleBinding
    private lateinit var mapView : MapView
    private lateinit var naverMap : NaverMap
    private lateinit var locationSource: FusedLocationSource
    var latitude : Double = 0.0
    var longtitude : Double = 0.0
    val marker = Marker()
    private val LOCATION_PERMISSION_REQUEST_CODE: Int = 1000

    //백엔드 서버 연동
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : AccomoService = retrofit.create(AccomoService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentGoogleBinding.inflate(layoutInflater)
        //activity의 setcontentview가 아닌 return값을 주면된다.

        val roomId : Long = 2
        api.getRoomDetail(roomId = roomId).enqueue(object : Callback<AccomodationData> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<AccomodationData>,
                response: Response<AccomodationData>
            ) {
                Log.d("숙소 세부 정보 google 통신 성공", response.toString())
                Log.d("숙소 세부 정보 google 통신 성공", response.body().toString())
                val body = response.body()
                if (body != null) {
                    //binding.frameHost.textName.text = body.result.hostName
                    viewBinding.textAddress.text = body.result.address
                    viewBinding.houseName.text = body.result.roomName
                    latitude = body.result.latitude.toDouble()
                    longtitude = body.result.longitude.toDouble()
//                    Log.d("숙소 latitude", body.result.latitude)
//                    Log.d("숙소 longitude", body.result.longitude)
                    Log.d("latitude", "${latitude}")
                    Log.d("longitude", "${longtitude}")

                    createNaverMap(mapView)
                    mapView.onCreate(savedInstanceState)


                }
            }
            override fun onFailure(call: Call<AccomodationData>, t: Throwable) {
                Log.d("숙소 세부 정보 google 통신 실패","ㅠㅠ")
            }
        })

        return viewBinding.root
    }

    private fun createNaverMap(mapView: MapView) {
        Log.d("0latitude", "${latitude}")
        Log.d("0longitude", "${longtitude}")
        this.mapView = viewBinding.navermap
        Log.d("1latitude", "${latitude}")
        Log.d("1longitude", "${longtitude}")

        Log.d("2latitude", "${latitude}")
        Log.d("2longitude", "${longtitude}")
        mapView.getMapAsync(this)
        Log.d("3latitude", "${latitude}")
        Log.d("3longitude", "${longtitude}")
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        Log.d("4latitude", "${latitude}")
        Log.d("4longitude", "${longtitude}")
        marker.position = LatLng(latitude, longtitude)
        marker.map = naverMap
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[GoogleViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap

        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true
        //카메라 설정
        val cameraPosition = CameraPosition(
            LatLng(latitude, longtitude),
            16.0,
            20.0,
            180.0
        )
        naverMap.cameraPosition = cameraPosition

        naverMap.addOnCameraChangeListener { reason, animated ->
            //Log.d("숙소 이게 모야", "실행돼씀")
            marker.position = LatLng(
                naverMap.cameraPosition.target.latitude,
                naverMap.cameraPosition.target.longitude
            )
        }
        Log.d("Map Ready : latitude", "${latitude}")
        Log.d("Map Ready : longitude", "${longtitude}")
        /*
        naverMap.addOnCameraIdleListener {
            //네이버멥 중앙에 마커
            marker.map = naverMap
            marker.icon = MarkerIcons.RED
            marker.iconTintColor = Color.BLUE
        }

         */
        naverMap.locationSource = locationSource

    }
    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

}
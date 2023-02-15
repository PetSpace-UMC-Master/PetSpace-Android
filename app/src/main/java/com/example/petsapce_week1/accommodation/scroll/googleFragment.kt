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
import kotlin.properties.Delegates

class googleFragment(val roomId : Long) : Fragment(), OnMapReadyCallback {

    private lateinit var viewModel: GoogleViewModel
    private lateinit var viewBinding:FragmentGoogleBinding
    private lateinit var mapView : MapView

    val marker = Marker()
    private val LOCATION_PERMISSION_REQUEST_CODE: Int = 1000

    //백엔드 서버 연동
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : AccomoService = retrofit.create(AccomoService::class.java)
    val accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5c2xpbTM3QG5hdmVyLmNvbSIsImlhdCI6MTY3NTMyMTY0NywiZXhwIjoxNjc1MzIzNDQ3fQ.4CDgFa2fp_b-9fEuDiiwPkTR3SC23bI23NYOEdBiSB8"
    val accessTokenPost = "Bearer $accessToken"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentGoogleBinding.inflate(layoutInflater)
        //activity의 setcontentview가 아닌 return값을 주면된다.


        api.getRoomDetail(accessToken = accessTokenPost, roomId = roomId).enqueue(object : Callback<AccomodationData> {
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
                    longitude = body.result.longitude.toDouble()

                    Log.d("latitude", "${latitude}")
                    Log.d("longitude", "${longitude}")

                    if(body.isSuccess){
                        naverMap?.let { onMapReady(it) }
                    }
                }
            }
            override fun onFailure(call: Call<AccomodationData>, t: Throwable) {
                Log.d("숙소 세부 정보 google 통신 실패","ㅠㅠ")
            }
        })
        this.mapView = viewBinding.navermap
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        marker.position = LatLng(latitude, longitude)
        marker.map = naverMap

        return viewBinding.root
    }

    private var naverMap : NaverMap ?= null
    private lateinit var locationSource: FusedLocationSource
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[GoogleViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource

        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true
        //카메라 설정
        val cameraPosition = CameraPosition(
            LatLng(latitude, longitude),
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
        Log.d("Map Ready : longitude", "${longitude}")

        naverMap.addOnCameraIdleListener {
            //네이버멥 중앙에 마커
            marker.map = naverMap
            marker.icon = MarkerIcons.RED
            marker.iconTintColor = Color.BLUE
        }
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
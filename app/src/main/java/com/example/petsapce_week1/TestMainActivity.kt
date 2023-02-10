package com.example.petsapce_week1

//import kotlinx.android.synthetic.main.activity_home_onlyfortest.*
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.accommodation.AccMainActivity
import com.example.petsapce_week1.databinding.ActivityHomeOnlyfortestBinding
import com.example.petsapce_week1.loginrelated.LoginActivity
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.placetogo.NoLoginPlacetogoFragment
import com.example.petsapce_week1.placetogo.PlaceToGoFragment
import retrofit2.Retrofit


class TestMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeOnlyfortestBinding

    // ========== 백엔드 연동 부분 ===========
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : AccomoService = retrofit.create(AccomoService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeOnlyfortestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAccomo.setOnClickListener {
            val intent = Intent(this@TestMainActivity, AccMainActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this@TestMainActivity, LoginActivity::class.java)
            startActivity(intent)
        }

//        binding.btnReviewCreate.setOnClickListener {
//            val intent = Intent(this@TestMainActivity, ReviewPostActivity::class.java)
//            startActivity(intent)
//        }
        binding.btnPlacetogo.setOnClickListener {
            supportFragmentManager.popBackStack()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val placeFragment = PlaceToGoFragment()
            //fragmentTransaction.remove(supportFragmentManager.findFragmentById(R.id.fragmentview)!!)
            fragmentTransaction.replace(R.id.fragmentContainerView, placeFragment, null).commit()
            //fragmentTransaction.addToBackStack(null)
        }


        // ============ 홈 => 함께갈곳 이동 시 로그인 여부 체크 =============

        //토큰 저장 객체
        var accessToken : String ?= null
        val atpref = getSharedPreferences("accessToken", MODE_PRIVATE)
        if(atpref != null){
            accessToken = atpref.getString("accessToken", "default")
        }
        if(accessToken == "default"){
            val intent = Intent(this, NoLoginPlacetogoFragment::class.java)
            startActivity(intent)
        }
        binding.btnPlacetogo.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val placeFragment = PlaceToGoFragment()
            //fragmentTransaction.remove(supportFragmentManager.findFragmentById(R.id.fragmentview)!!)
            //fragmentTransaction.replace(R.id.fragmentview, placeFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}

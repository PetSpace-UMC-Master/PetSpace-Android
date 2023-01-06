package com.example.petspace3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.petspace3.databinding.ActivityMainBinding
import kr.co.prnd.readmore.ReadMoreTextView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //개인 정보 수집 동의 내용 (tvAgreeContent1)

        //(라이브러리 사용) - 내용 변동 시 : 더보기 클릭 이벤트 리스너
        /*

        val readMoreTextView: ReadMoreTextView = binding.tvAgreeContent1
        readMoreTextView.changeListener = object : ReadMoreTextView.ChangeListener {
            override fun onStateChange(state: ReadMoreTextView.State) {
                val intent = Intent(this, TermsContentActivity1::class.java)
                startActivity(intent)
            }
        }

        binding.tvAgreeContent1.changeListener = object : ReadMoreTextView.ChangeListener {
            val intent = Intent(this, TermsContentActivity1::class.java)
            startActivity(intent)
        }

        binding.tvAgreeContent1.setOnClickListener {
            val intent = Intent(this, TermsContentActivity1::class.java)
            startActivity(intent)
        }
        */

        //더보기 text 버튼 클릭 이벤트 리스너 (고정)
        binding.btnViewMore1.setOnClickListener {
            val intent = Intent(this, TermsContentActivity1::class.java)
            startActivity(intent)
        }

        //마케팅 수신 동의 내용 (tvAgreeContent2)
        /*
         binding.tvAgreeContent2.setOnClickListener {
            startActivity(Intent(this, TermsContentActivity2::class.java))
        }
         */
        //더보기 text 버튼 클릭 이벤트 리스너 (고정)
        binding.btnViewMore2.setOnClickListener {
            val intent = Intent(this, TermsContentActivity2::class.java)
            startActivity(intent)
        }

        //checkbox 모두 선택 시 버튼 활성화
        var if1Checked : Int = 0
        var if2Checked : Int = 0
        binding.checkboxAgreeToInfo.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                if1Checked = 1
                if(if2Checked == 1){
                    binding.btnComleteAfter.visibility = View.VISIBLE
                }
            }
            else{
                if1Checked = 0
                binding.btnComleteAfter.visibility=View.INVISIBLE
            }
        }
        binding.checkboxAgreeToMarketing.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                if2Checked = 1
                if ( if1Checked == 1 ){
                    binding.btnComleteAfter.visibility = View.VISIBLE
                }
                else{
                    binding.btnComleteAfter.visibility = View.INVISIBLE
                }
            }
            else{
                binding.btnComleteAfter.visibility = View.INVISIBLE
            }
        }

        binding.btnComleteAfter.setOnClickListener{
            val intent = Intent(this, ReviewReadMoreActivity::class.java)
            //**이 부분 약관 동의 완료 시 "000님 환영합니다" 있는 시작 화면으로 수정해야 함.**
            startActivity(intent)
        }
    }
}
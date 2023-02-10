package com.example.petsapce_week1.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.petsapce_week1.databinding.LayoutBottomSheetBinding

class PersonDialogFragment : DialogFragment() {

    private lateinit var binding: LayoutBottomSheetBinding
    lateinit var viewModel: ResViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutBottomSheetBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        // viewModel의 주최를 MainActivityViewModel로 설정
        viewModel = ViewModelProvider(requireActivity()).get(ResViewModel::class.java)

//        binding.lifecycleOwner = viewLifecycleOwner


        initDataBinding()
        initViews()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initDataBinding() {
        //성인
        val nameObserver = Observer<Int> { curAdult ->
            binding.textAdult.text = curAdult.toString()
            binding.btnResult.text =
                "성인 ${curAdult}명, 아동 ${viewModel.curChild.value}명, 반려동물 ${viewModel.curAnimal.value}마리"

        }
        viewModel.curAdult.observe(viewLifecycleOwner, nameObserver)

        //아동
        viewModel.curChild.observe(viewLifecycleOwner, Observer { curChild ->
            binding.textChild.text = curChild.toString()
            binding.btnResult.text =
                "성인 ${viewModel.curAdult.value}명, 아동 ${curChild}명, 반려동물 ${viewModel.curAnimal.value}마리"

        })

        //동물
        viewModel.curAnimal.observe(viewLifecycleOwner, Observer { curAnimal ->
            binding.textAnimal.text = curAnimal.toString()
            binding.btnResult.text =
                "성인 ${viewModel.curAdult.value}명, 아동 ${viewModel.curChild.value}명, 반려동물 ${curAnimal}마리"

        })
    }


    private fun initViews() = with(binding) {

        // 뒤로가기 버튼, 빈 화면 터치를 통해 dialog가 사라지지 않도록
        dialog?.setCancelable(false)

        // background를 투명하게 만듦
        // (중요) Dialog는 내부적으로 뒤에 흰 사각형 배경이 존재하므로, 배경을 투명하게 만들지 않으면
        // corner radius의 적용이 보이지 않는다.
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Button 클릭에 대한 Callback 처리.
        btnBack.setOnClickListener {
            dismiss()
        }
        btnMinusAdult.setOnClickListener {
            viewModel.minusAdult()
        }
        btnPlusAdult.setOnClickListener {
            viewModel.plusAdult()
        }
        btnMinusChild.setOnClickListener {
            viewModel.minusChild()
        }
        btnPlusChild.setOnClickListener {
            viewModel.plusChild()
        }
        btnMinusAnimal.setOnClickListener {
            viewModel.minusAnimal()
        }
        btnPlusAnimal.setOnClickListener {
            viewModel.plusAnimal()
        }
        btnResult.setOnClickListener {
            dismiss()
        }


    }


}
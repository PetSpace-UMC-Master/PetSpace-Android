package com.example.petsapce_week1.vo

import com.example.petsapce_week1.vo.accomo_datamodel.Facility

data class AccommodationFacilityMore(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: Result2
)

data class Result2(
    val allFacilityInfos: List<AllFacilityInfo>
    //val allFacilityInfos: List<FacilityReceived>
)

data class AllFacilityInfo(
    val category: String,
    val facilities: List<Facility>
)


package com.example.petsapce_week1.vo

import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData
//import com.example.petsapce_week1.vo.accomo_datamodel.Facility

data class AccommodationFacilityMore(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: Result
){
    data class Result(
        val allFacilityInfos: List<AllFacilityInfo>
        //val allFacilityInfos: List<FacilityReceived>
    )

    data class AllFacilityInfo(
        val category: String,
        val facilities: List<AccomodationData.Facility>
    )
}



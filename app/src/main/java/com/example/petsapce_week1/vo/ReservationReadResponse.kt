package com.example.petsapce_week1.vo

import androidx.annotation.Keep
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

//예약 시 받는 데이터


data class ReservationReadResponse(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: Result
){
    data class Result(
        val isLast: Boolean,
        val page: Int,
        val reservations: List<Reservation>
    )

    data class Reservation(
        val endDate: String,
        val remainingDays: Int,
        val reservationCode: String,
        val reviewCreated: Boolean,//true => 수정, false => 작성
        val roomId: Long,
        val roomImageUrls: List<String>,
        val roomName: String,
        val startDate: String
    )
}
//
//@Keep
//data class Reservation(
//    @SerializedName("id") val id: Long,
//    @SerializedName("start_date") val startDate: String,
//    @SerializedName("end_date") val endDate: String,
//    @SerializedName("total_guests") val totalGuests: Int,
//    @SerializedName("accommodation_id") val accommodationId: Long,
//) : Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readLong(),
//        parcel.readString() ?: "",
//        parcel.readString() ?: "",
//        parcel.readInt(),
//        parcel.readLong()
//    )
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeLong(id)
//        parcel.writeString(startDate)
//        parcel.writeString(endDate)
//        parcel.writeInt(totalGuests)
//        parcel.writeLong(accommodationId)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Reservation> {
//        override fun createFromParcel(parcel: Parcel): Reservation {
//            return Reservation(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Reservation?> {
//            return arrayOfNulls(size)
//        }
//    }
//}

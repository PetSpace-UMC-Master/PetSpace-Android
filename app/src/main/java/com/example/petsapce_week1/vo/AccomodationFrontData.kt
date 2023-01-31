package com.example.petsapce_week1.vo.accomo_datamodel

//백엔드로 주는 데이터 클래스
data class AccomodationRoomData(
    val roomId : Int ?= null
)

/**
 * 이걸 만든 거는
 * post 보낼 때 데이터 객체로 묶어서 보내려고
 * 근데 내가 필요한 거는 roomId 뿐이고
 * Get 방식으로 path variable 로 받으면 되는데
 * /{roomId} 식으로
 * 근데 이 데이터 클래스에 Long 타입을 어디선가 받아서 넣고,
 * 다시 나한테 getRoomDetail(roomId) 보낼 때는
 * getRoomDetail(AccomasdfRoomData.roomId) 식으로 뽑아서 써야 하는데
 * Long 타입 id 만 있으면 되는데
 * 굳이 데이터 클래스에 넣었다가 다시 빼서 써야하는지 의 느낌이랄까 ?..,,
 */

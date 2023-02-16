import com.example.petsapce_week1.reservationbcw.reserveChildData
import java.io.Serializable

data class reserveMainData(
    val imgList: ArrayList<reserveChildData>,
    val roomName : String,
    val score: Float, val location:String, val date:String, val price:Int,
    val review:Int, val roomID:Int):
    Serializable
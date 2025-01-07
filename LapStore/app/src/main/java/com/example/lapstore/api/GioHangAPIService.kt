import com.example.lapstore.models.HinhAnh
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

data class GioHangResponse(
    val hinhanh: List<HinhAnh>
)
interface GioHangAPIService{
    @GET("GioHang/read.php")
    fun GetAllGioHang(): Call<GioHangResponse>

    @PUT("GioHang/update.php")
    fun UpdateGioHang():Call<GioHangResponse>


}
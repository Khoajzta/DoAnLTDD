import com.example.lapstore.models.GioHang
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

data class GioHangResponse(
    val giohang: List<GioHang>
)

data class UpdateResponse(
    val success: Boolean,
    val message: String
)

interface GioHangAPIService {
    @GET("GioHang/getgiohangtheomakhachhang.php")
    suspend fun getGioHangByKhachHang(
        @Query("MaKhachHang") MaKhachHang: Int
    ): GioHangResponse

    @PUT("GioHang/update.php")
    suspend fun updateGioHang(
        @Body gioHang: GioHang
    ): UpdateResponse
}
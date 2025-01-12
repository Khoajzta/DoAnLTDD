
import com.example.lapstore.models.HoaDonBan
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class addHoaDonBanResponse(
    val success: Boolean,
    val message: String
)

data class HoaDonBanResponse(
    val hoadonban: List<HoaDonBan>? // Có thể API trả về null nếu không có dữ liệu
)



interface HoaDonBanAPIService{
    @POST("HoaDonBan/create.php")
    suspend fun addHoaDonBan(
        @Body hoadon: HoaDonBan
    ): addHoaDonBanResponse

    @GET("HoaDonBan/getHoaDonBanByKhachHang.php")
    suspend fun getHoaDoByKhachHang(
        @Query("MaKhachHang") MaKhachHang: Int,
        @Query("TrangThai") TrangThai: Int
    ): HoaDonBanResponse

}
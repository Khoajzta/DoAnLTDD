import com.example.lapstore.models.ChiTietHoaDonBan
import com.example.lapstore.models.HoaDonBan
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


data class addChiTietHoaDonBanResponse(
    val success: Boolean,
    val message: String
)
interface ChiTietHoaDonBanAPIService{
    @POST("ChiTietHoaDonBan/create.php")
    suspend fun addChiTietHoaDonBan(
        @Body chitiethoadonban: ChiTietHoaDonBan
    ): addChiTietHoaDonBanResponse

    @GET("HoaDonBan/getHoaDonBanByKhachHang.php")
    suspend fun getHoaDoByKhachHang(
        @Query("MaKhachHang") MaKhachHang: Int,
        @Query("TrangThai") TrangThai: Int
    ): HoaDonBanResponse

}
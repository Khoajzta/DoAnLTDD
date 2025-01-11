import com.example.lapstore.models.GioHang
import com.example.lapstore.models.HinhAnh
import com.example.lapstore.models.SanPham
import com.example.lapstore.models.TaiKhoan
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

data class TaiKhoanResponse(
    val taikhoan: TaiKhoan?,
    val message: String?
)

data class taikhoanUpdateResponse(
    val success: Boolean,
    val message: String
)

interface TaiKhoanAPIService{
    @GET("TaiKhoan/checktaikhoan.php")
    suspend fun kiemTraTaiKhoan(
        @Query("tentaikhoan") tentaikhoan: String,
        @Query("matkhau") matkhau: String
    ): TaiKhoan

    @GET("TaiKhoan/show.php")
    suspend fun getTaiKhoanByTentaikhoan(
        @Query("tentaikhoan") tentaikhoan: String
    ): TaiKhoan

    @PUT("TaiKhoan/update.php")
    suspend fun updateTaiKhoan(
        @Body taikhoan: TaiKhoan
    ): taikhoanUpdateResponse
}
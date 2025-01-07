import com.example.lapstore.models.HinhAnh
import com.example.lapstore.models.SanPham
import com.example.lapstore.models.TaiKhoan
import retrofit2.http.GET
import retrofit2.http.Query

data class TaiKhoanRespose(
    val hinhanh: List<TaiKhoan>
)


interface TaiKhoanAPIService{
    @GET("HinhAnh/show.php")
    suspend fun getTaiKhoan(
        @Query("tentaikhoan") tentaikhoan: String,
        @Query("matkhau") matkhau: String
    ): TaiKhoan
}
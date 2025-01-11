import com.example.lapstore.models.HinhAnh
import com.example.lapstore.models.SanPham
import com.example.lapstore.models.TaiKhoan
import retrofit2.http.GET
import retrofit2.http.Query

data class TaiKhoanRespose(
    val taikhoan: List<TaiKhoan>
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
}
import com.example.lapstore.models.DiaChi
import com.example.lapstore.models.GioHang
import com.example.lapstore.models.SanPham
import retrofit2.http.GET
import retrofit2.http.Query

data class DiaChiResponse(
    val diachi: List<DiaChi>
)

interface DiaChiAPIService{
    @GET("DiaChi/show.php")
    suspend fun getDiaChiByMaDiaChi(
        @Query("id") MaDiaChi: Int
    ): DiaChi


    @GET("DiaChi/getdiachibykhachhang.php")
    suspend fun getDiaChiByMaKhachHang(
        @Query("MaKhachHang") MaKhachHang: Int?
    ): DiaChiResponse
}
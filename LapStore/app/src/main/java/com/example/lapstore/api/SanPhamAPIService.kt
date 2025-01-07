import com.example.lapstore.models.SanPham
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

data class SanPhamResponse(
    val sanpham: List<SanPham>
)

interface SanPhamAPIService{
    @GET("SanPham/read.php")
    suspend fun getAllSanPham(): SanPhamResponse


    @GET("SanPham/readByLoaiSanPham.php")
    suspend fun getSanPhamByLoai(
        @Query("MaLoaiSanPham") maLoaiSanPham: Int
    ): SanPhamResponse


    @GET("SanPham/show.php")
    suspend fun getSanPhamById(
        @Query("id") id: String
    ): SanPham
}
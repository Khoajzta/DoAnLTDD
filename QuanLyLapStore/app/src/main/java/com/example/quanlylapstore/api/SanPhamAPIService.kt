
import com.example.quanlylapstore.models.SanPham
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
    fun getAllSanPham(): Call<SanPhamResponse>

    @GET("SanPham/readByLoaiSanPham.php?MaLoaiSanPham=2")
    fun getAllSanPhamGaming(): Call<SanPhamResponse>

    @GET("SanPham/readByLoaiSanPham.php?MaLoaiSanPham=1")
    fun getAllSanPhamVanPhong(): Call<SanPhamResponse>

    @GET("SanPhamapi/searchSanPham.php")
    fun searchSanPham(@Query("search") search: String): Call<SanPhamResponse>

    @DELETE("SanPham/delete.php")
    fun deleteSanPham(@Query("id") id: Int): Call<SanPhamResponse>

}
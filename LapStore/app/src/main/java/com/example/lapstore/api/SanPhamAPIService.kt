import com.example.lapstore.models.SanPham
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

data class SanPhamResponse(
    val sanpham: List<SanPham>
)


interface SanPhamAPIService{
    @GET("SanPham/read.php")
    fun getAllSanPham(): Call<SanPhamResponse>

//    @GET("music/{id}")
//    suspend fun getMusic(
//        @Path("id") musicId:String,
//    ):SanPham
//
//    @POST("/music")
//    suspend fun addMusic(
//        @Body music:Music
//    ):retrofit2.Response<MusicResponse>
//
//    @PUT("/music/{id}")
//    suspend fun updateMusic(
//        @Path("id") musicId:String,
//        @Body music:Music
//    ):retrofit2.Response<MusicResponse>
//
//    @DELETE("/music/{id}")
//    suspend fun deleteMusic(
//        @Path("id") musicId:String
//    ):retrofit2.Response<MusicResponse>


}
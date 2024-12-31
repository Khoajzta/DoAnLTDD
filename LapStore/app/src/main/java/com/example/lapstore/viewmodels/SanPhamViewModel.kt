import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lapstore.api.QuanLyBanLaptopRetrofitClient
import com.example.lapstore.models.SanPham
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch/**/

class SanPhamViewModel : ViewModel() {
    var listSanPham: List<SanPham> by mutableStateOf(emptyList())

    @Composable
    fun getAllSanPham() {
        LaunchedEffect(Unit) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response =
                        QuanLyBanLaptopRetrofitClient.sanphamAPIService.getAllSanPham().execute()
                    if (response.isSuccessful) {
                        listSanPham = response.body()?.sanpham ?: emptyList()
                    }
                } catch (e: Exception) {
                }
            }
        }


//        fun getAllSanPham() {
//            viewModelScope.launch(Dispatchers.IO) {
//                try {
//                    listSanPham = QuanLyBanLaptopRetrofitClient.sanphamAPIService.getAllSanPham()
//                } catch (e: Exception) {
//                    Log.e("SanPhamViewModel", "Error getiting music", e)
//                }
//            }
//        }
//    fun getMusic(id: String){
//        viewModelScope.launch(Dispatchers.IO){
//            try {
//                music = MusicRetrofitClient.musicAPIService.getMusic(id)
//            } catch (e: Exception){
//                Log.e("MusicViewModel", "Error getting music", e)
//            }
//        }
//    }
//    fun addMusic(music: Music){
//        viewModelScope.launch(Dispatchers.IO){
//            try {
//                musicAddResult = MusicRetrofitClient.musicAPIService.addMusic(music).message()
//                listMusic = MusicRetrofitClient.musicAPIService.getAllMusic()
//            } catch (e: Exception){
//                Log.e("MusicViewModel", "Error adding music", e)
//            }
//        }
//    }
//    fun updateMusic(musicID : String,music: Music) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                musicUpdateResult =
//                    MusicRetrofitClient.musicAPIService.updateMusic(musicID, music).message()
//                listMusic = MusicRetrofitClient.musicAPIService.getAllMusic()
//            } catch (e: Exception) {
//                Log.e("MusicViewModel", "Error updating music", e)
//
//            }
//        }
//    }
//    fun newMusic():Int{
//        if(listMusic.isEmpty()){
//            return 1
//        }
//        return listMusic.maxOf { it.id.toInt() }+1
//    }
//
//    fun deleteMusic(musicId: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response = MusicRetrofitClient.musicAPIService.deleteMusic(musicId)
//                if (response.isSuccessful) {
//                    listMusic = MusicRetrofitClient.musicAPIService.getAllMusic()
//                } else {
//                    Log.e("MusicViewModel", "Failed to delete music: ${response.message()}")
//                }
//            } catch (e: Exception) {
//                Log.e("MusicViewModel", "Error deleting music", e)
//            }
//        }
//    }

    }
}


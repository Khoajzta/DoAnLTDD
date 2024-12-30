package com.example.lapstore.apis

import com.example.lapstore.models.SanPham
import com.example.lapstore.models.SanPhamResponse
import retrofit2.http.GET

interface SanPhamAPIService {
    @GET("SanPham/read.php")
    suspend fun getAllSanPham(): SanPhamResponse
}



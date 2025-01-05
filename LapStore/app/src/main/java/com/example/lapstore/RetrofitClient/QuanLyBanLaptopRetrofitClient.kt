// QuanLyBanLaptopRetrofitClient.kt
package com.example.lapstore.api

import SanPhamAPIService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import KhachHangAPIService

object Constants {
    const val BASE_URL = "http://10.0.2.2/lap_store_api/api/" // Đảm bảo URL chính xác
}

object QuanLyBanLaptopRetrofitClient {
    val sanphamAPIService: SanPhamAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(SanPhamAPIService::class.java)
    }
    val khachHangAPIService: KhachHangAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(KhachHangAPIService::class.java)
    }
}

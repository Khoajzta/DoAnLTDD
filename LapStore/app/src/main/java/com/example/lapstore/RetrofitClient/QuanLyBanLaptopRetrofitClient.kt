// QuanLyBanLaptopRetrofitClient.kt
package com.example.lapstore.api

import ChiTietHoaDonBanAPIService
import DiaChiAPIService
import GioHangAPIService
import HinhAnhAPIService
import HoaDonBanAPIService
import KhachHangAPIService
import SanPhamAPIService
import TaiKhoanAPIService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constants {
    const val BASE_URL = "https://f2aa-2402-800-6315-ddaf-14a4-6c86-de1c-4a20.ngrok-free.app/lap_store_api/api/"
}

object QuanLyBanLaptopRetrofitClient {
    val sanphamAPIService: SanPhamAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(SanPhamAPIService::class.java)
    }

    val hinhAnhAPIService: HinhAnhAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(HinhAnhAPIService::class.java)
    }

    val taiKhoanAPIService: TaiKhoanAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(TaiKhoanAPIService::class.java)
    }

    val khachHangAPIService: KhachHangAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(KhachHangAPIService::class.java)
    }

    val giohangAPIService: GioHangAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(GioHangAPIService::class.java)
    }

    val hoaDonBanAPIService: HoaDonBanAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(HoaDonBanAPIService::class.java)
    }

    val diaChiAPIService: DiaChiAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(DiaChiAPIService::class.java)
    }

    val chiTietHoaDonBanAPIService: ChiTietHoaDonBanAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ChiTietHoaDonBanAPIService::class.java)
    }
}

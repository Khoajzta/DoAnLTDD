package com.example.lapstore.ui

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.lapstore.models.TaiKhoan
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.map


class TaiKhoanStore (private val context :Context){
    companion object{
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("taikhoan")
        private val TENTAIKHOAN = stringPreferencesKey("TenTaiKhoan")
        private val MAKHACHHANG = intPreferencesKey("MaKhachHang")
        private val MatKhau = stringPreferencesKey("MatKhau")
        private val TaiKhoan_TOKEN_KEY = stringPreferencesKey("taikhoan_token")
    }

    suspend fun saveUserInfo(taiKhoan: TaiKhoan){
        context.dataStore.edit {
            it[TENTAIKHOAN] = taiKhoan.TenTaiKhoan
            it[MAKHACHHANG] = taiKhoan.MaKhachHang
            it[MatKhau] = taiKhoan.MatKhau
            it[TaiKhoan_TOKEN_KEY] = taiKhoan.taikhoan_token
        }
    }

    fun getTaiKhoan() = context.dataStore.data.map {
        TaiKhoan(
            TenTaiKhoan = it[TENTAIKHOAN]?: "",
            MaKhachHang = it[MAKHACHHANG]?: 0,
            MatKhau = it[MatKhau]?: "",
            taikhoan_token = it[TaiKhoan_TOKEN_KEY]?: "",
        )
    }

    val getAccessToken: Flow<String> = context.dataStore.data.map {
            preferences ->
        preferences[TaiKhoan_TOKEN_KEY]?:""
    }

    suspend fun logoutTaiKhoan(){
        context.dataStore.edit {
            it.clear()
        }
    }
}
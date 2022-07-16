package com.example.summerassessment.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.summerassessment.base.APP
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

inline fun <reified T> create():T{
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("${APP.BASE_URL}")
        .build()
        .create(T::class.java)
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.decrypt() {

    val raw = APP.PASSWORD.toByteArray()
    val secretKeySpec = SecretKeySpec(raw, "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec)
    val encrypted = Base64.getDecoder().decode(this.replace("ftp://",""))
    val original = cipher.doFinal(encrypted)
    val result = String(original)
    println(result)
}
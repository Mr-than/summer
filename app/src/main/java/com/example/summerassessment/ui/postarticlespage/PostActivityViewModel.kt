package com.example.summerassessment.ui.postarticlespage

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.summerassessment.base.APP
import com.example.summerassessment.model.*
import com.example.summerassessment.repository.PostActivityRepository
import com.google.gson.Gson
import com.qiniu.android.common.FixedZone
import com.qiniu.android.storage.Configuration
import com.qiniu.android.storage.UploadManager
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.Exception
import java.util.*
import kotlin.concurrent.thread

/**
 *   description:发文章页的vm
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */
class PostActivityViewModel : ViewModel() {


    @RequiresApi(Build.VERSION_CODES.O)
    fun getToken(data: ByteArray, name: String, content: String) {
            val ob = PostActivityRepository.getToken(name)
            ob.map {
                it
            }
                .observeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(object : Observer<PostTokenData> {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                    }

                    override fun onNext(t: PostTokenData) {
                        val token = t.data.token
                        val a = t.data.token.split(":")
                        val b = a[a.size - 1]

                        val base64 = Base64.getDecoder()
                        val decodedString = String(base64.decode(b.encodeToByteArray()))
                        val gson = Gson()
                        val key = gson.fromJson<Key>(decodedString, Key::class.java)
                        val scope = key.scope
                        val path = scope.split("/")
                        val p = "open-source/file/${path[path.size - 1]}"
                        postImgJoke(data, token, p, content)
                    }

                })

    }

    fun postJoke(content: String) {
        PostActivityRepository.postText(content).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<AllData> {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                }

                override fun onNext(t: AllData?) {
                    Toast.makeText(APP.getApp(), t?.msg, Toast.LENGTH_SHORT).show()
                }
            })


    }

    private fun postImgJoke(data: ByteArray, token: String, key: String, content: String) {


        val config = Configuration.Builder()
            .zone(FixedZone.zone2)
            .build()
        val uploadManager = UploadManager(config)
        uploadManager.put(
            data,
            key,
            token,
            { key, info, res -> //res 包含 hash、key 等信息，具体字段取决于上传策略的设置

                if (info.isOK) {
                    thread {

                        val gson = Gson()

                        val hash = gson.fromJson(res.toString(), HashData::class.java).hash

                        PostActivityRepository.postImg(content, hash)
                            .observeOn(Schedulers.io())
                            .subscribe(object : Observer<AllData> {
                                override fun onCompleted() {

                                }

                                override fun onError(e: Throwable?) {
                                    e?.printStackTrace()
                                }

                                override fun onNext(t: AllData?) {

                                }
                            })

                    }
                } else {
                    Log.i("qiniu", "Upload Fail")
                    //如果失败，这里可以把 info 信息上报自己的服务器，便于后面分析上传错误原因
                }
                Log.i("qiniu", "$key,\r\n $info,\r\n $res")
            },
            null
        )

    }
}


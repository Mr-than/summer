package com.example.summerassessment.ui.postarticlespage

import android.util.Log
import androidx.lifecycle.ViewModel
import com.qiniu.android.common.FixedZone
import com.qiniu.android.storage.Configuration
import com.qiniu.android.storage.UploadManager

class PostActivityViewModel: ViewModel() {


    fun postImgJoke(data:ByteArray){
        val config = Configuration.Builder()
            .zone(FixedZone.zone2)
            .build()
        val uploadManager = UploadManager(config)
        uploadManager.put(data,
            "open-source/file/1869c3bd86194e8d9c773f2a73ad60df.jpeg",
            "dDzvWsb8wd1RAL-icMJHfM_YonGrmtPhIIlIcL1K:QdiZ_dvXF7gupySDs0Mlu63dIeM=:eyJzY29wZSI6Impva2VzLXBpYzpvcGVuLXNvdXJjZS9maWxlLzE4NjljM2JkODYxOTRlOGQ5Yzc3M2YyYTczYWQ2MGRmLmpwZWciLCJkZWFkbGluZSI6MTY1ODYwNTU2OH0=",
            { key, info, res -> //res 包含 hash、key 等信息，具体字段取决于上传策略的设置
                if (info.isOK) {
                    Log.i("qiniu", "Upload Success")
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
package com.android.mvvm.data.remote

import android.content.Context
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType

class MockRequestInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val header = request.header(MOCK)

        if (header != null) {
            val filename = LOCAL_FILE_NAME
            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .message("")
                .code(200)
                .body(
                    ResponseBody.create(
                        JSON_MEDIA_TYPE,
                        context.readFileFromAssets("mocks/$filename.json")
                    )
                )
                .build()
        }

        return chain.proceed(request.newBuilder().removeHeader(MOCK).build())
    }

    fun Context.readFileFromAssets(filePath: String): String {
        return resources.assets.open(filePath).bufferedReader().use {
            it.readText()
        }
    }

    companion object {
        private val JSON_MEDIA_TYPE = "application/json".toMediaType()
        private const val MOCK = "mock"
        private const val LOCAL_FILE_NAME = "drinkslocalfile"
    }
}
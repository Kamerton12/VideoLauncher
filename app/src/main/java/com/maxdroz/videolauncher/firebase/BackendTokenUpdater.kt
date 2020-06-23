package com.maxdroz.videolauncher.firebase

import com.maxdroz.videolauncher.Settings.URL
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import kotlin.coroutines.resume

object BackendTokenUpdater {

    fun updateTokenSync(token: String) {
        GlobalScope.launch(Dispatchers.IO) {
            updateToken(token)
        }.start()
    }

    private suspend fun updateToken(token: String) {
        return suspendCancellableCoroutine { continuation ->
            val requestBody = JSONObject().put("user", JSONObject().put("token", token)).toString().toRequestBody()
            val request = Request.Builder()
                .url("$URL/api/user/1")
                .header("Content-Type", "application/json")
                .put(requestBody)
                .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    continuation.resume(Unit)
                }

                override fun onFailure(call: Call, e: IOException) {
                    continuation.cancel(e)
                }
            })
            continuation.invokeOnCancellation {
                call.cancel()
            }
        }
    }

    val client = OkHttpClient()
}
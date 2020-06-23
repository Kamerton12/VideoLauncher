package com.maxdroz.videolauncher.firebase

import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("test tag", message.data.toString())
        val uri = message.data.getOrElse("url", { null }) ?: return
        val intent = Intent(Intent.ACTION_VIEW)
        val videoUri = Uri.parse(uri)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setDataAndType(videoUri, "application/x-mpegURL")
        intent.setPackage("com.mxtech.videoplayer.ad")
        Handler(Looper.getMainLooper()).post {
            application.startActivity(intent)
        }
    }

    override fun onNewToken(token: String) {
        BackendTokenUpdater.updateTokenSync(token)
    }
}
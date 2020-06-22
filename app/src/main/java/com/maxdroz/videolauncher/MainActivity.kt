package com.maxdroz.videolauncher

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle


/**
 * Loads [MainFragment].
 */
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(Intent.ACTION_VIEW)
        val videoUri = Uri.parse("https://load.hdrezka-ag.net//movies/d3c24acb395af16111ad3551e3aba52215517e7f/94069293c62d723387ff0b893d120cca:2020062501/720.mp4:hls:manifest.m3u8")
        intent.setDataAndType(videoUri, "application/x-mpegURL")
        intent.setPackage("com.mxtech.videoplayer.pro")
        startActivity(intent)

        finish()
    }
}
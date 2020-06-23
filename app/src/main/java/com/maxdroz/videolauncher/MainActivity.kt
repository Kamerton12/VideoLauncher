package com.maxdroz.videolauncher

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.maxdroz.videolauncher.firebase.BackendTokenUpdater


/**
 * Loads [MainFragment].
 */
class MainActivity : Activity() {

    private val TAG = "test tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                val token = task.result?.token ?: return@OnCompleteListener

                Log.d(TAG, token)
                BackendTokenUpdater.updateTokenSync(token)
            })
    }
}
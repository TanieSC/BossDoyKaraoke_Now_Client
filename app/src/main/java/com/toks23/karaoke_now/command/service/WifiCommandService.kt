package com.toks23.karaoke_now.command.service

import android.net.wifi.WifiManager
import android.widget.ListView
import android.widget.Toast
import android.content.Context.WIFI_SERVICE
import android.app.Activity


class WifiCommandService{
    private val constants: ConstantsService? = ConstantsService()
    private val MY_PERMISSIONS_ACCESS_COARSE_LOCATION = 1
    private var wifiList: ListView? = null
    private var wifiManager: WifiManager? = null

    fun WifiCommandService(activity: Activity, wifiDeviceList: ListView){
        wifiList = wifiDeviceList
        wifiManager = activity.applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        if (!wifiManager!!.isWifiEnabled) {
            Toast.makeText(activity.applicationContext, "Turning WiFi ON...", Toast.LENGTH_LONG).show()
            wifiManager!!.isWifiEnabled = true
        }
    }

    fun startScan(){
        wifiManager?.startScan()
    }
}



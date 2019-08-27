package com.toks23.karaoke_now

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Toast

class WiFiBroadcastReceiver : BroadcastReceiver() {
    var wifiManager: WifiManager? = null
    var sb: StringBuilder? = null
    var wifiDeviceList: ListView? = null

    fun WiFiBroadcastReceiver(wifiManager: WifiManager, wifiDeviceList: ListView) {
        this.wifiManager = wifiManager
        this.wifiDeviceList = wifiDeviceList
    }
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val action = intent.action
        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION == action) {
            sb = StringBuilder()
            val wifiList = wifiManager?.getScanResults()
            val deviceList = ArrayList<Any>()
            if (wifiList != null) {
                for (scanResult in wifiList) {
                    sb!!.append("\n").append(scanResult.SSID).append(" - ").append(scanResult.capabilities)
                    deviceList.add(scanResult.SSID + " - " + scanResult.capabilities)
                }
            }
            Toast.makeText(context, sb, Toast.LENGTH_SHORT).show()
            val arrayAdapter = ArrayAdapter(context, R.layout.list_item, R.id.tv, deviceList.toArray())
            Log.d("WIFI_CONNECT", arrayAdapter.toString())
            
            wifiDeviceList?.adapter = arrayAdapter
        }
    }
}

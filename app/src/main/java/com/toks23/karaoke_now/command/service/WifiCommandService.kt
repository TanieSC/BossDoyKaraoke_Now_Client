package com.toks23.karaoke_now.command.service

import android.app.Activity
import android.content.Context
import android.net.wifi.WifiManager
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Toast
import android.content.Intent
import android.content.BroadcastReceiver
import android.util.Log
import com.toks23.karaoke_now.R

class WifiCommandService : BroadcastReceiver(){
    private var _acitvity: Activity? = null
    private val _constants: ConstantsCommandService? = ConstantsCommandService()
    private var _wifiManager: WifiManager? = null
    private var _listview : ListView? = null
    private val MY_PERMISSIONS_ACCESS_COARSE_LOCATION = 1

    var sb: StringBuilder? = null

    fun WifiCommandService(activity: Activity, listView: ListView?) {
        _wifiManager = _acitvity?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (!_wifiManager!!.isWifiEnabled) {
            Toast.makeText( _acitvity?.applicationContext, "Turning WiFi ON...", Toast.LENGTH_LONG).show()
            _wifiManager!!.isWifiEnabled = true
        }


        _wifiManager!!.startScan()
    }

    override fun onReceive(context: Context, intent: Intent?) {
        val action = intent?.action
        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION == action) {
            sb = StringBuilder()
            val wifiList = _wifiManager?.scanResults
            val deviceList = ArrayList<Any>()
            for (scanResult in wifiList!!) {
                sb!!.append("\n").append(scanResult.SSID).append(" - ").append(scanResult.capabilities)
                Log.d("WIFI_LIST", scanResult.SSID)
                deviceList.add(scanResult.SSID + " - " + scanResult.capabilities)
            }
            Toast.makeText(_acitvity, sb, Toast.LENGTH_SHORT).show()
            val arrayAdapter = ArrayAdapter(context, R.layout.list_item, R.id.tv, deviceList.toArray())
            _listview?.adapter = arrayAdapter
        }
    }
}

/*class WifiCommandService (activity: Activity, listView: ListView?) : BroadcastReceiver() {
    private var _acitvity: Activity? = activity
    private val _constants: ConstantsCommandService? = ConstantsCommandService()
    private var _wifiManager: WifiManager? = null
    private var _listview : ListView? = listView
    private val MY_PERMISSIONS_ACCESS_COARSE_LOCATION = 1

    fun initialized() {
        _wifiManager = _acitvity?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (!_wifiManager!!.isWifiEnabled) {
            Toast.makeText( _acitvity?.applicationContext, "Turning WiFi ON...", Toast.LENGTH_LONG).show()
            _wifiManager!!.isWifiEnabled = true
        }

        _wifiManager!!.startScan()

        wifiReceiver
    }

    private val wifiReceiver = object : BroadcastReceiver() {
        var sb: StringBuilder? = null
        override fun onReceive(context: Context, intent: Intent?) {
            val action = intent?.action
            if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION == action) {
                sb = StringBuilder()
                val wifiList = _wifiManager?.scanResults
                val deviceList = ArrayList<Any>()
                for (scanResult in wifiList!!) {
                    sb!!.append("\n").append(scanResult.SSID).append(" - ").append(scanResult.capabilities)
                    Log.d("WIFI_LIST", scanResult.SSID)
                    deviceList.add(scanResult.SSID + " - " + scanResult.capabilities)
                }
                Toast.makeText(_acitvity, sb, Toast.LENGTH_SHORT).show()
                val arrayAdapter = ArrayAdapter(context, R.layout.list_item, R.id.tv, deviceList.toArray())
                _listview?.adapter = arrayAdapter
            }
        }
    }
}*/



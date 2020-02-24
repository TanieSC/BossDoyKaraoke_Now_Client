package com.toks23.karaoke_now.command.service

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.toks23.karaoke_now.ConstantsGlobal
import java.io.*
import java.net.Socket


class WifiLanCommandService(private val dstAddress: String, private val dstPort: Int, private val handler: Handler){

    private val tag: String = "WifiLanCommandService"
    private val d: Boolean = true

    private var _connectThread: ConnectThread? = null
    private var _connectedThread: ConnectedThread? = null
    private var _state = 0

    @Synchronized
    private fun setState(state: Int) {
        if (d) Log.d(tag, "setState() $_state -> $state" )
        _state = state

        // Give the new state to the Handler so the UI Activity can update
        handler.obtainMessage(ConstantsGlobal.MESSAGE_STATE_CHANGE, state, -1).sendToTarget()
    }

    @Synchronized
    fun getState(): Int {
        return _state
    }

    @Synchronized
    fun connect() {
        if (d) Log.d(tag, "connect to: BossDoy Karaoke_Now")

        // Cancel any thread attempting to make a connection
        if (_state == ConstantsGlobal.STATE_CONNECTING) {
            if (_connectThread != null) {
                _connectThread?.cancel()
                _connectThread = null
            }
        }
        // Cancel any thread currently running a connection
        if (_connectedThread != null) {
            _connectedThread?.cancel()
            _connectedThread = null
        }
        // Start the thread to connect with the given device
        _connectThread = ConnectThread()
        _connectThread?.start()
        setState(ConstantsGlobal.STATE_CONNECTING)
    }

    @Synchronized
    fun connected(socket: Socket?) {
        if (d) Log.d(tag, "connected")

        // Cancel the thread that completed the connection
        if (_connectThread != null) {
            _connectThread?.cancel()
            _connectThread = null
        }

        // Cancel any thread currently running a connection
        if (_connectedThread != null) {
            _connectedThread?.cancel()
            _connectedThread = null
        }
        // Start the thread to manage the connection and perform transmissions
        _connectedThread = ConnectedThread(socket)
        _connectedThread?.start()
        // Send the name of the connected device back to the UI Activity
        val msg: Message = handler.obtainMessage(ConstantsGlobal.MESSAGE_DEVICE_NAME)
        val bundle = Bundle()
        bundle.putString(ConstantsGlobal.DEVICE_NAME, "Karaoke_Now")
        msg.data = bundle
        handler.sendMessage(msg)
        setState(ConstantsGlobal.STATE_CONNECTED)
    }

    @Synchronized
    fun disconnect() {
        if (d) Log.d(tag,"stop")

        if (_connectThread != null) {
            _connectThread?.cancel()
            _connectThread = null
        }
        if (_connectedThread != null) {
            _connectedThread?.cancel()
            _connectedThread = null
        }
        setState(ConstantsGlobal.STATE_NONE)
    }

    fun write(out: ByteArray?) { // Create temporary object
        var r: ConnectedThread?
        // Synchronize a copy of the ConnectedThread
        synchronized(this) {
            if (_state != ConstantsGlobal.STATE_CONNECTED) return
            r = _connectedThread
        }
        // Perform the write unsynchronized
        r?.write(out)
    }

    fun write(out: Int) { // Create temporary object
        var r: ConnectedThread?
        // Synchronize a copy of the ConnectedThread
        synchronized(this) {
            if (_state != ConstantsGlobal.STATE_CONNECTED) return
            r = _connectedThread
        }
        // Perform the write unsynchronized
        r?.write(out)
    }

    fun write(out: String) { // Create temporary object
        var r: ConnectedThread?
        // Synchronize a copy of the ConnectedThread
        synchronized(this) {
            if (_state != ConstantsGlobal.STATE_CONNECTED) return
            r = _connectedThread
        }
        // Perform the write unsynchronized
        r?.write(out)
    }

    private fun connectionFailed() {
        setState(ConstantsGlobal.STATE_NONE)
        // Send a failure message back to the Activity
        val msg: Message = handler.obtainMessage(ConstantsGlobal.MESSAGE_TOAST)
        val bundle = Bundle()
        bundle.putString(ConstantsGlobal.TOAST, "Unable to connect device")
        msg.data = bundle
        handler.sendMessage(msg)
    }

    private fun connectionLost() {
        setState(ConstantsGlobal.STATE_NONE)
        // Send a failure message back to the Activity
        val msg: Message = handler.obtainMessage(ConstantsGlobal.MESSAGE_TOAST)
        val bundle = Bundle()
        bundle.putString(ConstantsGlobal.TOAST, "Device connection was lost")
        msg.data = bundle
        handler.sendMessage(msg)
    }

    inner class ConnectThread : Thread() {
        var socket: Socket? = null
        override fun run() {
            Log.i(tag, "BEGIN _connectThread")

            name = "ConnectThread"

            if(socket == null)
            {
                connectionFailed()
                // Close the socket
                try {
                    socket?.close()
                } catch (e2: IOException) {
                    Log.e(tag, "unable to close() socket during connection failure", e2
                    )
                }
                return
            }

            // Reset the ConnectThread because we're done
            synchronized(this@WifiLanCommandService) { _connectThread = null }
            // Start the connected thread
            connected(socket)
        }

        fun cancel() {
            try {
                socket?.close()
            } catch (e: IOException) {
                Log.e(tag, "close() of connect socket failed", e)
            }
        }

        init {
            var tmp: Socket? = null
            try {
                tmp = Socket(dstAddress, dstPort)
            } catch (e: IOException) {
                Log.e(tag, "create() failed", e)
            }
            socket = tmp
        }
    }

    inner class ConnectedThread(socket: Socket?) : Thread() {
        private var socket: Socket? = null
        private var inStream: InputStream
        private var outStream: OutputStream

        override fun run() {
            Log.i(tag, "BEGIN _connectedThread")
            val buffer = ByteArray(1024)
            // Keep listening to the InputStream while connected
            while (true) {
                try { // Read from the InputStream
                    val bytes = inStream.read(buffer)
                    handler.obtainMessage(ConstantsGlobal.MESSAGE_FROM_SERVER, bytes, -1, buffer)
                        .sendToTarget()
                } catch (e: IOException) {
                    Log.e(tag, "disconnected", e)
                    connectionLost()
                    break
                }
            }
        }

        fun write(buffer: ByteArray?) {
            try {
                outStream.write(buffer)
                // Share the sent message back to the UI Activity
//                mHandler.obtainMessage(BluetoothChat.MESSAGE_WRITE, -1, -1, buffer)
//                        .sendToTarget();
            } catch (e: IOException) {
                Log.e(tag, "Exception during write", e)
            }
        }

        fun write(out: Int) {
            try {
                outStream.write(out)
                // Share the sent message back to the UI Activity
//                mHandler.obtainMessage(BluetoothChat.MESSAGE_WRITE, -1, -1, buffer)
//                        .sendToTarget();
            } catch (e: IOException) {
                Log.e(tag, "Exception during write", e)
            }
        }

        fun write(out: String)
        {
            try {
                if (null != socket) {
                    val outMsg = PrintWriter(BufferedWriter(OutputStreamWriter(outStream)), true)
                    outMsg.println(out)
                    //out.write(msg)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun cancel() {
            try {
                val mdgBuff: ByteArray = ConstantsGlobal.EXIT_CMD.toString().toByteArray()
                outStream.write(mdgBuff)
                this.socket?.close()
            } catch (e: IOException) {
                Log.e(tag, "close() of connect socket failed", e)
            }
        }

        init {
            Log.d(tag, "create ConnectedThread")
            this.socket = socket
            var tmpIn: InputStream? = null
            var tmpOut: OutputStream? = null

            try {
                tmpIn = socket?.inputStream
                tmpOut = socket?.outputStream
            } catch (e: IOException) {
                Log.e(tag, "temp sockets not created", e)
            }
            inStream = tmpIn!!
            outStream = tmpOut!!
        }
    }

    /*override fun doInBackground(vararg p0: Void?): Void? {

        try {
            _socket = Socket(dstAddress, dstPort)

            try {
                _inStream = _socket.getInputStream()
                _outStream = _socket.getOutputStream()
            } catch (e: IOException) {
            }

            val byteArrayOutputStream = ByteArrayOutputStream(1024)
            val buffer = ByteArray(1024)
            var bytesRead: Int

            while (_inStream?.read(buffer).also { bytesRead = it!! } != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead)
                _response += byteArrayOutputStream.toString("UTF-8")
            }

        }catch (ue : UnknownHostException){
        }catch (e : IOException){
        }finally{
            if(_socket != null){
                try {
                    _socket.close()
                } catch (e : IOException) {
                }
            }
        }
        return null
    }*/

}
package com.toks23.karaoke_now

class ConstantsGlobal {
    companion object {
        const val EXIT_CMD = -1
        const val STATE_NONE = 0 // we're doing nothing
        const val STATE_LISTEN = 1 // now listening for incoming connections
        const val STATE_CONNECTING = 2 // now initiating an outgoing connection
        const val STATE_CONNECTED = 3 // now connected to a remote device

        const val MESSAGE_STATE_CHANGE = 4
        const val MESSAGE_FROM_SERVER = 5
        const val MESSAGE_DEVICE_NAME = 6
        const val MESSAGE_TOAST = 7

        const val DEVICE_NAME = "device_name"
        const val TOAST = "toast"
        const val MESSAGE = "message"
    }
}
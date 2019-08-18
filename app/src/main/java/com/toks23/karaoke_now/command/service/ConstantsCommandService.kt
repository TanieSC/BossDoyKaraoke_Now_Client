package com.toks23.karaoke_now.command.service

class ConstantsCommandService {

    // Constants that indicate the current connection state
    val STATE_NONE = 0       // we're doing nothing
    val STATE_LISTEN = 1     // now listening for incoming connections
    val STATE_CONNECTING = 2 // now initiating an outgoing connection
    val STATE_CONNECTED = 3  // now connected to a remote device
}
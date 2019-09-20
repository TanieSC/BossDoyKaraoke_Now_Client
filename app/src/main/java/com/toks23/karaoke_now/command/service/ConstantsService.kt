package com.toks23.karaoke_now.command.service

class ConstantsService {

    // Constants that indicate the current connection state
    val STATE_NONE = 0       // we're doing nothing
    val STATE_LISTEN = 1     // now listening for incoming connections
    val STATE_CONNECTING = 2 // now initiating an outgoing connection
    val STATE_CONNECTED = 3  // now connected to a remote device

    val GET_ALL_SONGS = 0
    val SEARCH_SONGS_ARTIST = 1
    val SEARCH_SONGS_TITLE= 2
}
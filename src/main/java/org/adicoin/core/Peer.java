package org.adicoin.core;

/**
 * From the developer documentation --------------------------------
 * 
 * The Bitcoin network protocol allows full nodes (peers) to collaboratively
 * maintain a peer-to-peer network for block and transaction exchange.
 * 
 * Connecting to a peer is done by sending a version message, which contains
 * your version number, block, and current time to the remote node. The remote
 * node responds with its own version message. Then both nodes send a verack
 * message to the other node to indicate the connection has been established.
 * Once connected, the client can send to the remote node getaddr and addr
 * messages to gather additional peers.
 * 
 * @author aditya
 *
 */
public class Peer {

}

package org.adicoin.core;

import java.net.InetSocketAddress;

public interface PeerDiscovery {
	
	InetSocketAddress[] getPeers() throws PeerDiscoveryException;

}

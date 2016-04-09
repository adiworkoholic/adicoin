package org.adicoin.core;

import java.net.InetSocketAddress;

public class DnsPeerDiscovery implements PeerDiscovery {

	@Override
	public InetSocketAddress[] getPeers() throws PeerDiscoveryException {
		throw new PeerDiscoveryException("Not Implemented Yet!");
	}

}

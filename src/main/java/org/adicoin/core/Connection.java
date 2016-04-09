package org.adicoin.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Connection {
	private static final Logger logger = LoggerFactory.getLogger(Connection.class);
	
	private final InputStream in;
	private final OutputStream out;
	
	private final Socket socket;
	private final InetAddress remoteIp;
	private AdiCoinNetworkConfig config;
	
	public Connection(InetAddress remoteIp, AdiCoinNetworkConfig config, int connectTimeOut) throws IOException {
        this.config = config;
        this.remoteIp = remoteIp;


        InetSocketAddress address = new InetSocketAddress(remoteIp, config.port);
        socket = new Socket();
        socket.connect(address, connectTimeOut);
        
        out = socket.getOutputStream();
        in = socket.getInputStream();
	}

}

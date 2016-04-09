package org.adicoin.core;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Contains all the coin networks default configuration and other common network constants.
 * @author aditya
 *
 */
public class AdiCoinNetworkConfig implements Serializable {

	private static final long serialVersionUID = -1855099976260615556L;
	
	
    public static final int COIN_PROTOCOL_VERSION = 31800;
    public int port;
    public PlainBlock genesisBlock;
    public BigInteger proofOfWorkLowerBound;
    
    /** Header magic bytes to identify the start of a coin network packet */
    public long packetMagicHeader;
    /** First byte of a coin address*/
    public int addressHeader;
    
    

    public static AdiCoinNetworkConfig initAdiCoinNetwork() {
    	AdiCoinNetworkConfig config = new AdiCoinNetworkConfig();
    	// TODO: Set all the default values for the config fields.
    	throw new RuntimeException("Not Implemented Yet!");
    }

    

}

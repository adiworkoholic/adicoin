package org.adicoin.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;


/**
 * Abstract representation of a message which can be anything in Adicoin 
 * - Transaction OR Block OR Any other data that can be sent to the peer.
 * 
 * Sub types should implement parse and their serialization methods.
 * When they are received over the wire they are constructed by "parsing" the payload as per their reqs
 *  
 * 
 * @author aditya
 *
 */
public abstract class Message implements Serializable {
	private static final long serialVersionUID = 5352054986519007626L;
	private AdiCoinNetworkConfig config;
    protected transient int protocolVersion;
    protected transient byte[] bytes;
    
    protected transient int offset; // Where the message starts in the byte array
    protected transient int cursor; // Where we are in the byte array while parsing

	public Message(AdiCoinNetworkConfig config) {
		this.config = config;
	}

	public Message(AdiCoinNetworkConfig params, byte[] msg, int offset) throws ProtocolException {
		this(params);
		this.bytes = msg;
		this.offset = offset;
		parse();
		
	}

	public byte[] serialize() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			serializeToStream(stream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return stream.toByteArray();
	}
	
	abstract void serializeToStream(ByteArrayOutputStream stream) throws IOException;
	
    abstract void parse() throws ProtocolException;
    
    
    int getMessageSize() {
        return cursor - offset;
    }
    
    long readUint32() {
        long u = Utils.readUint32(bytes, cursor);
        cursor += 4;
        return u;
    }
    
    /* Some methods used while parsing */
    
    Sha256Hash readHash() {
        byte[] hash = new byte[32];
        System.arraycopy(bytes, cursor, hash, 0, 32);
        // We have to flip it around, as it's been read off the wire in little endian.
        // Not the most efficient way to do this but the clearest.
        hash = Utils.reverseBytes(hash);        
        cursor += 32;
        return new Sha256Hash(hash);
    }
    
    
}

package org.adicoin.core;

import java.io.Serializable;
import java.math.BigInteger;

import com.google.bitcoin.core.ECKey;

public class KeyPair implements Serializable {
	
	private static final long serialVersionUID = 4052825642783975701L;
	private final ECKey ecKey;
	
	
	public KeyPair() {
		this.ecKey = new ECKey();
	}
    /**
     * Creates an KeyPair given only the private key. This works because EC public keys are derivable from their
     * private keys by doing a multiply with the generator value.
     */
    public KeyPair(BigInteger privKey) {
        this.ecKey = new ECKey(privKey);
    }
    
    
	
    /** Gets the hash160 form of the public key (as seen in addresses). */
    public byte[] getPubKeyHash() {
    	return ecKey.getPubKeyHash();
    }
	
    /**
     * Gets the raw public key value. This appears in transaction scriptSigs. Note that this is <b>not</b> the same
     * as the pubKeyHash/address.
     */
    public byte[] getPubKey() {
        return ecKey.getPubKey();
    }
    
    
    
    
    
	/**
	 * Returns the address that corresponds to the public part of this ECKey. 
	 */
	public Address toAddress(AdiCoinNetworkConfig config) {
	    byte[] hash160 = Utils.sha256hash160(ecKey.getPubKey());
	    return new Address(config, hash160);
	}
    
    public String toString() {
    	return ecKey.toString();
    }

    
    
    
    /**
     * Calcuates an ECDSA signature in DER format for the given input hash. Note that the input is expected to be
     * 32 bytes long.
     */
    public byte[] sign(byte[] input) {
    	return ecKey.sign(input);
    }
    
    /**
     * Verifies the given ASN.1 encoded ECDSA signature against a hash using the public key.
     * @param data Hash of the data to verify.
     * @param signature ASN.1 encoded signature.
     */
    public boolean verify(byte[] data, byte[] signature) {
        return ECKey.verify(data, signature, getPubKey());
    }
    
    
    
    
    /**
     * Construct an ECKey from an ASN.1 encoded private key. These are produced by OpenSSL and stored by the BitCoin
     * reference implementation in its wallet.
     */
    public static ECKey fromASN1(byte[] asn1privkey) {
        return ECKey.fromASN1(asn1privkey);
    }
    
    /**
     * Output this ECKey as an ASN.1 encoded private key, as understood by OpenSSL or used by the BitCoin reference
     * implementation in its wallet storage format.
     */
    public byte[] toASN1(){
    	return ecKey.toASN1();
    }

}

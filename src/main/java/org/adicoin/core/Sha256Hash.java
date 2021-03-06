package org.adicoin.core;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;

import org.bouncycastle.util.encoders.Hex;

/**
 * SHA256 Hash bytes encapsulated with equals and hashCode methods implemented.
 * @author aditya
 *
 */
public class Sha256Hash implements Serializable {

	private static final long serialVersionUID = 6174903623608575112L;
	
    private byte[] bytes;

    public Sha256Hash(byte[] bytes) {
        assert bytes.length == 32;
        this.bytes = bytes;
    }

    public Sha256Hash(String string) {
        assert string.length() == 64;
        this.bytes = Hex.decode(string);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Sha256Hash)) return false;
        return Arrays.equals(bytes, ((Sha256Hash)other).bytes);
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    public String toString() {
        return Utils.bytesToHexString(bytes);
    }

    /** Returns the bytes interpreted as a positive integer. */
    public BigInteger toBigInteger() {
        return new BigInteger(1, bytes);
    }

    public byte[] getBytes() {
        return bytes;
    }


}

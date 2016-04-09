package org.adicoin.core;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;


public class Utils {
	
    public static byte[] hash160(byte[] input) {
        RIPEMD160Digest digest = new RIPEMD160Digest();
		digest.update(input, 0, input.length);
		byte[] out = new byte[20];
		digest.doFinal(out, 0);
		return out;
    }
    
    
    public static byte[] hashSHA256(byte[] input) {
        try {
            byte[] out = MessageDigest.getInstance("SHA-256").digest(input);
            return out;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static byte[] sha256hash160(byte[] input) {
        byte[] sha256 = hashSHA256(input);
		RIPEMD160Digest digest = new RIPEMD160Digest();
		digest.update(sha256, 0, sha256.length);
		byte[] out = new byte[20];
		digest.doFinal(out, 0);
		return out;
    }
    
    public static byte[] doubleSHA256Digest(byte[] input) {
        return doubleSHA256Digest(input, 0, input.length);
    }
    
    public static byte[] doubleSHA256Digest(byte[] input, int offset, int length) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(input, offset, length);
            byte[] first = digest.digest();
            return digest.digest(first);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);  // Cannot happen.
        }
    }

	public static byte[] doubleSHA256DigestTwoBuffers(byte[] input1, int offset1, int length1, byte[] input2, int offset2,
			int length2) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(input1, offset1, length1);
			digest.update(input2, offset2, length2);
			byte[] first = digest.digest();
			return digest.digest(first);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e); // Cannot happen.
		}
	}
    
    public static String bytesToHexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (byte b : bytes) {
            String s = Integer.toString(0xFF & b, 16);
            if (s.length() < 2)
                buf.append('0');
            buf.append(s);
        }
        return buf.toString();
    }
    
    public static byte[] reverseBytes(byte[] bytes) {
        byte[] buf = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++)
            buf[i] = bytes[bytes.length - 1 - i];
        return buf;
    }
    
    /* Methods to read Unsigned Integer 32 bytes in Little Endian (Default) and Big Endian */
    public static long readUint32(byte[] bytes, int offset) {
        return ((bytes[offset++] & 0xFFL) <<  0) |
               ((bytes[offset++] & 0xFFL) <<  8) |
               ((bytes[offset++] & 0xFFL) << 16) |
               ((bytes[offset] & 0xFFL) << 24);
    }
    
    public static long readUint32BE(byte[] bytes, int offset) {
        return ((bytes[offset + 0] & 0xFFL) << 24) |
               ((bytes[offset + 1] & 0xFFL) << 16) |
               ((bytes[offset + 2] & 0xFFL) <<  8) |
               ((bytes[offset + 3] & 0xFFL) <<  0);
    }
    
    
    public static void uint32ToByteStreamLE(long val, OutputStream stream) throws IOException {
        stream.write((int)(0xFF & (val >>  0)));
        stream.write((int)(0xFF & (val >>  8)));
        stream.write((int)(0xFF & (val >> 16)));
        stream.write((int)(0xFF & (val >> 24)));
    }

}

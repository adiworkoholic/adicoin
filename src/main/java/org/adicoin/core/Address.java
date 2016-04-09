package org.adicoin.core;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author aditya
 *
 */
/**
 * @author aditya
 *
 */
public class Address {
	private byte[] bytes;

	public Address(byte[] bytes) {
		this.bytes = bytes;
	}
	
	public Address(String address) throws AddressFormatException {
		this.bytes = checkAndDecode(address);
	}

	private byte[] checkAndDecode(String address) throws AddressFormatException {
		byte[] encoded = Base64.decodeBase64(address);
		byte[] checksum = new byte[4];
		System.arraycopy(encoded, 20, checksum, 0, 4);
		byte[] addressBytes = new byte[20];
		System.arraycopy(encoded, 0, addressBytes, 0, 20);
		byte[] calculatedHash = Utils.doubleSHA256Digest(addressBytes, 0, 20);
		
		if (!Arrays.equals(calculatedHash, checksum))
            throw new AddressFormatException("Checksum does not validate");
		
		return addressBytes;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(this.bytes);
	}

	@Override
	public boolean equals(Object obj) {
        if (!(obj instanceof Address)) return false;
        Address addr =(Address)obj;
        return Arrays.equals(addr.bytes, this.bytes);
	}
	
	
	public byte[] getHash160() {
		return bytes;
	}

	@Override
	public String toString() {
		int addressBytesLength = 20;
		int checksumBytesLength = 4;
		byte[] addressBytes = new byte[addressBytesLength + checksumBytesLength ];
		System.arraycopy(this.bytes, 0, addressBytes, 0, 20);
		byte[] checksum = Utils.doubleSHA256Digest(addressBytes, 0, 20);
		System.arraycopy(checksum, 0, addressBytes, 20, 4);

		return Base64.encodeBase64String(addressBytes);
	}

}

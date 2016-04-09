package org.adicoin.core;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;

/**
 * @author aditya
 *
 */
public class Address {
    private int version;
	private byte[] bytes;

	public Address(AdiCoinNetworkConfig config, byte[] hash160) {
		if (hash160.length != 20)
			throw new RuntimeException("Invalid length. Must be 20 bytes");
		this.version = config.addressHeader;
		this.bytes = hash160;
	}
	
	public Address(AdiCoinNetworkConfig config, String coinAddress) throws AddressFormatException {
		this.bytes = checkAndDecode(coinAddress);
	}

	private byte[] checkAndDecode(String address) throws AddressFormatException {
		byte[] encoded = Base64.decodeBase64(address); // Using Base64 for now. To be changed to Base58 Encoding
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
	
	
	public byte[] getPubKeyHashBytes() {
		return bytes;
	}

	@Override
	public String toString() {
		int versionBytesLength = 1;
		int addressBytesLength = 20;
		int checksumBytesLength = 4;
		byte[] addressBytes = new byte[versionBytesLength + addressBytesLength + checksumBytesLength ];
        addressBytes[0] = (byte)version;

		System.arraycopy(this.bytes, 0, addressBytes, 1, 20);
		byte[] checksum = Utils.doubleSHA256Digest(addressBytes, 0, 21);
		System.arraycopy(checksum, 0, addressBytes, 21, 4);

		return Base64.encodeBase64String(addressBytes); // Using Base64 for now. To be changed to Base58 Encoding
	}

}

package org.adicoin.core;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
  	Size				Field				Description
  	----				-----				-----------
	4 bytes 			Version 			Specifies which rules this transaction follows
	1–9 bytes (VarInt)	Input Counter		How many inputs are included
	Variable			Inputs				One or more transaction inputs
	1–9 bytes (VarInt)	Output Counter		How many outputs are included
	Variable			Outputs				One or more transaction outputs
	4 bytes				Locktime			A Unix timestamp or block number

 * @author aditya
 *
 */

public class Transaction extends Message implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(Transaction.class);
	private static final long serialVersionUID = -5477545382702797408L;

	long version;
	ArrayList<TransactionInput> inputs;
	ArrayList<TransactionOutput> outputs;
    long lockTime;

    transient HashAsBinary txHash;


	
    Transaction(AdiCoinNetworkConfig params) {
        super(params);
        version = 1;
        inputs = new ArrayList<TransactionInput>();
        outputs = new ArrayList<TransactionOutput>();
    }
    
    public Transaction(AdiCoinNetworkConfig params, byte[] payload) throws ProtocolException {
        super(params, payload, 0);

    }
    
    /* Provides immutable view of the transaction inputs*/
    public List<TransactionInput> getInputs() {
        return Collections.unmodifiableList(inputs);
    }
    
    public HashAsBinary getHash() {
        if (txHash == null) {
            byte[] bits = serialize();
            txHash = new HashAsBinary(Utils.reverseBytes(Utils.doubleSHA256Digest(bits)));
        }
        return txHash;
    }

	@Override
	void parse() throws ProtocolException {
		// TODO Auto-generated method stub
		
	}

	@Override
	void serializeToStream(ByteArrayOutputStream stream) {
		// TODO Auto-generated method stub
		
	}

}


/**
 * From developer documentation
 * ---------------------------- 
 * Because a single transaction can include multiple outputs, the outpoint structure includes
 * both a TXID and an output index number to refer to specific output.
 * 
 * @author aditya
 *
 */
class TransactionOutpoint extends Message implements Serializable {
    private static final long serialVersionUID = -8579790865702290101L;

    HashAsBinary txHash;
    long outputIndex;
    
    Transaction sourceTx;
    
    
    TransactionOutpoint(AdiCoinNetworkConfig params, long outputIndex, Transaction sourceTx) throws ProtocolException {
        super(params);
        if(sourceTx != null) {
            this.sourceTx = sourceTx;
            this.txHash = sourceTx.getHash();
        } 
        this.outputIndex = outputIndex;
    }

    TransactionOutpoint(AdiCoinNetworkConfig config, byte[] payload, int offset) throws ProtocolException {
        super(config, payload, offset);
    }

    @Override
    void parse() throws ProtocolException {
        txHash = readHash();
        outputIndex = readUint32();
    }

    @Override
    void serializeToStream(ByteArrayOutputStream stream) throws IOException {
        stream.write(Utils.reverseBytes(txHash.getBytes()));
        Utils.uint32ToByteStreamLE(outputIndex, stream);
    }
    
    TransactionOutput getConnectedOutput() {
        if (sourceTx == null) return null;
        return sourceTx.outputs.get((int)outputIndex);
    }
    
    /* Methods to get the most important parts of the Source TX that are required to redeem */
    byte[] getConnectedPubKeyScript() {
        byte[] result = getConnectedOutput().getScriptBytes();
        return result;
    }
    
    byte[] getConnectedPubKeyHash() throws ScriptException {
        return getConnectedOutput().getScriptPubKey().getPubKeyHash();
    }
}


class TransactionInput extends Message implements Serializable {

	private static final long serialVersionUID = 5538762740537115975L;

	byte[] scriptBytes;
	transient private CoinScript scriptSig;
	Transaction parentTransaction;
	TransactionOutpoint outpoint;

	public TransactionInput(AdiCoinNetworkConfig config) {
		super(config);
	}

	@Override
	void parse() throws ProtocolException {
		// TODO Auto-generated method stub

	}

	@Override
	void serializeToStream(ByteArrayOutputStream stream) {
		// TODO Auto-generated method stub

	}
}

class TransactionOutput extends Message implements Serializable {
	private static final Logger log = LoggerFactory.getLogger(TransactionOutput.class);
	private static final long serialVersionUID = 6764754488001030219L;

	private BigInteger value;
	private byte[] scriptBytes;
	private transient CoinScript scriptPubKey;

	Transaction parentTransaction;

	private AdiCoinNetworkConfig config;

	TransactionOutput(AdiCoinNetworkConfig config, byte[] msg, int offset) throws ProtocolException {
		super(config, msg, offset);
	}

	TransactionOutput(AdiCoinNetworkConfig config) {
		super(config);
	}

	@Override
	void parse() throws ProtocolException {

	}

	@Override
	void serializeToStream(ByteArrayOutputStream stream) {
		// TODO Auto-generated method stub

	}

	public byte[] getScriptBytes() {
		return scriptBytes;
	}

	public CoinScript getScriptPubKey() throws ScriptException {
		if (scriptPubKey == null)
			scriptPubKey = new CoinScript(config, scriptBytes, 0, scriptBytes.length);
		return scriptPubKey;
	}

}

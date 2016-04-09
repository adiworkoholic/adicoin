package org.adicoin.core;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

	public TransactionOutpoint(AdiCoinNetworkConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}

	@Override
	void serializeToStream(ByteArrayOutputStream stream) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	void parse() throws ProtocolException {
		// TODO Auto-generated method stub
		
	}


}


class TransactionInput extends Message implements Serializable {
	
	private static final long serialVersionUID = 5538762740537115975L;
	public TransactionInput(AdiCoinNetworkConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}


	@Override
	void serializeToStream(ByteArrayOutputStream stream) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	void parse() throws ProtocolException {
		// TODO Auto-generated method stub
		
	}
	

}

class TransactionOutput extends Message implements Serializable {
    public TransactionOutput(AdiCoinNetworkConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}
	private static final Logger log = LoggerFactory.getLogger(TransactionOutput.class);
	private static final long serialVersionUID = 6764754488001030219L;
	@Override
	void serializeToStream(ByteArrayOutputStream stream) throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	void parse() throws ProtocolException {
		// TODO Auto-generated method stub
		
	}



}

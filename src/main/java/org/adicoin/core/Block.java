package org.adicoin.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 	02000000 ........................... Block version: 2

	b6ff0b1b1680a2862a30ca44d346d9e8
	910d334beb48ca0c0000000000000000 ... Hash of previous block's header
	9d10aa52ee949386ca9385695f04ede2
	70dda20810decd12bc9b048aaab31471 ... Merkle root

	24d95a54 ........................... Unix time: 1415239972
	30c31b18 ........................... Target: 0x1bc330 * 256**(0x18-3)
	fe9f0864 ........................... Nonce
 * @author aditya
 *
 */
public class Block extends Message {
    private static final Logger log = LoggerFactory.getLogger(Block.class);

	private static final long serialVersionUID = -2497440265918156052L;

    public static final int BLOCK_HEADER_SIZE = 80;

    private long version;
    private Sha256Hash prevBlockHash;
    private Sha256Hash merkleRoot;
    private long time;
    private long difficultyTarget;  // "nBits"

    private long nonce;

    List<Transaction> transactions; // null indicates that it currently only holders the block header
    
    private transient Sha256Hash hash; // Hash of this block
    
	public Block(AdiCoinNetworkConfig config) {
		super(config);
	}

	@Override
	void parse() throws ProtocolException {
		// TODO Auto-generated method stub
		
	}

	@Override
	void serializeToStream(ByteArrayOutputStream stream) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	private void writeHeader(OutputStream stream) {
    	throw new RuntimeException("Not Implemented Yet!");

	}
	

    /**
     * Returns the hash of the block (which for a valid, solved block should be below the target). Big endian.
     */
    public Sha256Hash getHash() {
        if (hash == null)
            hash = calculateBlockHash();
        return hash;
    }
    
    public BigInteger getWork() {
    	throw new RuntimeException("Not Implemented Yet!");
    }
    
    public Block getHeaderOnly() {
    	throw new RuntimeException("Not Implemented Yet!");
    }
    
    /* Solve the block - find nonce that makes the block hash lower than the difficultyTarget */
    public void mineMe() {
    	throw new RuntimeException("Not Implemented Yet!");
    }
	
    private boolean verifyTimeStamp() throws VerificationException {
    	throw new RuntimeException("Not Implemented Yet!");

    }
    private boolean verifyProofofWork() throws VerificationException {
    	throw new RuntimeException("Not Implemented Yet!");

    }
    private void verifyMerkleRoot() throws VerificationException {
    	throw new RuntimeException("Not Implemented Yet!");

    }
    private boolean verifyTransactions() throws VerificationException {
    	// Check that ONLY the first transaction in this block is coinbase transaction
    	throw new RuntimeException("Not Implemented Yet!");

    }

    
    public void verify() throws VerificationException {
    	verifyProofofWork();
    	verifyTimeStamp();
    	
    	if(transactions != null) {
    		verifyTransactions();
    		verifyMerkleRoot();
    	}
    }
	private Sha256Hash calculateBlockHash() {
    	throw new RuntimeException("Not Implemented Yet!");

	}
	
	private Sha256Hash calculateMerkleRoot() {
    	throw new RuntimeException("Not Implemented Yet!");
	}
	
	
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Block)) return false;
        Block other = (Block) o;
        return getHash().equals(other.getHash());
    }

    @Override
    public int hashCode() {
        return getHash().hashCode();
    }
    
    
    
    /* Getters and Setters */
    /** Returns the merkle root in big endian form, calculating it from transactions if necessary. */
    public Sha256Hash getMerkleRoot() {
        if (merkleRoot == null)
            merkleRoot = calculateMerkleRoot();
        return merkleRoot;
    }
	
    /** Returns the version of the block data structure as defined by the BitCoin protocol. */
    public long getVersion() {
        return version;
    }

    /** Returns the hash of the previous block in the chain, as defined by the block header. */
    public Sha256Hash getPrevBlockHash() {
        return prevBlockHash;
    }

    void setPrevBlockHash(Sha256Hash prevBlockHash) {
        this.prevBlockHash = prevBlockHash;
        this.hash = null;
    }

    /** Returns the time at which the block was solved and broadcast, according to the clock of the solving node. */
    public long getTime() {
        return time;
    }

    void setTime(long time) {
        this.time = time;
        this.hash = null;
    }

    /**
     * Returns the difficulty of the proof of work that this block should meet encoded in compact form. The
     * {@link BlockChain} verifies that this is not too easy by looking at the length of the chain when the block is
     * added. To find the actual value the hash should be compared against, use getDifficultyTargetBI.
     */
    public long getDifficultyTarget() {
        return difficultyTarget;
    }

    void setDifficultyTarget(long compactForm) {
        this.difficultyTarget = compactForm;
        this.hash = null;
    }

    /**
     * Returns the nonce, an arbitrary value that exists only to make the hash of the block header fall below the
     * difficulty target.
     */
    public long getNonce() {
        return nonce;
    }

    void setNonce(long nonce) {
        this.nonce = nonce;
        this.hash = null;
    }

}

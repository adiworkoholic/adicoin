package org.adicoin.core;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BlockChain {
    private static final Logger log = LoggerFactory.getLogger(BlockChain.class);

    protected PersistedBlock chainHead;
    protected AdiCoinNetworkConfig config;
    
    protected ArrayList<Block> unconnectedBlocks = new ArrayList<>();

    
    public synchronized boolean add(Block block) throws VerificationException, ScriptException {
    	// Verify Block
    	
    	// Get the Previous Block of the block being added if present in the persisted Blocks Storage
    	
    	
    	// If previous block is not available in the storage, add current block to list of unconnected blocks.
    	// 	
    	// If previous block is available in the storage and is the chainHead
    	// 	put current block in block storage and connect the current block to the blockchain i.e make it chainHead.
    	//
    	
    
		return false;
    }
    
    
    public synchronized PersistedBlock getChainHead() {
        return chainHead;
    }

}

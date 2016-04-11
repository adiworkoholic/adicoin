package org.adicoin.core;

/**
 *  Map of hashes to StoredBlock.
 *  The hash is the double digest of block header serialized
 */
public interface BlockStorage {
	
    void put(PersistedBlock block) throws BlockStoreException;
    PersistedBlock get(Sha256Hash hash) throws BlockStoreException;
    
    /* represents the top of the chain of greatest total work.*/
    PersistedBlock getChainHead() throws BlockStoreException;
    void setChainHead(PersistedBlock chainHead) throws BlockStoreException;

}

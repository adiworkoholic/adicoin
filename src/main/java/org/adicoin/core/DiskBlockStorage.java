package org.adicoin.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DiskBlockStorage implements BlockStorage {
	
	private static final Logger log = LoggerFactory.getLogger(DiskBlockStorage.class);

	private FileOutputStream stream;
	private Map<Sha256Hash, PersistedBlock> blockMap;
	private Sha256Hash chainHead;
	private AdiCoinNetworkConfig config;
	
	
    public DiskBlockStorage(AdiCoinNetworkConfig config, File file) throws BlockStoreException {
        this.config = config;
        blockMap = new HashMap<Sha256Hash, PersistedBlock>();
        try {
            load(file);
            stream = new FileOutputStream(file, true);    // Do append.
        } catch (IOException e) {
            log.error("Failed to load Blockchain from file", e);
            createNewStore(config, file);
        }
    }
    
    
    private void load(File file) throws IOException, BlockStoreException {
    	
    }

    private void createNewStore(AdiCoinNetworkConfig config, File file) throws BlockStoreException {
    	
    }

	@Override
	public synchronized void put(PersistedBlock block) throws BlockStoreException {
        try {
            Sha256Hash hash = block.getHeader().getHash();
            assert blockMap.get(hash) == null : "Attempt to insert duplicate";
            // Append to the end of the file. The other fields in StoredBlock will be recalculated when it's reloaded.
            byte[] bytes = block.getHeader().serialize();
            stream.write(bytes);
            stream.flush();
            blockMap.put(hash, block);
        } catch (IOException e) {
            throw new BlockStoreException(e);
        }
	}

	@Override
	public synchronized PersistedBlock get(Sha256Hash hash) throws BlockStoreException {
        return blockMap.get(hash);
	}

	@Override
	public synchronized PersistedBlock getChainHead() throws BlockStoreException {
        return blockMap.get(chainHead);
	}

	@Override
	public void setChainHead(PersistedBlock chainHead) throws BlockStoreException {
		 try {
	            this.chainHead = chainHead.getHeader().getHash();
	            // Write out new hash to the first 32 bytes of the file past one (first byte is version number).
	            stream.getChannel().write(ByteBuffer.wrap(this.chainHead.getBytes()), 1);
	        } catch (IOException e) {
	            throw new BlockStoreException(e);
	        }
	}

}

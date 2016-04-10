package org.adicoin.core;

import java.math.BigInteger;

public class PersistedBlock {
    private static final long serialVersionUID = -6097565241243701771L;

    private Block header;
    private BigInteger chainWork;
    private int height;
    
    public PersistedBlock(Block header, BigInteger chainWork, int height) {
        this.header = header;
        this.chainWork = chainWork;
        this.height = height;
    }
    
   public PersistedBlock build(Block block) throws VerificationException {

       BigInteger chainWork = this.chainWork.add(block.getWork());
       int height = this.height + 1;
       return new PersistedBlock(block.getHeaderOnly(), chainWork, height);
   }
   
   public Block getHeader() {
       return header;
   }
   
   public PersistedBlock getPrev(BlockStorage store) throws BlockStoreException {
       return store.get(getHeader().getPrevBlockHash());
   }
   
   
   @Override
   public boolean equals(Object other) {
       if (!(other instanceof PersistedBlock)) return false;
       PersistedBlock o = (PersistedBlock) other;
       return o.header.equals(header) && o.chainWork.equals(chainWork) && o.height == height;
   }

   @Override
   public int hashCode() {
       // A better hashCode is possible, but this works for now.
       return header.hashCode() ^ chainWork.hashCode() ^ height;
   }
}

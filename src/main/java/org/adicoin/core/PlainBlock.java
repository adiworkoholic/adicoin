package org.adicoin.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PlainBlock extends Message {

	private static final long serialVersionUID = -2497440265918156052L;

	public PlainBlock(AdiCoinNetworkConfig config) {
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

}
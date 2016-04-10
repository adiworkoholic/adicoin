package org.adicoin.core;

import java.util.Stack;

import org.bouncycastle.util.Arrays;

public class CoinScript {
	private final Stack<byte[]> stack;
	private AdiCoinNetworkConfig config;

	// As per the developer documentation (OPCODE Word and the OPCODE)

	public static final int OP_DUP = 118; // Duplicates the top stack item.

	public static final int OP_HASH160 = 169; // The input is hashed twice:
												// first with SHA-256 and then
												// with RIPEMD-160.

	public static final int OP_EQUALVERIFY = 136; // Equivalent to running
													// OP_EQUAL followed by
													// OP_VERIFY.
													// OP_EQUAL : Returns 1 if
													// the inputs are exactly
													// equal, 0 otherwise.
													// OP_VERIFY : Marks
													// transaction as invalid if
													// top stack value is not
													// true.

	public static final int OP_CHECKSIG = 172; // The signature used by
												// OP_CHECKSIG must be a valid
												// signature for
												// transaction hash and public
												// key. If it is, 1 is returned,
												// 0 otherwise.

	public CoinScript(AdiCoinNetworkConfig config, byte[] scriptBytes, int offset, int length) throws ScriptException {
		stack = new Stack<byte[]>();
		this.config = config;
		// TODO: 1) Parse the script Bytes into instructions and data. 2) Run
		// the instructions and operate on the data
	}

	static byte[] createInputScript(byte[] signature, byte[] pubkey) {
		throw new RuntimeException("Not Implemented yet!");
	}

	static byte[] createOutputScript(Address to) {
		throw new RuntimeException("Not Implemented yet!");
	}

	private void opDup() {
		stack.add(Arrays.clone(stack.lastElement()));
	}

	private void opHash160() {
		byte[] buf = stack.pop();
		byte[] hash = Utils.sha256hash160(buf);
		stack.add(hash);
	}

	private void opEqualVerify() throws ScriptException {
		byte[] a = stack.pop();
		byte[] b = stack.pop();
		if (!Arrays.areEqual(a, b))
			throw new ScriptException(
					"OP_EQUALVERIFY failed: " + Utils.bytesToHexString(a) + " vs " + Utils.bytesToHexString(b));
	}

	private void opCheckSig(Transaction txContext) throws ScriptException {
		throw new RuntimeException("Not Implemented Yet !");
	}

	public byte[] getPubKeyHash() throws ScriptException {
		throw new RuntimeException("Not Implemented Yet !");
	}

}




import java.io.*;

import java.math.BigInteger;
import java.security.SecureRandom;


public class ECPrivKey implements Serializable{
	/**
	 * The EC Domain Parameters
	 */
	public ECDomainParameters dp;

	/**
	 * The Private Key
	 */
	public BigInteger s;

	/**
	 * Generate a random private key with ECDomainParameters dp
	 */
	public ECPrivKey(ECDomainParameters dp) {
		this.dp = (ECDomainParameters) dp.clone();
		SecureRandom rnd = new SecureRandom();
		s = new BigInteger(dp.m, rnd);
		s = s.mod(dp.r);
	}

	/**
	 * Generate a private key with ECDomainParameters dp
	 * and private key s
	 */
	public ECPrivKey(ECDomainParameters dp, BigInteger s) {
		this.dp = dp;
		this.s = s;
	}

	public String toString() {
		String str = new String("dp: ").concat(dp.toString()).concat("\n");
		str = str.concat("s: ").concat(s.toString()).concat("\n");
		return str;
	}

	protected Object clone() {
		return new ECPrivKey(dp, s);
	}

}

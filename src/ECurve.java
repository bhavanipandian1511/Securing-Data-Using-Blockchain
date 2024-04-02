


import java.math.BigInteger;
import java.io.*;

public abstract class ECurve implements Serializable{
	protected Fq a4;
	protected Fq a6;

	public String toString() {
		return "a4:0x" + a4 + "\na6:0x" + a6;
	}

	public abstract ECPoint add(ECPoint P0, ECPoint P1);
	public abstract ECPoint mul(BigInteger n, ECPoint P);

}

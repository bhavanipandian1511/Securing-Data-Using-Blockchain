




import java.io.*;
public class ECPubKey implements Serializable{
	/**
	 * The EC Domain Parameters
	 */
	public ECDomainParameters dp; // the EC domain parameters for this key pair
	/**
	 * The public key
	 */
	public ECPoint W; // the public key

	/**
	 * Generate a public key from private key sk
	 */	public ECPubKey (ECPrivKey sk)
	{
		dp = (ECDomainParameters)sk.dp.clone();
		W = dp.E.mul (sk.s, dp.G);
	}

	/**
	 * Generate a public key with ECDomainParameters dp
	 * and public key W
	 */	public ECPubKey (ECDomainParameters dp, ECPointF2m W)
	{
		this.dp = (ECDomainParameters)dp.clone();
		this.W = (ECPointF2m)W.clone();
	}

	public String toString()
	{
		String str = new String("dp: ").concat(dp.toString()).concat("\n");
		str = str.concat("W: x:").concat(W.x.toString()).concat("\n");
		str = str.concat("   y:").concat(W.y.toString()).concat("\n");
		return str;

	}

	protected Object clone()
	{
		return new ECPubKey(dp, (ECPointF2m)W);
	}

}

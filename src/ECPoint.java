


import java.io.*;

public abstract class ECPoint implements Serializable{
	/**
	 * The x coordinate of the point.
	 */
	public Fq x;

	/**
	 * The x coordinate of the point.
	 */
	public Fq y;

	/**
	 * Returns true if this is the point at infinity, O = (0, 0).
	 */
	public boolean isZero() {
		return (x.isZero() & y.isZero());
	}

	public String toString() {
		return "x:0x" + x + " y:0x" + y;
	}

	/**
	 * Returns the additive inverse of this point (-P).
	 */
	public abstract ECPoint negate();

	protected abstract Object clone();

}

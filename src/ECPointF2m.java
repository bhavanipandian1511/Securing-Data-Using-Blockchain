


import java.io.*;

public class ECPointF2m extends ECPoint implements Serializable{
	/**
	 * Construct a new point P = O the point at infinity
	 */
	public ECPointF2m() {
		x = new F2m();
		y = new F2m();
	}

	/**
	 * Construct a new point P = (x, y) on the curve
	 * @param x : the x coordinate of the point
	 * @param y : the y coordinate of the point
	 */
	public ECPointF2m(F2m x, F2m y) {
		this.x = (F2m) x.clone();
		this.y = (F2m) y.clone();
	}

	/**
	 * Returns the additive inverse of this point (-P).
	 */
	public ECPoint negate() {
		return new ECPointF2m((F2m) x, (F2m) y.add(x));
	}

	protected Object clone() {
		return new ECPointF2m((F2m) x, (F2m) y);
	}
}

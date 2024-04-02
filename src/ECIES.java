


import java.io.*;


public class ECIES implements Serializable
{
	public ECPubKey V;
	public int[] C;
	public int[] T;
	public ECIES ()
	{
	}

	public ECIES (ECPrivKey u, ECPubKey W, byte[] p)
	{		int P1[] = new int[0]; // This is 0
		int P2[] = new int[0]; // This is 0
		/*int P[] = new int[3]; // Key Derivation Parameters
		for (int i=0; i<3; i++) {
			P[i] = i;
		}*/

		Fq z = ECC.ECSVDP_DH (u.dp, u.s, W.W);
		int Z[] = Utils.FE2OSP (z);
		int K[] = ECC.KDF2 (Z, 32, P1); // 256 bits
		int K1[] = new int[16]; // 128 bit symmetric encryption key
		int K2[] = new int[16]; // 128 bit MAC key
		for (int j=0; j<K1.length;j++) {
			K1[j] = K[j];
		}
		for (int k=0; k<K2.length;k++) {
			K2[k] = K[k+K1.length];
		}		V = new ECPubKey (u);
		C = ECC.AES_CBC_IV0_Encrypt (K1, Utils.toIntArray(p), 128);
		T = ECC.MAC1 (K2, Utils.concatenate(C, P2));
	}

	public ECIES (ECPubKey V, int[] C, int[] T)
	{
		this.V = V;		this.C = C;		this.T = T;	}

	public byte[] decrypt(ECPrivKey s) throws Exception
	{		int P1[] = new int[0]; // This is 0
		int P2[] = new int[0]; // This is 0
		/*int P[] = new int[3]; // Key Derivation Parameters
		for (int i=0; i<3; i++) {
			P[i] = i;
		}*/

		Fq z = ECC.ECSVDP_DH (s.dp, s.s, V.W);
		int Z[] = Utils.FE2OSP (z);
		int K[] = ECC.KDF2 (Z, 32, P1); // 256 bits
		int K1[] = new int[16]; // 128 bit symmetric encryption key
		int K2[] = new int[16]; // 128 bit MAC key
		for (int j=0; j<K1.length;j++) {
			K1[j] = K[j];
		}
		for (int k=0; k<K2.length;k++) {
			K2[k] = K[k+K1.length];
		}
		int M[] = ECC.AES_CBC_IV0_Decrypt (K1, C, 128);

		if (!Utils.compare (T, ECC.MAC1 (K2, Utils.concatenate(C, P2)))) {
			throw new Exception ("ECIES: Authentication Tag Invalid");
		}

		return Utils.toByteArray(M);
	}
	public byte[] decrypt(ECPrivKey s, ECPubKey V, int[] C, int[] T) throws Exception
	{		this.V = V;		this.C = C;		this.T = T;		return decrypt (s);
	}
	public String toString()
	{
		String str = new String("V:").concat(V.toString().concat("\n"));
		str = str.concat("C:").concat(Utils.intArrayToString(C)).concat("\n");
		str = str.concat("T:").concat(Utils.intArrayToString(T)).concat("\n");

		return str;
	}

	protected Object clone()
	{
		return new ECIES();
	}

}

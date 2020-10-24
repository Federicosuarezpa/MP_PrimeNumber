package numPrimero;

import java.math.BigInteger;
import java.util.Random;

public class BigIntegerCompare {

	long maxValue;
	BigInteger bigNumber;
	boolean itsBig;
	
	/**
	 * 
	 * @param numero
	 */
	public BigIntegerCompare(String numero)
	{
		bigNumber = new BigInteger(numero);
		itsBig = true;
		if(BigInteger.valueOf(Long.MAX_VALUE).compareTo(bigNumber) >= 0 ) 
		{
			maxValue = bigNumber.longValue();
			itsBig = false;
		}

	}
	/**
	 * 
	 * @return
	 */
	public long highestPrimeLong()
	{
		long prime = 1;
		for(long i = 1; i <= maxValue; i ++)
		{
			if(isPrime(i) && i > prime)
			{
				prime = i;
			}
		}
		return prime;
	}
	/**
	 * 
	 * @return
	 */
	public long highestPrimeLong2()
	{
		long prime = 1;
		int k = 2;
		for(long i = 1; i <= maxValue; i++) 
		{
			if(isPrime2(i,k) && i > prime)
			{
				System.out.println(i);
				prime = i;
			}
		}
		return prime;
	}
	public long highestPrimeLongInverse()
	{
		int k = 2;
		for(long i = this.maxValue; i >= 0; i-- )
		{
			if(isPrime(i))
			{
				return i;
			}
		}
		return 0;
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	boolean isPrime(long n) {
		if(n < 2) return false;

		if(n == 2 || n == 3) return true;
		
		if(n % 2 == 0) return false;

		double maxValue = Math.sqrt(n);

		for(long i = 3; i  <= maxValue; i += 1) 
		{
			if(n % i == 0) return false;
		}
		return true;
	}
	/**
	 * 
	 * @param n
	 * @param k
	 * @return
	 */

	static boolean isPrime2(long n, int k) { 

		// Corner cases 
		if (n <= 1 || n == 4) 
			return false; 
		if (n <= 3) 
			return true; 

		// Find r such that n = 2^d * r + 1  
		// for some r >= 1 
		long d = n - 1; 

		while (d % 2 == 0) 
			d /= 2; 

		// Iterate given nber of 'k' times 
		for (long i = 0; i < k; i++) 
			if (!miillerTest(d, n)) 
				return false; 

		return true; 
	} 
	
	/**
	 * 
	 * @param number
	 * @return
	 */

	public static boolean FermatTest(BigInteger n, Random r) {
		
		// Ensures that temp > 1 and temp < n.
		BigInteger temp = BigInteger.ZERO;
		do {
			temp = new BigInteger(n.bitLength()-1, r);
		} while (temp.compareTo(BigInteger.ONE) <= 0);
		
		// Just calculate temp^*(n-1) mod n
		BigInteger ans = temp.modPow(n.subtract(BigInteger.ONE), n);
		
		// Return true iff it passes the Fermat Test!
		return (ans.equals(BigInteger.ONE));
	}
	
	private static boolean MyMillerRabin(BigInteger n, Random r) {
		
		// Ensures that temp > 1 and temp < n.
		BigInteger temp = BigInteger.ZERO;
		do {
			temp = new BigInteger(n.bitLength()-1, r);
		} while (temp.compareTo(BigInteger.ONE) <= 0);
		
		// Screen out n if our random number happens to share a factor with n.
		if (!n.gcd(temp).equals(BigInteger.ONE)) return false;
		
		// For debugging, prints out the integer to test with.
		//System.out.println("Testing with " + temp);
		
		BigInteger base = n.subtract(BigInteger.ONE);
		BigInteger TWO = new BigInteger("2");
		
		// Figure out the largest power of two that divides evenly into n-1.
		int k=0;
		while ( (base.mod(TWO)).equals(BigInteger.ZERO)) {
			base = base.divide(TWO);
			k++;
		}
		
		// This is the odd value r, as described in our text.
		//System.out.println("base is " + base);
		
		BigInteger curValue = temp.modPow(base,n);
		
		// If this works out, we just say it's prime.
		if (curValue.equals(BigInteger.ONE))
			return true;
			
		// Otherwise, we will check to see if this value successively 
		// squared ever yields -1.
		for (int i=0; i<k; i++) {
			
			// We need to really check n-1 which is equivalent to -1.
			if (curValue.equals(n.subtract(BigInteger.ONE)))
				return true;
				
			// Square this previous number - here I am just doubling the 
			// exponent. A more efficient implementation would store the
			// value of the exponentiation and square it mod n.
			else
				curValue = curValue.modPow(TWO, n);
		}
		
		// If none of our tests pass, we return false. The number is 
		// definitively composite if we ever get here.
		return false;
	}
	
	public static boolean MillerRabin(BigInteger n, int numTimes) {
		
		Random r = new Random();
		
		// Run Miller-Rabin numTimes number of times.
		for (int i=0; i<numTimes; i++) 
			if (!MyMillerRabin(n,r)) return false;
			
		// If we get here, we assume n is prime. This will be incorrect with
		// a probability no greater than 1/4^numTimes.
		return true;
	}
	/**
	 * 
	 * @return
	 */
	public BigInteger highestPrimeBig()
	{
		BigInteger i = new BigInteger("1");
		for(i =  new BigInteger(this.bigNumber.toString()); i.compareTo(BigInteger.ZERO) > 0 ; i = i.subtract(BigInteger.ONE))
		{
			if(MillerRabin(i,50))
			{
				return i;
			}
		}
		return i;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param p
	 * @return
	 */
	static long power(long x, long y, long p) { 

		long res = 1; // Initialize result 


		x = x % p;  

		while (y > 0) { 

			if ((y & 1) == 1) 
				res = (res * x) % p; 

			y = y >> 1; // y = y/2 
					x = (x * x) % p; 
		} 

		return res; 
	} 

	/**
	 * 
	 * @param d
	 * @param n
	 * @return
	 */
	static boolean miillerTest(long d, long n) { 


		long a = 2 + (long)(Math.random() % (n - 4)); 

		long x = power(a, d, n); 

		if (x == 1 || x == n - 1) 
			return true; 

		while (d != n - 1) { 
			x = (x * x) % n; 
			d *= 2; 

			if (x == 1) 
				return false; 
			if (x == n - 1) 
				return true; 
		} 

		// Return composite 
		return false; 
	} 
	public long getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(long maxValue) {
		this.maxValue = maxValue;
	}
	public BigInteger getBigNumber() {
		return bigNumber;
	}
	public void setBigNumber(BigInteger bigNumber) {
		this.bigNumber = bigNumber;
	}
	public boolean isItsBig() {
		return itsBig;
	}
	public void setItsBig(boolean itsBig) {
		this.itsBig = itsBig;
	}

}

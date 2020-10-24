package numPrimero;

import java.math.BigInteger;
import java.util.ArrayList;

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
		if(BigInteger.valueOf(Long.MAX_VALUE).compareTo(bigNumber) > 0 ) 
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
		for(long i = 1; i < maxValue; i ++)
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
		for(long i = 1; i < maxValue; i++) 
		{
			if(isPrime2(i,k) && i > prime)
			{
				prime = i;
			}
		}
		return prime;
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	boolean isPrime(long n) {
		if(n < 2) return false;

		if(n == 2 || n == 3) return true;

		double maxValue = Math.sqrt(n)+1;

		for(long i = 3; i  <= maxValue; i += 2) 
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
	private static boolean isPrimeBig(BigInteger number)
	{

		for(BigInteger i = new BigInteger("2"); i.pow(2).compareTo(number) < 0 ; i = i.add(BigInteger.ONE))
		{
			if(number.mod(i).compareTo(BigInteger.ZERO) == 0)
			{
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * @return
	 */
	public BigInteger highestPrimeBig()
	{
		BigInteger i = new BigInteger(Long.toString(this.highestPrimeLong2()));
		BigInteger prime = new BigInteger(i.toString());

		for(; i.pow(2).compareTo(bigNumber) < 0 ; i.add(BigInteger.ONE))
		{
			if(isPrimeBig(i))
			{
				prime = i;
			}
			i = i.nextProbablePrime();
		}
		return prime;
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

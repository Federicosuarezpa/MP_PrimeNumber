package numPrimero;

import java.math.BigInteger;

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
			System.out.println(i);
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
	private static boolean isPrimeBig(BigInteger number)
	{

		System.out.println("Hola");
		BigInteger pruebas = new BigInteger("3");
		if(number.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0) return false;
		if(number.mod(pruebas).compareTo(BigInteger.ZERO) == 0) return false;
		pruebas = pruebas.add(BigInteger.TWO);
		if(number.mod(pruebas).compareTo(BigInteger.ZERO) == 0) return false;
		
		for(BigInteger i = new BigInteger("6"); i.pow(2).compareTo(number) < 0 ; i = i.add(BigInteger.ONE))
		{
			System.out.println(i);
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
		BigInteger i = new BigInteger("1");
		for(i =  new BigInteger(this.bigNumber.toString()); i.compareTo(BigInteger.ZERO) > 0 ; i = i.subtract(BigInteger.ONE))
		{
			if(isPrimeBig(i))
			{
				return i;
			}
			System.out.println(i+"a");
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

package numPrimero;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class main {

	static Scanner input=new Scanner(System.in);
	private static String ficheroProductos = "1234.txt";
	static final int numLineas = 10;
	
	public static String[] leerArchivo(int numLineas) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(ficheroProductos));
		String[] numLista;		

		if(numLineas > 0 ) 
		{
			numLista = new String[numLineas];
		}
		else
		{
			numLineas = (int) br.lines().count();
			numLista = new String [numLineas];
		}
		br.close();
		br = new BufferedReader(new FileReader(ficheroProductos));
		for(int i = 0; i < numLineas; i++)
		{
			numLista[i] = br.readLine();
		}
		for(int i = 0; i < numLineas; i++)
		{
			System.out.println(numLista[i]);
		}
		br.close();
		return numLista;
	}
	public static void main(String[] args) throws IOException 
	{
		
		String[] numeros = new String[numLineas];
		
		numeros = leerArchivo(numLineas);
		
		BigInteger a = null;
		
		a = (new BigInteger(numeros[0]));
		System.out.println(Long.MAX_VALUE);
		
		BigIntegerCompare x = new BigIntegerCompare("21");
		if(x.isItsBig())
		{
			System.out.println(x.highestPrimeBig());
		}
		else
		{
			System.out.println(x.highestPrimeLong2());
		}
	}

}

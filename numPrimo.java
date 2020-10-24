package numPrimero;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class numPrimo {

	static Scanner input=new Scanner(System.in);
	private static String ficheroProductos = "pruebas.txt";
	static final int numLineas = 0;

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

		br.close();
		return numLista;
	}
	public static void main(String[] args) throws IOException 
	{

		String[] numeros = new String[numLineas];
		long inicio,fin;
		double tiempo;

		numeros = leerArchivo(numLineas);

		BigInteger numero = null;

		BigIntegerCompare maxValue;
		
		
		FileWriter myFile = new FileWriter("Resultados.txt");
		
		myFile.write("N \tprimer <= N\t tiempo");
		myFile.write("\n");


		for(int i = 0; i < numeros.length; i++)
		{

			tiempo = 0;

			numero = new BigInteger(numeros[i]);

			maxValue = new BigIntegerCompare(numero.toString());

			inicio = System.currentTimeMillis();

			if(maxValue.isItsBig())
			{
				//myFile.write(Long.toString(maxValue.getMaxValue())+"\t");
				//myFile.write(Long.toString(maxValue.highestPrimeLongInverse())+"\t");
				myFile.write(maxValue.getBigNumber().toString() + "\t");
				myFile.write(maxValue.highestPrimeBig().toString()+ "\t");
			}
			else
			{
				myFile.write(Long.toString(maxValue.getMaxValue())+"\t");
				myFile.write(Long.toString(maxValue.highestPrimeLongInverse())+"\t");
			}
			fin = System.currentTimeMillis();

			tiempo = (double) ((fin - inicio))/1000;
			
			myFile.write(Double.toString(tiempo)+" segundos");
			myFile.write("\n");
		}
		myFile.close();
		
	}

}

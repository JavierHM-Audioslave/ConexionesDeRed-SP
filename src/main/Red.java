package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Red {
	
	private Grafo conexiones; //Grafo que es el mapa de conexiones de red. //
	
	public Red()
	{
		conexiones = new Grafo(30, 0);
		conexiones.cargarGrafoSinConexiones();
	}
	
	public void resolver(File entrada)
	{
		Scanner sc;
		String[] palabraLeida;
		Integer seg, ter; //Las uso para el segundo y tercer elemento de palabraLeida. //
		BFS bfs;
		ArrayList<Integer> dev;
		ArrayList<Character> salida = new ArrayList<>();
		try
		{
			sc = new Scanner(entrada);
			palabraLeida = sc.nextLine().split(" ");
			
			while(!palabraLeida[0].equals("F"))
			{
				if(palabraLeida[0].equals("P"))
				{
					bfs = new BFS(conexiones, (Integer.parseInt(palabraLeida[1])));
					bfs.resolver();
					dev = bfs.getRecorridos();
					if(dev.contains((Integer.parseInt(palabraLeida[2])-1)))
					{
						salida.add('S');
					}
					else
					{
						salida.add('N');
					}
				}
				else
				{
					conexiones.setValorCelda((Integer.parseInt(palabraLeida[1])-1), (Integer.parseInt(palabraLeida[2])-1), 1);
					conexiones.setValorCelda((Integer.parseInt(palabraLeida[2])-1), (Integer.parseInt(palabraLeida[1])-1), 1);
				}
				
				palabraLeida = sc.nextLine().split(" ");
			}
			
			try
			{
				sc.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
			
			imprimirSalida(salida);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void imprimirSalida(ArrayList<Character> salida)
	{
		FileWriter fw;
		PrintWriter pw;
		try
		{
			fw = new FileWriter(new File("out1.txt"));
			pw = new PrintWriter(fw);
			
			for(Character i: salida)
			{
				System.out.println(i);
				pw.println(i);
			}
			
			try
			{
				pw.close();
				fw.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

}

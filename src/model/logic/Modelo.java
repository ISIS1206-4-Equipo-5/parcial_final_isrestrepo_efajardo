package model.logic;

import java.io.FileReader;
import java.time.LocalDate;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import com.opencsv.CSVReader;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;

import model.data_structures.AcumPuntos;
import model.data_structures.BST;
import model.data_structures.DiaTaxi;
import model.data_structures.Digraph;
import model.data_structures.DijkstraSP;
import model.data_structures.Edge;
import model.data_structures.OrdenarNumServicios;
import model.data_structures.OrdenarNumTaxis;
import model.data_structures.Queue;
import model.data_structures.Quick;
import model.data_structures.RBT;
import model.data_structures.Stack;
import model.data_structures.TSCCompania;
import model.data_structures.TSCPuntos;
import model.data_structures.TablaSeparateChaining;
import view.View;
import java.util.regex.Pattern;
import model.data_structures.Quick;

/**
 * Definicion del modelo del mundo
 * @param <T>
 */

public class Modelo<T> {

	private View vista= new View();
	final static String TAXI_SMALL = "data/taxi-trips-wrvz-psew-subset-small.csv";
	
	private RBT rojoNegro;
	public Digraph[] grafos;
	public TSCCompania tablaCompanias;
	
	int n = 0;
	int f = 0;

	public Modelo()
	{
		rojoNegro = new RBT();
		grafos = new Digraph[96];
		for (int i = 0; i < grafos.length; i++) {
			grafos[i] = new Digraph(78); 
		}
		tablaCompanias = new TSCCompania();
	}

	public void cargarTablaCompanias(int files) throws Exception
	{

		try {
			
			Stopwatch timer = new Stopwatch();
			String datos = TAXI_SMALL;
			
			CSVParser parser1 = new CSVParserBuilder().withSeparator(',').build();
			FileReader fr1 = new FileReader(datos);
			CSVReader reader1 = new CSVReaderBuilder(fr1).withCSVParser(parser1).build();

			String[] fila1 = null;

			if (datos.equals(TAXI_SMALL)) {

				while((fila1 = reader1.readNext()) != null) 
				{
					if(!fila1[0].equals("trip_id") && !fila1[14].equals("") && !fila1[1].equals("") && !fila1[5].equals("")
							&& !fila1[12].equals(""))
					{
						Compania nueva = new Compania(fila1[14], fila1[1]);

						tablaCompanias.put(fila1[14], nueva);
						f++;
					}
				}
			}

			reader1.close();

			double time = timer.elapsedTime();
			vista.printMessage("Tiempo tomado para tabla: "+ time);
		} 
		catch (Exception e) {
			throw new Exception(e.getMessage() +"pifeo");
		}
	}

	public void cargarRBT2(int files) throws Exception
	{
		try {
			
			Stopwatch timer = new Stopwatch();
			String datos  = TAXI_SMALL;

			CSVParser parser1 = new CSVParserBuilder().withSeparator(',').build();
			FileReader fr1 = new FileReader(datos);
			CSVReader reader1 = new CSVReaderBuilder(fr1).withCSVParser(parser1).build();

			String[] fila1 = null;

			if (datos.equals(TAXI_SMALL)) {

				while((fila1 = reader1.readNext()) != null) 
				{

					if(! fila1[0].equals("trip_id") && !fila1[2].equals("") && !fila1[1].equals("") && !fila1[5].equals("")
							&& !fila1[12].equals(""))
					{
						String numFecha = fila1[2].substring(0, 10);
						LocalDate fecha = LocalDate.parse(numFecha);
						String idTaxi = fila1[1];
						double millas = Double.parseDouble(fila1[5]);
						double dinero = Double.parseDouble(fila1[12]);

						if(millas != 0.0 && dinero != 0.0)
						{
							DiaTaxi taxi = new DiaTaxi(idTaxi, millas, dinero);
							rojoNegro.put(fecha, idTaxi, taxi);
							f++;
						}

					}
				}
			}

			reader1.close();

			double time = timer.elapsedTime();
			vista.printMessage("Tiempo tomado para RBT: "+ time);
		} 
		catch (Exception e) {
			throw new Exception(e.getMessage() +"pifeo");
		}
	}

	/**
	 * @param inicioR
	 * @param finR
	 * @param from
	 * @param to
	 * Rangos
	 * 00:00 = 0
	 * 00:15 = 1
	 * 00:30 = 2
	 * ...
	 * 23:45 = 95
	 */
	
	public void cargarGrafos(int files) throws Exception
	{
		try {
			
			Stopwatch timer = new Stopwatch();
			String datos = TAXI_SMALL;

			CSVParser parser1 = new CSVParserBuilder().withSeparator(',').build();
			FileReader fr1 = new FileReader(datos);
			CSVReader reader1 = new CSVReaderBuilder(fr1).withCSVParser(parser1).build();

			String[] fila1 = null;
			int n = 0;
			LocalTime hora = LocalTime.of(00, 00);
			LocalTime enCeros = LocalTime.of(00,00);

			if(datos.equals(TAXI_SMALL))
			{
				while((fila1 = reader1.readNext()) != null) 
				{
					if(! fila1[0].equals("trip_id") && !fila1[19].equals("") && !fila1[7].equals("") && !fila1[4].equals("")
							&& !fila1[19].equals(fila1[7])) // quita self loops
					{	
						DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
						String time = fila1[2].substring(11);
						LocalTime time1 = LocalTime.parse(time, dateTimeFormatter);

						int pick = (int) Double.parseDouble(fila1[19]);
						int drop = (int) Double.parseDouble(fila1[7]);
						double secs = Double.parseDouble(fila1[4]);

						Edge arco = new Edge(pick, drop, secs, time1);

						if(time1.equals(hora))
							grafos[n].addEdge(arco);

						else
						{
							hora = hora.plusMinutes(15);
							n++;
							if(hora.equals(enCeros))
								n = 0;
							grafos[n].addEdge(arco);

						}
					}
				}
			}

			reader1.close();

			double time = timer.elapsedTime();
			vista.printMessage("Tiempo tomado para grafo: "+ time);
		} 
		catch (Exception e) {
			throw new Exception(e.getMessage() +"pifeo");
		}
	}

	public int darNumTotalTaxis()
	{
		return tablaCompanias.totalTaxis();
	}

	public int numCompanias()
	{
		return tablaCompanias.keySet().size();
	}
	////////////////////////////////////////////////////////////////////////
	
	// .............................. REQUERIMIENTOS .......................
	
	////////////////////////////////////////////////////////////////////////
	
	


}
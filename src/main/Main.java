package main;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import controller.Controller;
import model.data_structures.Bag;
import model.data_structures.Edge;
import model.logic.*;

public class Main {
	
	
	
	
	public static void main(String[] args) throws Exception 
	{
		Controller controler = new Controller();
		controler.run();
		
//		Modelo model = new Modelo();
//		model.cargarRBT2(1);
//		model.cargarGrafos(1);
//		System.out.println("altura: "+ model.darAlturaRBT());
//		System.out.println("num llaves: "+ model.darNumKeysRBT());
//		System.out.println("max: "+ model.darMaxRBT());
//		System.out.println("min: "+  model.darMinRBT());
//		model.valueSetTablaCero();
//		LocalDate init = LocalDate.of(2019, 7, 22);
//		LocalDate end = LocalDate.of(2019, 10, 31);
		
//		model.diasTaxiEntre(init, end, 10);
//		model.topNPuntos(end, 10);
		
		
		
//		model.cargarGrafos();
//		model.parteC(90, 95, 33, 56);
//		
//		model.parteC(90, 95, 33, 56);
		
//		model.cargarTablaCompanias(1);
		
//		System.out.println(model.topM(3));
//		System.out.println(model.topN(3));
		
//		Bag bolsa =  (Bag) model.grafos[0].edges();
//		Iterator iter = bolsa.iterator();
//		
//		while (iter.hasNext())
//		{
//			System.out.println(iter.next());
//		}
//		
//		System.out.println(bolsa.size());
//		
//		LocalTime hora = LocalTime.of(23, 45);
//		System.out.println(hora);
//		hora = hora.plusMinutes(15);
//		System.out.println(hora);
		
		
		
		
		// FALTA: verificar que las tablas adentro del arbol si se esten llenando solo con los taxis,
		// y que el numero de servicios si este incrementando cada vez que se repite el taxi en la tabla.
		
	}
}

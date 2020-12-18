package controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import model.data_structures.Edge;
import model.logic.Modelo;
import view.View;

public class Controller {

	private Modelo modelo;
	private View view;

	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() throws Exception 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato,dato2,dato3 = null;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			
			case 1:
				view.printMessage("Cargando---------");
				modelo.cargarTablaCompanias(1);
				modelo.cargarGrafos(1);
				modelo.cargarRBT2(1);
				view.printMessage("\n---------\n");
				break;
				
			case 2:
				view.printMessage("Viajes por compañia");
				ArrayList<String> comp = modelo.viajesPorCompania();
				for (Iterator<String> iterator = comp.iterator(); iterator.hasNext();) {
					String actual = (String) iterator.next();
					view.printMessage(actual);
				}
				
				view.printMessage("Viajes por Area");
				ArrayList<String> comp2 = modelo.viajesPorArea();
				for (Iterator iterator = comp2.iterator(); iterator.hasNext();) {
					String actual2 = (String) iterator.next();
					view.printMessage(actual2);
				}
				break;
				
			case 3: 
				view.printMessage("Deme el origen del viaje: ");
				dato = lector.next();
				view.printMessage("Deme el destino del viaje: ");
				dato2 = lector.next();
				view.printMessage("Deme el tiempo de inicio del viaje: ");
				dato3 = lector.next();
				
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
				LocalTime time1 = LocalTime.parse(dato3, dateTimeFormatter);
				
				Iterator<Edge> res = modelo.caminoMasCorto(Integer.valueOf(dato), Integer.valueOf(dato2), time1).iterator();
				
				view.printMessage("Camino mas corto al destino: ");
				while (res.hasNext()) {
					Edge edge = (Edge) res.next();
					view.printMessage(edge.toString());
				}
				break;
				
			case 4: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	
				
			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
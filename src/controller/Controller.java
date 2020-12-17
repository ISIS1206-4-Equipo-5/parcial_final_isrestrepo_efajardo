package controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Scanner;


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
		String dato = null;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			
			case 1:
				view.printMessage("---------");
				modelo.cargarTablaCompanias(1);
				modelo.cargarGrafos(1);
				modelo.cargarRBT2(1);
				view.printMessage("\n---------\n");
				break;
				
			case 2:

				break;
				
			case 3: 

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
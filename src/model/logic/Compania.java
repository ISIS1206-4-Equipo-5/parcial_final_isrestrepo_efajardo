package model.logic;

import java.util.ArrayList;

public class Compania {
	
	String nombre;
	int numServicios;
	public ArrayList idsTaxisAfiliados;
	
	public Compania(String nombre, String idTaxi)
	{
		this.nombre = nombre;
		numServicios = 1;
		idsTaxisAfiliados = new ArrayList<>();
		idsTaxisAfiliados.add(idTaxi);
	}
	
	public void agregarTaxi(String idTaxi)
	{
		idsTaxisAfiliados.add(idTaxi);
	}

	public void incNumServicios()
	{
		numServicios++;
	}
	
	public String darNombre() {
		return nombre;
	}
	
	public int darNumServicios() {
		return numServicios;
	}
	
	public int darNumTaxis() {
		return idsTaxisAfiliados.size();
	}
	
	public int compareNumTaxis(Compania x) {
		if (darNumTaxis() < x.darNumTaxis()) return -1;
		else if (darNumTaxis() > x.darNumTaxis()) return 1;
		else return 0;
	}
	
	public int compareNumServicios(Compania x) {
		if (darNumServicios() < x.darNumServicios()) return -1;
		else if (darNumServicios() > x.darNumServicios()) return 1;
		else return 0;
	}
	
}

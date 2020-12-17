package model.data_structures;

public class AcumPuntos implements Comparable<AcumPuntos> {
	
	public double puntos;
	public String idTaxi;
	
	public AcumPuntos(String idTaxi, double puntosIniciales)
	{
		puntos = puntosIniciales;
		this.idTaxi = idTaxi;
	}
	
	public void sumarPuntos(double aSumar)
	{
		puntos = puntos + aSumar;
	}

	@Override
	public int compareTo(AcumPuntos o) {
		int rta = 0;
		if(puntos < o.puntos)
			rta = -1;
		else if(puntos> o.puntos)
			rta = 1;
		return rta;
	}
}

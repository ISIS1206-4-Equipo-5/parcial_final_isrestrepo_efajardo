package model.data_structures;

public class DiaTaxi implements Comparable<DiaTaxi> {
	
	public double millas, dinero;
	public int servicios;
	public String id;
	
	public DiaTaxi (String id, double millas, double dinero)
	{
		this.millas = millas;
		this.dinero = dinero;
		this.id = id;
		servicios = 1;
	}
	
	public double calcularPuntos()
	{
		return (millas/dinero) * servicios;	
	}
	
	public void sumarMillas(double aSumar)
	{
		millas = millas + aSumar;
	}
	
	public void sumarDinero(double aSumar)
	{
		dinero = dinero + aSumar;
	}
	
	public void incrServicios()
	{
		servicios++;
	}

	@Override
	public int compareTo(DiaTaxi o) {
		int rta = 0;
		if(calcularPuntos() < o.calcularPuntos())
			rta = -1;
		else if(calcularPuntos()> o.calcularPuntos())
			rta = 1;
		return rta;
	}

}

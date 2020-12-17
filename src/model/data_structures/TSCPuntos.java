package model.data_structures;

import java.util.ArrayList;

import model.data_structures.SSPuntos.NodeSS;

/**
 * @author 
 *
 */
public class TSCPuntos {

	private int N, M,T;
	
	private SSPuntos [] st;

	/**
	 * 
	 */
	public TSCPuntos() {
		this(997);
	}

	public TSCPuntos (int M)
	{
		this.M = M;
		st = (SSPuntos[]) new SSPuntos[M];
		for (int i = 0; i < M; i++)
			st[i] = new SSPuntos(); 
	}

	public int hash(String key)
	{ return (key.hashCode() & 0x7fffffff) % M; }


	// resize the hash table to have the given number of chains,
	// rehashing all of the keys
	private void resize(int chains) {
		System.out.println("entra al resize, M inicial: " + M+ " ,N in: "+ N + " T en: "+T);
		System.out.println("resize a: "+chains);
		TSCPuntos temp = new TSCPuntos(chains);
		for (int i = 0; i < M; i++) {
//			for (K key : st[i].keys()) {
//				temp.put(key, st[i].get(key));
//			}
			NodeSS inicio = st[i].first;
			for (NodeSS x = inicio; x != null; x = x.next) {
				
				temp.put((String)x.IDTaxi, (AcumPuntos) x.acumulador);
			}
		}
		this.M  = temp.M;
		this.N  = temp.N;
		this.st = temp.st;
	}

	public void put(String key, AcumPuntos val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");

		if (N/M >= 5.0) resize(2*M); 

		int i = hash(key);
		if (!st[i].contains(key)) N++;
		st[i].put(key, val);
		T++;
		
//		System.out.println("en "+ i +" se introduce llave " + key);
//		System.out.println(size());

	}

	public AcumPuntos get(String key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		int i = hash(key);
		return st[i].get(key);
	}
	
	// retorna -1 si la llave no se encuentra
	public AcumPuntos remove(String key) {
		AcumPuntos rta = null;

		if (key == null) throw new IllegalArgumentException("argument to delete() is null");

		int i = hash(key);
		if (st[i].contains(key)) N--;
		rta = st[i].get(key);
		st[i].delete(key);

		if (N/M <= 2) resize(M/2);

		return rta;
	}


	public boolean contains(String key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}


	public boolean isEmpty() {
		return size() == 0;
	}

	
	public int size() {
		return N;
	}
	
	/**
	 * @return the m
	 */
	public int getM() {
		return M;
	}
	
	
	public int totalElem()
	{
		return T;
	}

	/**
	 * 
	 * @return Arreglo con todas las llaves de la tabla
	 */
	public ArrayList keySet() {
		ArrayList arreglo = new ArrayList();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < st[i].keys().size(); j++) {
				String temp = (String) st[i].keys().get(j);
				arreglo.add(temp);
			}		
		}
		return arreglo;
	} 
	
	/**
	 * 
	 * @return Arreglo con todos los valores de la tabla
	 */
	public ArrayList valueSet() {
		ArrayList arreglo = new ArrayList();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < st[i].values().size(); j++) {
				AcumPuntos temp = (AcumPuntos) st[i].values().get(j);
				arreglo.add(temp);
			}
		}
		return arreglo;
	} 
}
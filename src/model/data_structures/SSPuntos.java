
package model.data_structures;

import java.util.ArrayList;

/**
 * @author 
 *
 */
public class SSPuntos 
{// first node in the linked list
	public class NodeSS
	{ // linked-list node
		String IDTaxi;
		AcumPuntos acumulador;
		NodeSS next;
		int S;
		public NodeSS(String key, AcumPuntos taxi, NodeSS next)
		{
			this.IDTaxi = key;
			this.acumulador = taxi;
			this.next = next;
		}

		/**
		 * @return the val
		 */
		public AcumPuntos getVal() {
			return acumulador;
		}
	}

	public SSPuntos() {
	}

	private int n;           // number of key-value pairs
	public NodeSS first;      // the linked list of key-value pairs

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	
	public boolean contains(String key) {
		return get(key) != null;
	}

	// si no encuentra la llave buscada, retorna null
	public AcumPuntos get(String key)
	{ // Search for key, return associated value.
		for (NodeSS x = first; x != null; x = x.next)
			if (key.equals(x.IDTaxi))
			{
				return x.acumulador;
			}
		// search hit
		return null; // search miss
	}

	public void delete(String key) {
		first = delete(first, key);
	}

	///// si quiero eliminar una Key, se eliminarian todos los datos relacionados a la Key?

	private NodeSS delete(NodeSS x, String key) {
		if (x == null) return null;
		if (key.equals(x.IDTaxi)) {
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}

	public void put(String key, AcumPuntos val)
	{ 
		// Cuando tiene la misma llave se suman los valores, no se eliminan ni se generan nodos adyacentes
		for (NodeSS x = first; x != null; x = x.next)
			if (key.equals(x.IDTaxi))
			{
//				System.out.println("antes: "+x.acumulador.puntos);
				x.acumulador.sumarPuntos(val.puntos);// se suman los puntos de las llaves repetidas
//				System.out.println("despues: "+ x.acumulador.puntos);
				return;
			}
		// si no encuentra una key igual
		first = new NodeSS(key, val, first); // si no encuentra pone el diaTaxi como valor
		n++;// Search miss: add new node.
	}

	public ArrayList keys()  {
		ArrayList arreglo = new ArrayList<>();
		for (NodeSS x = first; x != null; x = x.next)
			arreglo.add((String) x.IDTaxi);
		return arreglo;
	}

	public ArrayList values()  {
		ArrayList arreglo = new ArrayList<>();
		for (NodeSS x = first; x != null; x = x.next)
		{
			arreglo.add(x.acumulador);
		}

		return arreglo;
	}
}
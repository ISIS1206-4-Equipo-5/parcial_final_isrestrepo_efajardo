
package model.data_structures;

import java.util.ArrayList;

import model.logic.Compania;

/**
 * @author 
 *
 */
public class SSCompanias 
{// first node in the linked list
	public class NodeSS
	{ // linked-list node
		String key;
		Compania comp;
		NodeSS next;
		int S;
		public NodeSS(String key, Compania comp, NodeSS next)
		{
			this.key = key;
			this.comp = comp;
			this.next = next;
		}

		/**
		 * @return the val
		 */
		public Compania getVal() {
			return comp;
		}
	}

	public SSCompanias() {
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
	public Compania get(String key)
	{ // Search for key, return associated value.
		for (NodeSS x = first; x != null; x = x.next)
			if (key.equals(x.key))
			{
				return x.comp;
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
		if (key.equals(x.key)) {
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}

	public void put(String key, Compania val)
	{ 
		// Cuando tiene la misma llave se suman los valores, no se eliminan ni se generan nodos adyacentes
		for (NodeSS x = first; x != null; x = x.next)
			if (key.equals(x.key))
			{
				x.comp.incNumServicios(); // incrementa los servicios
				
				String idTaxi = (String) val.idsTaxisAfiliados.get(0);
				if(!x.comp.idsTaxisAfiliados.contains(idTaxi))
				{
					x.comp.agregarTaxi(idTaxi);
				}

				return;
			}
		// si no encuentra una key igual
		first = new NodeSS(key, val, first); // si no encuentra pone el diaTaxi como valor
		n++;// Search miss: add new node.
	}

	public ArrayList keys()  {
		ArrayList arreglo = new ArrayList<>();
		for (NodeSS x = first; x != null; x = x.next)
			arreglo.add((String) x.key);
		return arreglo;
	}

	public ArrayList values()  {
		ArrayList arreglo = new ArrayList<>();
		for (NodeSS x = first; x != null; x = x.next)
		{
			arreglo.add(x.comp);
		}

		return arreglo;
	}
	
	public int numTaxis()  {
		int num = 0;
		for (NodeSS x = first; x != null; x = x.next)
		{
			num = num + x.comp.idsTaxisAfiliados.size();
		}

		return num;
	}
	
	public Compania getCompania() {
		Compania compania = null;
		boolean termine = false;
		for (NodeSS x = first; x != null && !termine; x = x.next)
		{
			compania = x.comp;
			termine = true;
		}
		return compania;
	}
}
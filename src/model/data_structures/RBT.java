/**
 * 
 */
package model.data_structures;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 
 *
 */
public class RBT{


	private static final boolean RED   = true;
	private static final boolean BLACK = false;

	private NodeRBT root;     // root of the BST

	// BST helper node data type
	private class NodeRBT {
		private LocalDate fecha;           // key
		private TablaSeparateChaining tabla;          // associated data
		private NodeRBT left, right;  // links to left and right subtrees
		private boolean color;     // color of parent link
		private int size;          // subtree count

		public NodeRBT(LocalDate fecha,TablaSeparateChaining tabla, boolean color, int size) {
			this.fecha = fecha;
			this.tabla = tabla;
			this.color = color;
			this.size = size;
		}
	}

	/**
	 * Initializes an empty symbol table.
	 */
	public RBT() {
	}


	// is node x red; false if x is null ?
	private boolean isRed(NodeRBT x) {
		if (x == null) return false;
		return x.color == RED;
	}

	// number of node in subtree rooted at x; 0 if x is null
	private int size(NodeRBT x) {
		if (x == null) return 0;
		return x.size;
	} 


	
	public int size() {
		return size(root);
	}

	
	public boolean isEmpty() {
		return root == null;
	}

	
	public TablaSeparateChaining get(LocalDate key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		return get(root, key);
	}

	private TablaSeparateChaining get(NodeRBT x, LocalDate key) {
		if (key == null) throw new IllegalArgumentException("calls get() with a null key");
		while (x != null) {
			int cmp = key.compareTo(x.fecha);
			if      (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else              return x.tabla;
		}
		return null;
	}

	public int getHeight(LocalDate key) {
		return height(root) - height(getNode(root, key));
	}

	private NodeRBT getNode(NodeRBT x, LocalDate key) {
		if (key == null) 
			throw new IllegalArgumentException("calls get() with a null key");
		if (x == null) 
			return null;
		int cmp = key.compareTo(x.fecha);
		if      (cmp < 0) 
			return getNode(x.left, key);
		else if (cmp > 0) 
			return getNode(x.right, key);
		else              
			return x;
	}
	
	public boolean contains(LocalDate fecha) {
		return get(fecha) != null;
	}

	public void put(LocalDate fecha, String idTaxi, DiaTaxi taxi) {
		if (fecha == null) throw new IllegalArgumentException("first argument to put() is null");

		root = put(root, fecha, idTaxi, taxi);
		root.color = BLACK;
		// assert check();
	}

	// insert the key-value pair in the subtree rooted at h
	private NodeRBT put(NodeRBT h, LocalDate fecha, String idTaxi, DiaTaxi taxi) { 
		if (h == null)
		{
			TablaSeparateChaining tabla = new TablaSeparateChaining();
			tabla.put(idTaxi, taxi);
			
			return new NodeRBT(fecha, tabla, RED, 1);
		}
		//        return new NodeRBT(key, val, RED, 1);

		int cmp = fecha.compareTo(h.fecha);
		if      (cmp < 0) h.left  = put(h.left, fecha, idTaxi, taxi); 
		else if (cmp > 0) h.right = put(h.right, fecha, idTaxi, taxi); 
		else              h.tabla.put(idTaxi, taxi);

		// fix-up any right-leaning links
		if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
		if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
		h.size = size(h.left) + size(h.right) + 1;

		return h;
	}

	// make a left-leaning link lean to the right
	private NodeRBT rotateRight(NodeRBT h) {
		// assert (h != null) && isRed(h.left);
		NodeRBT x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = x.right.color;
		x.right.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	// make a right-leaning link lean to the left
	private NodeRBT rotateLeft(NodeRBT h) {
		// assert (h != null) && isRed(h.right);
		NodeRBT x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = x.left.color;
		x.left.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	// flip the colors of a node and its two children
	private void flipColors(NodeRBT h) {
		// h must have opposite color of its two children
		// assert (h != null) && (h.left != null) && (h.right != null);
		// assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
		//    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}

	private int height(NodeRBT x) {
		if (x == null) return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}

	public int height() {
		return height(root);
	}

	public LocalDate min() {
		if (isEmpty()) System.out.println("calls min() with empty symbol table");
		return min(root).fecha;
	}

	private NodeRBT min(NodeRBT x) { 
		if (x.left == null) return x; 
		else                return min(x.left); 
	}

	public LocalDate max() {
		if (isEmpty()) System.out.println("calls max() with empty symbol table");
		return max(root).fecha;
	}

	private NodeRBT max(NodeRBT x) { 
		// assert x != null;
		if (x.right == null) return x; 
		else                 return max(x.right); 
	} 

	public ArrayList keySet() {
		if (isEmpty()) return new ArrayList<>();
		return keysInRange(min(), max());
	}

	public ArrayList keysInRange(LocalDate init, LocalDate end) {
		if (init == null) throw new IllegalArgumentException("first argument to keys() is null");
		if (end == null) throw new IllegalArgumentException("second argument to keys() is null");

		ArrayList<LocalDate> arreglo = new ArrayList<LocalDate>();
		keys(root, arreglo, init, end);
		return arreglo;
	}


	private void keys(NodeRBT x, ArrayList<LocalDate> arreglo, LocalDate lo, LocalDate hi) { 
		if (x == null) return; 
		int cmplo = lo.compareTo(x.fecha); 
		int cmphi = hi.compareTo(x.fecha); 
		if (cmplo < 0) keys(x.left, arreglo, lo, hi); 
		if (cmplo <= 0 && cmphi >= 0) arreglo.add(x.fecha); 
		if (cmphi > 0) keys(x.right, arreglo, lo, hi); 
	} 

	public ArrayList valueSet() {
		return valuesInRange(min(), max());
	}
	
	public ArrayList valuesInRange(LocalDate init, LocalDate end) {
		if (init == null)
			throw new IllegalArgumentException("first argument to keys() is null");
		if (end == null) 
			throw new IllegalArgumentException("second argument to keys() is null");

		ArrayList arreglo = new ArrayList(); 
		values(root, arreglo, init, end);
		return arreglo;
	}

	private void values(NodeRBT x, ArrayList arreglo, LocalDate lo, LocalDate hi) {
		if (x == null) 
			return; 
		int cmplo = lo.compareTo(x.fecha); 
		int cmphi = hi.compareTo(x.fecha); 
		if (cmplo < 0) 
			values(x.left, arreglo, lo, hi); 
		if (cmplo <= 0 && cmphi >= 0) {
			arreglo.add(x.tabla);
		}
		if (cmphi > 0) 
			values(x.right, arreglo, lo, hi); 
	}
	
	
	// cada key (fecha) tiene un arreglo de accidentes que ocurrieron en esta, por eso el uso del metodo size.
//	public K masAccidentesEntre(K init, K end)
//	{
//		K rta = null;
//		
//		ArrayList<K> ls = (ArrayList<K>) keysInRange(init, end);
//		int m = 0;
//		for (int i = 0; i < ls.size(); i++) {
//			
//			K llave = ls.get(i);
//			
//			if(get(llave).size()>m)
//			{
//				m = get(llave).size();
//				rta = llave;
//			}
//		}
//		System.out.println("max: "+ m);
//		return rta;
//	}
	
}

/**
 * 
 */
package model.data_structures;

import java.util.ArrayList;

/**
 * @author 
 *
 */
public class BST<K extends Comparable<K>, V> {

	private Node root;             // root of BST

	private class Node {
		private K key;           // sorted by key
		private ArrayList<V> val;         // associated data
		private Node left, right;  // left and right subtrees
		private int size;          // number of nodes in subtree

		public Node(K key, ArrayList<V> val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}

	/**
	 * Initializes an empty symbol table.
	 */
	public BST() {
	}

	
	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null) return 0;
		else return x.size;
	}

	
	public boolean isEmpty() {
		return size() == 0;
	}

	
	public ArrayList<V> get(K key) {
		return get(root, key);
	}

	private ArrayList<V> get(Node x, K key) {
		if (key == null) throw new IllegalArgumentException("calls get() with a null key");
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else              return x.val;
	}

	
	public int getHeight(K key) {
		return height(root) - height(getNode(root, key));
	}

	
	public boolean contains(K key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	
	public void put(K key, V val) {
		if (key == null) throw new IllegalArgumentException("calls put() with a null key");
		if (val == null) {
			return;
		}
		root = put(root, key, val);
	}

	private Node put(Node x, K key, V val) {
		if (x == null)
		{
			ArrayList<V> lista = new ArrayList<>();
			lista.add(val);
			return new Node(key, lista, 1);
		}
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = put(x.left,  key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else              x.val.add(val);
		x.size = 1 + size(x.left) + size(x.right);
		return x;
	}

	private int height(Node x) {
		if (x == null) return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}


	
	public int height() {
		return height(root);
	}

	
	public K min() {
		if (isEmpty()) System.out.println("calls min() with empty symbol table");
		return min(root).key;
	}

	private Node min(Node x) { 
		if (x.left == null) return x; 
		else                return min(x.left); 
	} 


	
	public K max() {
		if (isEmpty()) System.out.println("calls max() with empty symbol table");
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null) return x; 
		else                 return max(x.right); 
	} 

	
	public Iterable<K> keySet() {
		if (isEmpty()) return new Queue<K>();
		return keysInRange(min(), max());
	}

	
	public Iterable<K> keysInRange(K init, K end) {
		if (init == null) throw new IllegalArgumentException("first argument to keys() is null");
		if (end == null) throw new IllegalArgumentException("second argument to keys() is null");

		Queue<K> queue = new Queue<K>();
		keys(root, queue, init, end);
		return queue;
	}

	private void keys(Node x, Queue<K> queue, K lo, K hi) { 
		if (x == null) return; 
		int cmplo = lo.compareTo(x.key); 
		int cmphi = hi.compareTo(x.key); 
		if (cmplo < 0) keys(x.left, queue, lo, hi); 
		if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
		if (cmphi > 0) keys(x.right, queue, lo, hi); 
	} 

	
	public Iterable<V> valuesInRange(K init, K end) {
		if (init == null)
			throw new IllegalArgumentException("first argument to keys() is null");
		if (end == null) 
			throw new IllegalArgumentException("second argument to keys() is null");

		ArrayList<V> queue = new ArrayList<V>(); 
		values(root, queue, init, end);
		return queue;
	}

	private void values(Node x, ArrayList<V> queue, K lo, K hi) {
		if (x == null) 
			return; 
		int cmplo = lo.compareTo(x.key); 
		int cmphi = hi.compareTo(x.key); 
		if (cmplo < 0) 
			values(x.left, queue, lo, hi); 
		if (cmplo <= 0 && cmphi >= 0) {
			ArrayList<V> w = x.val;
			for (int i = 0; i < w.size(); i++) 
				queue.add(w.get(i)); 
		}
		if (cmphi > 0) 
			values(x.right, queue, lo, hi); 
	}
	
	public Iterable<V> valueSet(){
		if (isEmpty()) 
			return new ArrayList<V>();
		return valuesInRange(min(), max());
	}

	private Node getNode(Node x, K key) {
		if (key == null) 
			throw new IllegalArgumentException("calls get() with a null key");
		if (x == null) 
			return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) 
			return getNode(x.left, key);
		else if (cmp > 0) 
			return getNode(x.right, key);
		else              
			return x;
	}

}
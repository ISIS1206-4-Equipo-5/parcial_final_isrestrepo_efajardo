
package model.data_structures;

import java.util.ArrayList;
import java.util.Comparator;

import model.logic.Compania;

public class OrdenarNumServicios {
	
	//Insertion Sort
	public OrdenarNumServicios() { }

    public static void sort(Compania[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    private static boolean less(Compania v, Compania w) {
        return v.compareNumServicios(w) > 0;
    }
        
    private static void exch(Compania[] a, int i, int j) {
        Compania swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Compania[] a) {
        return isSorted(a, 0, a.length);
    }

    private static boolean isSorted(Compania[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
}
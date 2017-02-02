package jCell;
import java.io.*;

/**
 * Diese Klasse beschreibt die lokale Uebergangsfunktion fuer den ZA Modulo5.
 *
 * @version 0.9, 6/29/1999
 * @author Juergen Pahle
 */
public class Modulo5StateChangeFunction implements StateChangeFunction, Serializable {

    /**
     * Diese Methode errechnet den neuen Zustand einer Zelle aus den Zustaenden ihrer
     * benachbarten Zellen gemaess der Vorschrift fuer den ZA Modulo5.
     *
     * @param            Array von int, der die Zustaende der benachbarten Zellen beinhaltet.
     * @return           int. Neuer Zustand.
     */
    public int calculate(int[] states) {
	int i;
	int sum = 0;

	for (i = 0; i < states.length; i++) {
	    sum = sum + states[i];
	}
	return sum % 5;
    }
}

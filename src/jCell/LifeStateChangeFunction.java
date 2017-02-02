package jCell;
import java.io.*;

/**
 * Diese Klasse beschreibt die lokale Uebergangsfunktion fuer den ZA LIFE.
 *
 * @version 0.9, 6/29/1999
 * @author Juergen Pahle
 */
public class LifeStateChangeFunction implements StateChangeFunction, Serializable {

    /**
     * Diese Methode errechnet den neuen Zustand einer Zelle aus den Zustaenden ihrer
     * benachbarten Zellen gemaess der Vorschrift fuer den ZA LIFE.
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
	if (sum <= 2) return 0;
	else if (sum == 3) return 1;
	else if (sum == 4) return states[4];
	else return 0;
    }
}

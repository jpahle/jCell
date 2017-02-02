package jCell;
import java.io.*;

/**
 * Diese Klasse beschreibt die lokale Uebergangsfunktion fuer den ZA Wireworld.
 *
 * @version 0.9, 6/29/1999
 * @author Juergen Pahle
 */
public class WireworldStateChangeFunction implements StateChangeFunction, Serializable {

    /**
     * Diese Methode errechnet den neuen Zustand einer Zelle aus den Zustaenden ihrer
     * benachbarten Zellen gemaess der Vorschrift fuer den ZA Wireworld.
     *
     * @param            Array von int, der die Zustaende der benachbarten Zellen beinhaltet.
     * @return           int. Neuer Zustand.
     */
    public int calculate(int[] states) {
	int i;
	int sum = 0;

	if (states[4] == 0) return 0;
	else if (states[4] == 1) return 3;
	else if (states[4] == 2) return 1;
	else {
	    for (i = 0; i < 4; i++) {
		if (states[i] == 2) sum++;
	    }
	    for (i = 5; i < states.length; i++) {
		if (states[i] == 2) sum++;
	    }
	    if ((sum == 1)||(sum == 2)) return 2;
	    else return 3;
	}
    }
}

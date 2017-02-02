package jCell;
import java.io.*;

/**
 * Diese Klasse implementiert die Randbehandlung, bei der alle Zellen ausserhalb des
 * zugelassenen Bereiches im Ruhezustand sind. 
 *
 * @version 0.9, 6/29/1999
 * @author Juergen Pahle
 */
public class Constant0BorderHandler implements BorderHandler, Serializable {

    /**
     * Diese Methode gibt den Zustand 0 von Zellen zurueck, deren Koordinaten ausserhalb des 
     * zugelassenen Bereiches liegen.
     */
    public int getState(int x, int y, Lattice lattice) {
	return 0;
    }
}

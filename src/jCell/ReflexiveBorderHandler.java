package jCell;
import java.io.*;

/**
 * Diese Klasse implementiert die Randbehandlung, bei der die Begrenzungen des Zellenraumes
 * die Betrachtung der Zustaende spiegeln
 *
 * @version 0.9, 6/29/1999
 * @author Juergen Pahle
 */
public class ReflexiveBorderHandler implements BorderHandler, Serializable {

    /**
     * Diese Methode gibt den Zustand von Zellen zurueck, deren Koordinaten ausserhalb des 
     * zugelassenen Bereiches liegen. Dabei spiegeln die Begrenzungen des Raumes die Zustaende.
     */
    public int getState(int x, int y, Lattice lattice) {

	x = -x;
	y = -y;
	x = x % lattice.getWidth();
	y = y % lattice.getHeight();
	if (x < 0) x = x + lattice.getWidth();
	if (y < 0) y = y + lattice.getHeight();
	return lattice.getState(x, y);
    }
}

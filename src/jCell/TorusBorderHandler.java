package jCell;
import java.io.*;

/**
 * Diese Klasse implementiert die Randbehandlung, bei der der Zellenraum die Form eines
 * Torus hat.
 *
 * @version 0.9, 6/29/1999
 * @author Juergen Pahle
 */
public class TorusBorderHandler implements BorderHandler, Serializable {

    /**
     * Diese Methode gibt den Zustand von Zellen zurueck, deren Koordinaten ausserhalb des 
     * zugelassenen Bereiches liegen. Dabei bildet der Zellenraum einen Torus.
     */
    public int getState(int x, int y, Lattice lattice) {
	x = x % lattice.getWidth();
	y = y % lattice.getHeight();
	if (x < 0) x = x + lattice.getWidth();
	if (y < 0) y = y + lattice.getHeight();
	return lattice.getState(x, y);
    }
}

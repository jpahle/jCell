package jCell;
import jCell.*;
import java.io.*;

/**
 * Diese Klasse beschreibt die Von Neumann-Nachbarschaft mit Radius 1.
 *
 * @version 0.9, 6/29/1999
 * @author Juergen Pahle
 */
public class VonNeumannNeighbourhood1 implements Neighbourhood, Serializable {

    /**
     * Diese Methode gibt einen Array mit den Zustaenden der Nachbarschaft einer Zelle
     * zurueck.
     *
     * @param             int, X-Koordinate der Zelle.
     * @param             int, Y-Koordinate der Zelle.
     * @param             Lattice, Zellenraum.
     * @return            Array, der die Zustaende der Zellen in der Nachbarschaft enthaelt.
     */
    public int[] getStates(int x, int y, Lattice lattice) {
	int[] neighbours = new int[5];

	neighbours[0] = lattice.getState(x, y-1);
	neighbours[1] = lattice.getState(x-1, y);
	neighbours[2] = lattice.getState(x, y);
	neighbours[3] = lattice.getState(x+1, y);
	neighbours[4] = lattice.getState(x, y+1);

	return neighbours;
    }
}

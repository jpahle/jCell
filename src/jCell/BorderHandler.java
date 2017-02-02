package jCell;

/**
 * Diese Schnittstelle beschreibt eine Klasse, die die Randbehandlung von endlichen
 * Zellularautomaten implementiert.
 *
 * @version 0.9, 6/29/1999
 * @author Juergen Pahle
 */
public interface BorderHandler {

    /**
     * Diese Methode gibt Zustaende von Zellen zurueck, deren Koordinaten ausserhalb des 
     * zugelassenen Bereiches liegen.
     */
    public int getState(int x, int y, Lattice lattice);
}

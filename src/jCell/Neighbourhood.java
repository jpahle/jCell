package jCell;

/**
 * Dieses Interface beschreibt eine Nachbarschaft in einem ZA.
 *
 * @version 0.9, 6/29/1999
 * @author Juergen Pahle
 */
public interface Neighbourhood {

    /**
     * Diese Methode gibt einen Array mit den Zustaenden der Nachbarschaft einer Zelle
     * zurueck.
     *
     * @param             int. X-Koordinate der Zelle.
     * @param             int. Y-Koordinate der Zelle.
     * @param             int[][]. Feld mit allen Zustaenden.
     * @param             BorderHandler, zur Behandlung des Randes des Feldes.
     * @return            Array, der die Zustaende der benachbarten Zellen enthaelt.
     */
    public int[] getStates(int x, int y, Lattice lattice);
}

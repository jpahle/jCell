package jCell;

/**
 * Diese Schnittstelle beschreibt eine lokale Uebergangsfunktion fuer einen Zellularautomaten:
 *
 * @version 0.9, 6/29/1999
 * @author Juergen Pahle
 */
public interface StateChangeFunction {

    /**
     * Diese Methode errechnet den neuen Zustand einer Zelle aus den Zustaenden ihrer
     * benachbarten Zellen.
     *
     * @param            Vector, der die Zustaende der benachbarten Zellen beinhaltet.
     * @return           int. Neuer Zustand.
     */
    public int calculate(int[] states);
}

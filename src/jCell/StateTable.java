package jCell;
import java.awt.*;

/**
 * Diese Schnittstelle beschreibt eine Farb- und Namenstabelle fuer die Zustaende der
 * Zellen eines Zellularautomaten:
 *
 * @version 0.9, 6/29/1999
 * @author Juergen Pahle
 */
public interface StateTable {


    /**
     * Diese Methode gibt Farben fuer die graph. Ausgabe zurueck, die Zustaenden
     * zugeordnet sind.
     */
    public Color getColor(int i);


    /**
     * Diese Methode gibt Namen fuer das Popup-Menu zurueck, die den Zustaenden
     * zugeordnet sind.
     */
    public String getName(int i);


    /**
     * Diese Methode setzt ein Element der Relation zwischen Zustaenden, Namen und Farben.
     */
    public void setState(int i, String name, Color color);

    /**
     * Diese Methode gibt die Anzahl der Zustaende zurueck.
     */
    public int getNumberStates();
}

package jCell;
import jCell.*;
import java.awt.*;
import java.io.*;

/**
 * Diese StateTable beschreibt die Zustaende des Zellularautomaten LIFE.
 *
 * @version 0.9, 6/29/1999
 * @author Juergen Pahle
 */
public class LifeStateTable implements StateTable, Serializable {


    /**
     * Diese Methode gibt Farben fuer die graph. Ausgabe zurueck, die Zustaenden
     * zugeordnet sind.
     */
    public Color getColor(int i) {
	if (i == 0) {
	    return (Color.black);
	}
	else if (i == 1) {
	    return (Color.blue);
	}
	else return null;
    }


    /**
     * Diese Methode gibt Namen fuer das Popup-Menu zurueck, die den Zustaenden
     * zugeordnet sind.
     */
    public String getName(int i) {
	if (i == 0) {
	    return ("inaktiv");
	}
	else if (i == 1) {
	    return ("aktiv");
	}
	else return null;
    }


    /**
     * Diese Methode gibt die Anzahl der Zustaende zurueck.
     */
    public int getNumberStates () {
	return 2;
    }


    /**
     * Diese Methode setzt ein Element der Relation zwischen Zustaenden, Namen und Farben.
     */
    public void setState(int i, String name, Color color) {}
}

package jCell;
import jCell.*;
import java.util.*;
import java.io.*;

/**
 * Diese Klasse beschreibt den 2-dimensionalen Raum der Zellen.
 *
 * @version 0.9, 6/29/1999
 * @author Juergen Pahle
 */
public class Lattice implements Serializable {

    private int[][] lattice;
    private int[][] newLattice;
    private int width;
    private int height;
    private Random random = new Random();	//Zufallszahlengenerator
    private BorderHandler borderHandler;

    /**
     * Konstruktor der Klasse Lattice.
     *
     * @param             int, Hoehe des Raumes.
     * @param             int, Breite des Raumes.
     * @param             int, Anzahl der zugelassenen Zustaende.
     */
    public Lattice(int w, int h, BorderHandler bh) {
	lattice = new int[w][h];
	newLattice = new int[w][h];
	width = w;
	height = h;
	borderHandler = bh;
    }

    /**
     * Diese Methode setzt alle Zellen im Raum auf den Zustand 0.
     */
    public void reset() {
	int x;
	int y;
	
	for (y = 0; y < height; y++) {
	    for (x = 0; x < width; x++) {
		lattice[x][y] = 0;
	    }
	}
    }
	
    /**
     * Diese Methode setzt alle Zellen auf zufaellige Zustaende im Bereich 0 bis states.
     *
     * @param             int, Anzahl der Zustaende.
     */
    public void random(int states) {
	int x;
	int y;

	for (y = 0; y < height; y++) {
	    for (x = 0; x < width; x++) {
		lattice[x][y] = (int)Math.floor(random.nextDouble() * states);
		if (lattice[x][y] == states) lattice[x][y]--;
	    }
	}
    }	


    /**
     * Diese Methode gibt den aktuellen Zustand einer Zelle zurueck.
     *
     * @param             int, X-Koordinate der Zelle.
     * @param             int, Y-Koordinate der Zelle.
     * @return            int, der aktuelle Zustand der betrachteten Zelle.
     */
    public int getState(int x, int y) {

	if (isInArea(x, y)) return lattice[x][y];
	else return borderHandler.getState(x, y, this);
    }


    /**
     * Methode zum Ueberpruefen, ob die Koordinaten innerhalb des zugelassenen Bereiches liegen.
     * 
     * @param             int, X-Koordinate.
     * @param             int, Y-Koordinate.
     * @param             boolean, true, wenn Koordinaten innerhalb des Bereichs, false sonst.
     */
    private boolean isInArea(int x, int y) {
	if ((x < 0) || (x >= width)) {
	    return false;
	}
	else if ((y < 0) || (y >= height)) {
	    return false;
	}
	else return true;
    }

    /**
     * Diese Methode setzt den Zustand einer Zelle.
     *
     * @param             int, X-Koordinate.
     * @param             int, Y-Koordinate.
     * @param             int, gewuenschter Zustand.
     */
    public void setState(int x, int y, int s) {
	if (isInArea(x, y)) lattice[x][y] = s;
    }

    /**
     * Mit dieser Methode kann man den BorderHandler fuer dieses Lattice setzen.
     */
    public void setBorderHandler(BorderHandler bh) {
	borderHandler = bh;
    }

    /**
     * Diese Methode setzt einen zukuenftigen Zustand fuer eine Zelle.
     * @param             int, X-Koordinate.
     * @param             int, Y-Koordinate.
     * @param             int, gewuenschter Zustand.
     */
    public void setNewState(int x, int y, int s) {
	if (isInArea(x, y)) newLattice[x][y] = s;
    }

    /**
     * Diese Methode aktualisiert alle Zellen mit ihren neuen Zustaenden.
     */
    public void actualizeAll() {
	int[][] hilf;

	hilf = lattice;
	lattice = newLattice;
	newLattice = hilf;
    }

    /**
     * Diese Methode veraendert die Groesse des Raumes.
     *
     * @param             int, neue Breite.
     * @param             int, neue Hoehe.
     */
    public void setSize(int w, int h) {
	int[][] hilf = new int[w][h];
	int x;
	int y;

	for (y = 0; y < h; y++) {
	    for (x = 0; x < w; x++) {
		if (isInArea(x, y)) {
		    hilf[x][y] = lattice[x][y];
		}
		else {
		    hilf[x][y] = 0;
		}
	    }
	}
	lattice = hilf;
	newLattice = new int[w][h];
    }

    /**
     * Diese Methode gibt die Breite des Zellenraumes zurueck.
     *
     * @return             int, Breite des Zellenraumes.
     */
    public int getWidth() {
	return width;
    }


    /**
     * Diese Methode gibt die Hoehe des Zellenraumes zurueck.
     *
     * @return             int, Hoehe des Zellenraumes.
     */
    public int getHeight() {
	return height;
    }
}

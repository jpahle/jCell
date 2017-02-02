package jCell;
import jCell.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

 
/**
 * Diese Klasse implementiert einen Zellularautomaten. Bei Initialisierung öffnet der
 * Zellularautomat ein unsichtbares Fenster, in dem er sich später graphisch ausgeben kann. 
 * 
 * @version    0.99, 8/14/99
 * @author     Juergen Pahle
 */


public class CellularAutomaton extends Canvas implements Runnable, Serializable {


    public boolean stopped;

    private String name;
    private Lattice lattice;
    private BorderHandler myBorderHandler;
    private StateChangeFunction myFunction;
    private Neighbourhood myNeighbourhood;
    private StateTable myStateTable;
    private int height;
    private int width;
    private int stepsToGo;
    private int cellSize;
    private boolean graphicOn;
    private Dimension preferredSize;
    private Dimension minimumSize;
    private Dimension maximumSize;
    private Frame f;
    private ScrollPane pane;
    private Image offscreenImage;
    private Graphics offscreenGraphic;
    private int delay;
    private ControlWindow control;


    /**
     * Gibt die Groesse an, die die graph. Ausgabe des ZA mindestens hat. Wichtig fuer das
     * korrekte Funktionieren der ScrollPane.
     */
    public Dimension getMinimumSize() {
	return minimumSize;
    }

    /**
     * Gibt die Standardgroesse an, die die graph. Ausgabe des ZA hat. Wichtig fuer das
     * korrekte Funktionieren der ScrollPane.
     */
    public Dimension getPreferredSize() {
	return preferredSize;
    }

    /**
     * Diese Methode setzt die Verzogerung fuer die graphische Ausgabe.
     */
    public void setDelay(int i) {
	delay = i;
    }


    /**
     * Diese Methode veranlasst das Zuruecksetzen des Zellenraumes auf den Zustand 0.
     */
    public void reset() {
	lattice.reset();
	repaint();
    }

    /**
     * Diese Methode veranlasst das zufaellige Setzen von Zustaenden im Zellenraum.
     */
    public void random() {
	lattice.random(myStateTable.getNumberStates());
	repaint();
    }


    /**
     * Diese Methode gibt den Namen des ZA zurueck.
     */
    public String getName() {
	return name;
    }

    /**
     * Diese Methode setzt den Namen des ZA.
     */
    public void setName(String n) {
	name = n;
    }

    /**
     * Diese Methode gibt die Nachbarschaft zueruck.
     */
    public Neighbourhood getNeighbourhood() {
	return myNeighbourhood;
    }

    /**
     * Diese Methode gibt die StateChangeFunction zurueck.
     */
    public StateChangeFunction getStateChangeFunction() {
	return myFunction;
    }

    /**
     * Diese Methode gibt die StateTable zurueck.
     */
    public StateTable getStateTable() {
	return myStateTable;
    }

    /**
     * Diese Methode gibt den BorderHandler zurueck.
     */
    public BorderHandler getBorderHandler() {
	return myBorderHandler;
    }

    /**
     * Diese Methode gibt das Lattice zurueck.
     */
    public Lattice getLattice() {
	return lattice;
    }

    /**
     * Mit dieser Methode kann der Zellenraum gesetzt werden.
     */
    public void setLattice(Lattice l) {
	lattice = l;
    }


    /**
     * Diese Methode setzt den zu verwendeten BorderHandler.
     */
    public void setBorderHandler(BorderHandler bh) {
	myBorderHandler = bh;
	lattice.setBorderHandler(bh);
    }

    /**
     * Diese Methode setzt die Groesse des Automaten.
     */
    public void setSize(int x, int y) {
	width = x;
	height = y;
	lattice.setSize(x, y);
    }

    /**
     * Diese Methode setzt die StateChangeFunction.
     */
    public void setStateChangeFunction(StateChangeFunction function) {
	myFunction = function;
    }

    /**
     * Diese Methode setzt die StateTable.
     */
    public void setStateTable(StateTable table) {
	myStateTable = table;
    }

    /**
     * Diese Methode setzt die Nachbarschaft.
     */
    public void setNeighbourhood(Neighbourhood hood) {
	myNeighbourhood = hood;
    }

    /**
     * Ein Konstruktor der Klasse CellularAutomaton, der viele Parameter entgegennimmt.
     */
    public CellularAutomaton(String n, int h, int w, BorderHandler bh, StateChangeFunction scf, StateTable st, Neighbourhood nh, int vz, ControlWindow cw){

	stopped = false;
	graphicOn = false;
	name = n;
	cellSize = 10;
	myBorderHandler = bh;
	myFunction = scf;
	myStateTable = st;
	myNeighbourhood = nh;
	height = h;
	width = w;
	lattice = new Lattice(w, h, bh);
	delay = vz;
	control = cw;
	preferredSize = new Dimension(cellSize * width, cellSize * height);
	maximumSize = preferredSize;
	minimumSize = new Dimension(10,10);
	f = new CellularAutomatonFrame(name, new Dimension(1000, 1000));
	f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
	    }
        });
	this.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
	    } 
	    public void mouseEntered(MouseEvent e) {
	    }
	    public void mouseExited(MouseEvent e) {
	    }
	    public void mousePressed(MouseEvent e) {
		int x;
		int y;
		x = getXCoord(e.getPoint());
		y = getYCoord(e.getPoint());
		lattice.setState(x, y, (lattice.getState(x, y)+1)%myStateTable.getNumberStates());
		repaint();
	    }
	    public void mouseReleased(MouseEvent e) {
	    }
	});
	ScrollPane pane = new ScrollPane();
	pane.setSize(new Dimension(800, 800));
	f.setLayout(new BorderLayout());
	pane.add(this);
	f.add("Center", pane);
	f.pack();
	f.setVisible(false);
    }
    
    /**
     * Zeichnet den aktuellen Zustand des Zellularautomaten.
     */
    public void paint(Graphics g) {
	int x;
	int y;

	for (y = 0; y < height; y++) {
	    for (x = 0; x < width; x++) {
		g.setColor(myStateTable.getColor(lattice.getState(x, y)));
		g.fillRect(x*cellSize, y*cellSize, cellSize, cellSize);
	    }
	}
    }
    
    /**
     * Diese Methode implementiert das Double-Buffering, um das Flackern des Bildes zu
     * vermeiden.
     */
    public void update(Graphics g) {
	if (offscreenImage==null) {
	    offscreenImage = createImage(width * cellSize, height * cellSize);
	    offscreenGraphic = offscreenImage.getGraphics();
	}
	paint(offscreenGraphic);
	g.drawImage(offscreenImage,0,0,this);
    }

    
    /**
     * Diese Methode zeigt den aktuellen Zustand des Zellularautomaten graphisch an.
     */
    public void showCA() {
	Dimension d = new Dimension();
	d = getPreferredSize();
	f.setSize(d);
	repaint();
	f.setVisible(true);
	graphicOn = true;
    }
    
    /**
     * Diese Methode beendet die graphische Ausgabe des Zellularautomaten.
     */
    public void hideCA() {
	f.setVisible(false);
	graphicOn = false;
    }
    

    /**
     * Diese Methode gibt die X-Coordinate einer angeklickten Zelle zurueck. Als Eingabe
     * dienen die Koordinaten eines Punktes der graph. Ausgabe.
     *
     * @return            X-Coordinate der gewuenschten Zelle als int.
     */
    private int getXCoord(Point point) {
	return (point.x / cellSize);
    }


    /**
     * Diese Methode gibt die Y-Coordinate einer angeklickten Zelle zurueck. Als Eingabe
     * dienen die Koordinaten eines Punktes der graph. Ausgabe.
     *
     * @return            X-Coordinate der gewuenschten Zelle als int.
     */
    private int getYCoord(Point point) {
	return (point.y / cellSize);
    }


    /**
     * In dieser Methode wird die eigentliche Berechnung (ein Uebergang) ausgefuehrt.
     * Die graphische Ausgabe wird bei graphicOn == true automatisch  aktualisiert.
     */
    public void run() {

	boolean end = false;
	int i = 0;
	int x;
	int y;
	int[] neighbours;

	while (!end) {
	    /* Berechnung Anfang */
	    i++;
	    for (y = 0; y < height; y++) {
		for (x = 0; x < width; x++) {
		    neighbours = myNeighbourhood.getStates(x, y, lattice);
		    lattice.setNewState(x, y, myFunction.calculate(neighbours));
		}
	    }
	    lattice.actualizeAll();
	    control.incrementCounter();
	    /* Berechnung Ende */
	    if (graphicOn) {
		repaint();
		try {
		    Thread.currentThread().sleep(delay);
		}
		catch(Exception e) {}
	    }
	    if ( (i >=  stepsToGo) || (stopped == true) ) end = true;
	}
	if (stopped==false) control.computationStopped(false);
	else control.computationStopped(true);
    }

    public void compute(int steps) {

	stepsToGo = steps;
	Thread t = new Thread(this);
       	t.setPriority(java.lang.Thread.MIN_PRIORITY);
	t.start();
    }
}

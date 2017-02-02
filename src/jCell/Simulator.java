package jCell;
import jCell.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/** 
 * Simulator ist die Startklasse des Projekts jCell (Prakt. Web-Technologien) zur
 * Bearbeitung von Zellularautomaten. Simulator erbt von Applet
 * und kann deswegen in einem Java 1.1-faehigen Browser ausgefuehrt werden. Ausserdem
 * besitzt es eine Main-Methode und ist deswegen auch als Application startbar. Als
 * Applet gestartet wird ein Button angezeigt, mit dem man das Aufspringen eines
 * ControlWindow veranlassen kann. Beim Starten als Application erscheint ein solches
 * ControlWindow sofort, das eine graphische Oberflaeche zur Bearbeitung von
 * Zellularautomaten bietet. Simulator implementiert die Schnittstelle
 * ActionListener und wird selbst als ActionListener fuer den eigens erzeugten
 * Button eingesetzt.
 *  
 * @version 0.9, 6/22/99
 * @author Juergen Pahle
 */

public class Simulator extends Applet implements ActionListener {

    /** 
     * Ein Frame, der wenn Simulator als Applet gestartet wird, das
     * ControlWindow referenziert.
     */

    Frame controlWindow;
    Image logo;

    /**
     * Erzeugt ein Instanz der Klasse ControlWindow und zeigt diese an.
     * Dabei wird dem Konstruktor der Klasse ControlWindow als boolean-Parameter 
     * der Wert false angegeben, als Zeichen dafuer, dass eine Ausfuehrung als
     * Application gewuenscht ist.
     *
     * @param args     Ein Array von Strings, das hier bedeutungslos ist.
     */

    public static void main(String[] args) {
        Frame controlWindow;
	
        controlWindow = new ControlWindow(false, Toolkit.getDefaultToolkit().getImage("jCell.gif"));
        controlWindow.pack();
	controlWindow.setResizable(false);
	controlWindow.setVisible(true);
    }

    /**
     * Erzeugt einen Button, mit dem die Erzeugung einer Instanz der Klasse
     * ControlWindow veranlasst werden kann. Als ActionListener wird der Simulator
     * selbst verwendet.Wird beim Starten des Applet vom Browser aufgerufen.
     */

    public void init() {
        Button button = new Button("Start the Simulator !");
        button.addActionListener(this);
	setLayout(new FlowLayout());
        add(button);
	repaint();
	logo = getImage(getCodeBase(), "jCell.gif");
    }

    /**
     * Erzeugt eine Instanz der Klasse ControlWindow und packt diese zusammen.
     * Wird beim Starten als Applet vom Browser aufgerufen.
     */

    public void start() {
        controlWindow = new ControlWindow(true, logo);
        controlWindow.pack();
	controlWindow.setResizable(false);
    }

    /**
     * Macht das ControlWindow unsichtbar und belegt die Klassenvariable controlWindow
     * mit null. Wird vom Browser beim beenden des Applets aufgerufen.
     */

    public void destroy() {
        controlWindow.setVisible(false);
        controlWindow = null;
    }

    /**
     * Macht das vorher in start() erzeugte ControlWindow sichtbar, wenn der Button im
     * Browser angeklickt wird.
     */

    public void actionPerformed(ActionEvent event) {
        controlWindow.setVisible(true);
    }
}


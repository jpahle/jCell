package jCell;
import jCell.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/** 
 * ControlWindow ist die Klasse des Systems jCell (PraktWebTech SS99), die
 * die graphische Benutzeroberflaeche zur Bearbeitung der Zellularautomaten (ZA) bereitstellt.
 * Sie erbt dabei von Frame, stellt also ihr eigenes Fenster dar, und implementiert
 * die Schnittstellen ActionListener und ItemListener, so dass sie als ActionListener 
 * fuer alle ihre Komponenten dienen kann.
 * Ein angezeigtes ControlWindow teilt sich dabei in fuenf Bereiche:
 * 1. Eine Reihe Buttons ganz oben.
 * 2. Ein Bereich in dem Berechnungsvorgaenge gestartet werden koennen.
 * 3. Ein Panel, in dem Informationen fuer den Benutzer angezeigt werden.
 * 4. Ein Panel zum Neuinitialisieren des ZA und zum Einstellen einer Verzoegerung der
 *    graphischen Ausgabe.
 * 5. Einen Messagebereich, in dem Meldungen ueber Aktionen des Benutzers bzw. ueber Fehler
 *    ausgegeben werden.
 * Zur Benutzung:
 * Anfangs ist nur der Load-Button, der New-Button und der Exit-Button aktiviert. Die Funktion
 * des Exit-Buttons duerfte allgemein bekannt sein. Der Load-Button verursacht ein Laden
 * eines Zellularautomaten aus einer Datei. Mit dem New-Button kann ein neuer ZA erzeugt werden.
 * Wurde der ZA erfolgreich geladen oder erzeugt, so werden die übrigen Buttons aktiv.
 * Beim Anklicken des Buttons "Show" wird der aktuelle ZA in einem neuen Fenster angezeigt.
 * Das Druecken von "Hide" versteckt entsprechend die graph. Ausgabe.
 * Die eigentliche Berechnung wird durch die Buttons "Run" und "Step" angestossen. Mit "Step"
 * wird genau ein Übergang berechnet. Durch Drücken des Buttons "Run" wird die im Textfield 
 * "Steps" angegebene Anzahl von Übergängen abgearbeitet.
 * Das Anklicken des Buttons "Stop" hält den Berechnungsvorgang an.
 *
 * @version 0.99, 8/14/99
 * @author Juergen Pahle
 */

public class ControlWindow extends Frame implements ActionListener, AdjustmentListener {

    /**
     * Ein Panel, das die Reihe der Buttons aufnimmt.
     */
    private Panel buttonPanel;

    /**
     * Ein Panel, das Komponenten zum Start des Berechnungsvorganges beinhaltet.
     */
    private Panel runPanel;

    /**
     * Ein Panel, das Buttons zur Steuerung des Berechnungsvorganges beinhaltet.
     */
    private Panel controlPanel;

    /**
     * Ein Panel, das die Messageausgabe und das Logo beinhaltet.
     */
    private Panel messagePanel;

    /**
     * Ein Panel, das Informationen fuer den Benutzer enthält.
     */
    private Panel outputPanel;

    /**
     * Eine TextArea, die alle Meldungen des Systems an den Benutzer ausgibt.
     */
    private TextArea messages;

    /**
     * Eine Choice, zum Auswaehlen der zu benutzenden Randbehandlung.
     */
    private Choice borderChoice;

    /**
     * Ein Button, zum Laden aus Dateien.
     */
    private Button loadButton;
 
    /**
     * Ein Button, zum Sichern in Dateien.
     */
    private Button saveButton;

    /**
     * Ein Button, zum Erzeugen benutzerdefinierter Zellularautomaten.
     */
    private Button newButton;

    /**
     * Ein Button, zum Anzeigen der graphischen Ausgabe.
     */
    private Button showButton;

    /**
     * Ein Button, zum Durchführen genau eines Überganges.
     */
    private Button stepButton;

    /**
     * Ein Button zum Reseten des Counters.
     */
    private Button resetCounterButton;

    /**
     * Textfeld, zum Eingeben der Anzahl von durchzuführenden Übergängen..
     */
    private TextField stepsTextField;

    /**
     * Ein Button, zum Durchführen mehrerer Übergänge. (Anzahl siehe Textfield maxSteps)
     */
    private Button runButton;

    /**
     * Ein Button, zum Beenden des Programms.
     */
    private Button exitButton;

    /**
     * Ein Button zum Editieren des aktuellen Zellularautomaten.
     */
    private Button editButton;

    /**
     * Ein Button zum Reseten des ZA.
     */
    private Button resetButton;

    /**
     * Ein Button zur zufaelligen Belegung der Zellen mit Zustaenden.
     */
    private Button randomButton;

    /**
     * Eine Scrollbar ,zum Einstellen der Verzoegerung bei der graphischen Anzeige.
     */
    private Scrollbar delayScrollbar;

    /**
     * Eine Integer, zum Zaehlen der schon in der TextArea "messages" ausgegebenen
     * Meldungen.
     */
    private int i=0;

    /**
     * Eine Integer, zum Zaehlen der durchgefuehrten Berarbeitungsschritte.
     */
    private int count = 0;

    /**
     * Der Zellularautomat, dessen Methoden zur Bearbeitung aufgerufen werden.
     */
    private CellularAutomaton myCA;

    /**
     * Eine boolean, die anzeigt, ob die Ausfuehrung im Browser oder als
     * Application erfolgt.
     */
    private boolean inApplet;

    /**
     * GridLayout fuer die Panels.
     */
    private GridLayout gridOutput;
    private GridLayout gridRun;
    private GridLayout gridButton;
    private GridLayout gridControl;

    /** 
     * GridBagConstraints fuer die Panels.
     */
    private GridBagConstraints c;

    /**
     * GridBagLayout fuer die Panels.
     */
    private GridBagLayout gridBagAll;
    private GridBagLayout gridBagOutput;

    /**
     * Labels zur Bezeichnung des TextFields.
     */
    private Label stepsLabel;


    /**
     * Spezielle Canvas zur Aufnahme des Logos.
     */
    private LogoCanvas logoCanvas;

    /**
     * Label zur Bezeichnung der Scrollbar.
     */
    private Label delayLabel;

    /**
     * Labels fuer Infos an den Benutzer.
     */
    private Label name1Label;
    private Label name2Label;
    private Label count1Label;
    private Label count2Label;

    /**
     * FileDialog, zum Laden und Abspeichern von Dateien.
     */
    private FileDialog fDialog;

    /**
     * Konstruktor der Klasse, der als Argument eine boolean erwartet, die den
     * Ausfuehrungsmodus anzeigt. Es wird ein Fenster erzeugt, in dem alle
     * Komponenten erscheinen, die zur Arbeit mit diesem Programm notwendig sind.
     * Ein als innere Klasse definierter WindowAdapter kontrolliert das Schliessen
     * des ControlWindow und als ActionListener fuer die Buttons dient das
     * ControlWindow selbst.
     * Zum Aufbau der Oberflaeche:
     * ControlWindow mit Namen "jCell" und GridBagLayout beinhaltet
     * das buttonPanel, das runPanel, das outputPanel und ein messagePanel.
     * Das buttonPanel hat ein GridLayout und beinhaltet die sechs Buttons zur
     * Kontrolle des Programms. Dazu gehoeren der Load-, Save-, New-, Edit-, Show- und
     * der Exit-Button. Load- und Save-Button sind nur aktiv, wenn das Programm als
     * Application gestartet wurde.
     * Das runPanel mit GridLayout beinhaltet zwei Buttons zum Start von 
     * Berechnungsvorgaengen. Mit dem Step-Button kann genau ein Uebergang durchgefuehrt
     * werden. Durch Betaetigen des Run-Buttons wird die Anzahl von Uebergaengen 
     * durchgefuehrt, die im Textfield stepsTextField angegeben wurde. 
     * Im outputPanel werden Informationen ueber den Zellularautomaten fuer den
     * Benutzer ausgegeben. Ausserdem kann man mit dem resetCounterButton den Zaehler
     * fuer die durchgefuehrten Berechnungsschritte wieder auf Null setzen.
     * das controlPanel beinhaltet zwei Buttons und eine Scrollbar. Mit dem Reset- und dem
     * Random-Button koennen die Zustaende des Zellularautomaten entsprechend gesetzt
     * werden. Mit der Scrollbar delaySrollbar kann eine Verzoegerung der graphischen 
     * Ausgabe eingestellt werden. 
     * Das messagePanel ganz unten im ControlWindow gibt Meldungen an den Benutzer ueber 
     * die TextArea messages aus und beinhaltet noch das Logo des Programms in der
     * logoCanvas.
     * Die TextArea messages ist nicht editierbar.
     *
     * @param flag      Eine boolean, die den Ausfuehrungsmodus des Programms anzeigt.
     *                  true entspricht Ausfuehrung als Applet, sonst Ausf. als
     *                  Application.
     */
    public ControlWindow(boolean flag, Image logo) {
        super("jCell");
	inApplet = flag;

        gridBagAll = new GridBagLayout();
	c = new GridBagConstraints();
	setLayout(gridBagAll);

	buttonPanel = new Panel();
	gridButton = new GridLayout(1,6,2,5);
	buttonPanel.setLayout(gridButton);

	loadButton = new Button("Load");
	loadButton.setActionCommand("LOAD");
	loadButton.addActionListener(this);
	if (inApplet) loadButton.setEnabled(false);
	saveButton = new Button("Save");
	saveButton.setActionCommand("SAVE");
	saveButton.addActionListener(this);
	saveButton.setEnabled(false);
	newButton = new Button("New");
	newButton.setActionCommand("NEW");
	newButton.addActionListener(this);
	editButton = new Button("Edit");
	editButton.setActionCommand("EDIT");
	editButton.addActionListener(this);
	editButton.setEnabled(false);
        showButton = new Button("Show");
	showButton.setActionCommand("SHOW");
	showButton.addActionListener(this);
	showButton.setEnabled(false);
	exitButton = new Button("Exit");
	exitButton.setActionCommand("EXIT");
	exitButton.addActionListener(this);
      
	buttonPanel.add(loadButton);
	buttonPanel.add(saveButton);
	buttonPanel.add(newButton);
	buttonPanel.add(editButton);
	buttonPanel.add(showButton);
	buttonPanel.add(exitButton);

	runPanel = new Panel();
	gridRun = new GridLayout(1,4,2,5);
	runPanel.setLayout(gridRun);
	
	stepsLabel = new Label("Steps:");
	stepButton = new Button("Step");
	stepButton.setActionCommand("STEP");
	stepButton.addActionListener(this);
	stepButton.setEnabled(false);
	runButton = new Button("Run");
	runButton.setActionCommand("RUN");
	runButton.addActionListener(this);
	runButton.setEnabled(false);
	stepsTextField = new TextField("50", 10);

	runPanel.add(stepButton);
	runPanel.add(runButton);
	runPanel.add(stepsLabel);
	runPanel.add(stepsTextField);

	
	outputPanel = new Panel();
	gridOutput = new GridLayout(1,5,2,5);
	outputPanel.setLayout(gridOutput);
	name1Label = new Label("Cellular Automaton:");
	name2Label = new Label("none");
	count1Label = new Label("Stepscount:");
	count2Label = new Label("0");
	resetCounterButton = new Button("Reset Counter");
	resetCounterButton.setActionCommand("RESETCOUNTER");
	resetCounterButton.addActionListener(this);
	outputPanel.add(name1Label);
	outputPanel.add(name2Label);
	outputPanel.add(count1Label);
	outputPanel.add(count2Label);
	outputPanel.add(resetCounterButton);

	controlPanel = new Panel();
	gridControl = new GridLayout(1,4,2,5);
	controlPanel.setLayout(gridControl);
	delayLabel = new Label("Delay:");
	delayScrollbar = new Scrollbar(Scrollbar.HORIZONTAL,0,10,0,250);
	delayScrollbar.addAdjustmentListener(this);
	resetButton = new Button("Reset");
	resetButton.setActionCommand("RESET");
	resetButton.addActionListener(this);
	resetButton.setEnabled(false);
	randomButton = new Button("Random");
	randomButton.setActionCommand("RANDOM");
	randomButton.addActionListener(this);
	randomButton.setEnabled(false);
	controlPanel.add(resetButton);
	controlPanel.add(randomButton);
	controlPanel.add(delayLabel);
	controlPanel.add(delayScrollbar);

	messages = new TextArea(5,90);
	messages.setEditable(false);
	logoCanvas = new LogoCanvas(logo);
	messagePanel = new Panel();
	messagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	messagePanel.add(logoCanvas);
	messagePanel.add(messages);

	c.fill = GridBagConstraints.BOTH;
	c.weightx = 1.0;
	c.insets = new Insets(0,0,0,0);
	c.gridx = GridBagConstraints.RELATIVE;
	c.gridy = GridBagConstraints.RELATIVE;
	c.gridwidth  = GridBagConstraints.REMAINDER;
	gridBagAll.setConstraints(buttonPanel,c);
	add(buttonPanel);
	gridBagAll.setConstraints(runPanel,c);	
	add(runPanel);
	gridBagAll.setConstraints(controlPanel,c);
	add(controlPanel);
	gridBagAll.setConstraints(outputPanel,c);
	add(outputPanel);
	c.gridheight = 2;
	c.weighty = 1.0;
	gridBagAll.setConstraints(messagePanel,c);
	add(messagePanel);

	fDialog = new FileDialog(this);
	fDialog.setDirectory("../data");

	addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent event) {
		setVisible(false);
		if (inApplet == false) {
		    System.exit(0);
		}
	    }
	});
    }
    /**
     * Gibt eine Meldung ueber die TextArea im ControlWindow an den Benutzer aus.
     *
     * @param m         String, der die auszugebende Meldung darstellt.
     */
    void displayMessage(String m) {
	i=i+1;
 	messages.append(i+": "+m+"\n");
    }

    /**
     * Wird aufgerufen, wenn der Berechnungsvorgang erfolgreich abgebrochen oder abgeschlossen
     * wurde.
     * 
     * @param abgebrochen      boolean, die anzeigt, ob der Berechnungsvorgang abgebrochen wurde.
     */
    public void computationStopped(boolean abgebrochen) {
	myCA.stopped = false;
	if (inApplet == false) {
	    loadButton.setEnabled(true);
	    saveButton.setEnabled(true);
	}
	showButton.setEnabled(true);
	newButton.setEnabled(true);
	editButton.setEnabled(true);
	stepButton.setEnabled(true);
	randomButton.setEnabled(true);
	resetButton.setEnabled(true);
	runButton.setLabel("Run");
	runButton.setActionCommand("RUN");
	if (abgebrochen) {displayMessage("Der Berechnungsvorgang wurde abgebrochen!");
	} else {displayMessage("Der Berechnungsvorgang ist abgeschlossen.");}
    }

    /**
     * Diese Methode wird aufgerufen, wenn ein neuer Automat erzeugt wurde.
     */
    public void cellularAutomatonCreated(CellularAutomaton ca) {
	if (ca == null) {
	    if (inApplet == false) loadButton.setEnabled(true);
	    newButton.setEnabled(true);
	}
	else {
	    showButton.setEnabled(true);
	    runButton.setEnabled(true);
	    stepButton.setEnabled(true);
	    editButton.setEnabled(true);
	    resetButton.setEnabled(true);
	    randomButton.setEnabled(true);
	    newButton.setEnabled(true);
	    if (inApplet == false) {
		loadButton.setEnabled(true);
		saveButton.setEnabled(true);
	    }
	    myCA = ca;
	    name2Label.setText(myCA.getName());
	    displayMessage("Ein neuer Automat wurde erzeugt.");
	}
    }

    /**
     * Diese Methode ist zur Bearbeitung der durch die delayScrollbar erzeugten Events
     * zustaendig.
     *
     * @param             Ein AdjustmentEvent, das von der Scrollbar erzeugt wurde.
     */
    public void adjustmentValueChanged(AdjustmentEvent event) {
	myCA.setDelay(delayScrollbar.getValue() * 2);
    }


    /**
     * Diese Methode inkrementiert den Zaehler fuer die gemachten Berechnungsschritte.
     */
    public void incrementCounter() {
	count++;
	count2Label.setText(Integer.toString(count));
    }


    /**
     * Uebernimmt die Verwaltung der durch die Mausklicks erzeugten Events.
     * Dabei werden je nach Kommandoart (Save,Load,usw.) entsprechende Methoden
     * des Zellularautomaten aufgerufen, Buttons enabled oder Textfields
     * ausgelesen, um die vom Benutzer angegebenen Eingaben zu verarbeiten.
     *
     * @param event       Ein ActionEvent, das von einem der Buttons erzeugt wurde.
     */
    public void actionPerformed(ActionEvent event) {

	String filename;

	String command = event.getActionCommand();
	if (command == "EXIT") {
	    setVisible(false);
	    if (inApplet == false) {
		System.exit(0);}
	    else {
	      if (showButton.getActionCommand() == "HIDE") {
		myCA.hideCA();
		showButton.setLabel("Show");
		showButton.setActionCommand("SHOW");
	      }
	    }
	}
	else {
	    if (command == "LOAD") {	    
		try {
		    if (showButton.getActionCommand() == "HIDE") { 
			myCA.hideCA();
			showButton.setLabel("Show");
			showButton.setActionCommand("SHOW");
			displayMessage("Graphische Ausgabe wird beendet.");
		    }
		    fDialog.setTitle("Laden von ...");
		    fDialog.setMode(java.awt.FileDialog.LOAD);
		    fDialog.show();
		    filename = fDialog.getFile();
		    if (filename == null) {
			displayMessage("Es wurde kein Dateiname angegeben!");
		    }
		    else {
			filename = fDialog.getDirectory() + filename;
			FileInputStream in = new FileInputStream(filename);
			ObjectInputStream s = new ObjectInputStream(in);
			String n = (String)s.readObject();
			Neighbourhood nh = (Neighbourhood)s.readObject();
			StateChangeFunction scf = (StateChangeFunction)s.readObject();
			StateTable st = (StateTable)s.readObject();
			Lattice l = (Lattice)s.readObject();
			BorderHandler bh = (BorderHandler)s.readObject();
			myCA = new CellularAutomaton(n, l.getHeight(), l.getWidth(), bh, scf, st, nh, 0, this);
			myCA.setLattice(l);
			name2Label.setText(myCA.getName());
			showButton.setEnabled(true);
			runButton.setEnabled(true);
			stepButton.setEnabled(true);
			editButton.setEnabled(true);
			if (inApplet == false) saveButton.setEnabled(true);
			resetButton.setEnabled(true);
			randomButton.setEnabled(true);
			displayMessage("Graph wird geladen von: "+filename);
		    }
		}
		catch(Exception e) {
		    displayMessage(e.getClass()+" "+e.getMessage());
		}
	    }
	    else {
		if (command == "SAVE") {
		    try {
			fDialog.setMode(java.awt.FileDialog.SAVE);
			fDialog.setTitle("Speichern von ...");
			fDialog.show();
			filename = fDialog.getFile();
			if (filename == null) {
			    displayMessage("Es wurde kein Dateiname angegeben!");
			}
			else {
			    filename = fDialog.getDirectory() + filename;
			    FileOutputStream f = new FileOutputStream(filename);
			    ObjectOutput  s  =  new  ObjectOutputStream(f);
			    s.writeObject(myCA.getName());
			    s.writeObject(myCA.getNeighbourhood());
			    s.writeObject(myCA.getStateChangeFunction());
			    s.writeObject(myCA.getStateTable());
			    s.writeObject(myCA.getLattice());
			    s.writeObject(myCA.getBorderHandler());
			    s.flush();
			    displayMessage("Zellularautomat wird abgespeichert in: "+filename);
			}
		    }
		    catch(Exception e) {
			displayMessage(e.getClass()+" "+e.getMessage());
		    }
		}
		else {
		    if (command == "SHOW") {
			try {
			    myCA.showCA();
			    showButton.setLabel("Hide");
			    showButton.setActionCommand("HIDE");
			    displayMessage("Zellularautomat wird angezeigt.");
			}
			catch(Exception e) {
			    displayMessage(e.getClass()+" "+e.getMessage());
			}
		    }
		    else {
			if (command == "HIDE") {
			    try {
     				myCA.hideCA();
				showButton.setLabel("Show");
				showButton.setActionCommand("SHOW");
				displayMessage("Graphische Ausgabe wird beendet.");
			    }
			    catch(Exception e) {
				displayMessage(e.getClass()+" "+e.getMessage());
			    }
			}
			else {
			    if (command == "RUN") {
				try {
				    Integer parSteps = new Integer(stepsTextField.getText());
				    loadButton.setEnabled(false);
				    saveButton.setEnabled(false);
				    showButton.setEnabled(false);
				    newButton.setEnabled(false);
				    editButton.setEnabled(false);
				    stepButton.setEnabled(false);
				    randomButton.setEnabled(false);
				    resetButton.setEnabled(false);
				    runButton.setLabel("Stop");
				    runButton.setActionCommand("STOP");
		       		    myCA.compute(parSteps.intValue());
				    displayMessage("Der Berechnungsvorgang wurde gestartet.");
				}
				catch(Exception e) {
				    displayMessage("Fehler beim Starten des Berechnungsvorgangs. "+e.getMessage());
				}
			    }
			    else {
				if (command == "STEP") {
				    try {
					loadButton.setEnabled(false);
					saveButton.setEnabled(false);
					showButton.setEnabled(false);
					newButton.setEnabled(false);
					editButton.setEnabled(false);
					stepButton.setEnabled(false);
					randomButton.setEnabled(false);
					resetButton.setEnabled(false);
					myCA.compute(1);
					displayMessage("Es wird ein Berechnungsschritt durchgefuehrt.");
				    }
				    catch(Exception e) {
					displayMessage("Fehler beim Starten des Berechnungsvorgangs. "+e.getMessage());
				    }
				}
				else {
				    if (command == "STOP") {
					displayMessage("Der Berechnungsvorgang wird gleich gestoppt.");
					myCA.stopped=true;
				    }
				    else {
					if (command == "NEW") {
					    try {
						if (showButton.getActionCommand() == "HIDE") { 
						    myCA.hideCA();
						    showButton.setLabel("Show");
						    showButton.setActionCommand("SHOW");
						    displayMessage("Graphische Ausgabe wird beendet.");
						}
						showButton.setEnabled(false);
						runButton.setEnabled(false);
						stepButton.setEnabled(false);
						editButton.setEnabled(false);
						saveButton.setEnabled(false);
						resetButton.setEnabled(false);
						randomButton.setEnabled(false);
						loadButton.setEnabled(false);
						newButton.setEnabled(false);
						NewWindow newWindow = new NewWindow(this);
						newWindow.pack();
						newWindow.setVisible(true);
					    }
					    catch (Exception e) {
						displayMessage("Fehler beim Erzeugen des Zellularautomaten. "+e.getMessage());
					    }
					}
					else {
					    if (command == "EDIT") {
						try {
						    /* Hier muss noch Code eingefuegt werden. */
						    displayMessage("Sorry, not yet implemented!");
						}
						catch (Exception e) {
						    displayMessage("Fehler beim Editieren des Zellularautomaten. "+e.getMessage());
						}
					    }
					    else {
						if (command == "RESET") {
						    try {
							myCA.reset();
							displayMessage("Alle Zustaende wurden zurueckgesetzt.");
						    }
						    catch (Exception e) {
							displayMessage("Fehler beim Zuruecksetzen der Zustaende. "+e.getMessage());
						    }
						}
						else {
						    if (command == "RANDOM") {
							try {
							    myCA.random();
							    displayMessage("Alle Zellen wurden mit zufaelligen Zustaenden belegt.");
							}
							catch (Exception e) {
							    displayMessage("Fehler beim zufaelligen Setzen von Zustaenden. "+e.getMessage());
							}
						    }
						    else {
							if (command == "RESETCOUNTER") {
							    try {
								count = 0;
								count2Label.setText(Integer.toString(count));
								displayMessage("Der Zaehler wurde zurueckgesetzt. ");
							    }
							    catch (Exception e) {
								displayMessage("Fehler beim Zuruecksetzen des Zaehlers. "+e.getMessage());
							    }
							}
						    }
						}
					    }
					}
				    }
				}
			    }
			}
		    }
		}
	    }
	}
    }
}

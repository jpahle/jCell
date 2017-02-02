package jCell;
import java.awt.event.*;
import jCell.*;
import java.awt.*;

/** 
 * NewWindow ist die Klasse des Systems jCell zum Erzeugen von neuen ZAs.
 *
 * @version 1.0, 6/1/98
 * @author Juergen Pahle
 */

public class NewWindow extends Frame implements ActionListener, ItemListener {

    /**
     * Ein Panel, das die Choice fuer die Automaten aufnimmt.
     */
    private Panel choicePanel;

    /**
     * Panels, die die Komponenten zur Parametereingabe beinhalten.
     */
    private Panel parPanel;
    private Panel parSpeziellPanel;

    /**
     * Panel zur Aufnahme der Buttons.
     */
    private Panel buttonPanel;

    /**
     * TextFields, zum Eingeben der gewuenschten Groesse.
     */
    private TextField parHeightTextField;
    private TextField parWidthTextField;

    /**
     * Ein Button, des NewWindow.
     */
    private Button okButton;
 
    /**
     * Ein Button, des NewWindow.
     */
    private Button cancelButton;

    /**
     * Unterschiedliche GridLayouts.
     */
    private GridLayout gridAll;
    private GridLayout gridChoice;
    private GridLayout gridPar;
    private GridLayout gridSpeziellPar;
    private GridLayout gridButton;

    /**
     * Choice zur Auswahl des CA und des BorderHandlers.
     */
    private Choice caChoice;
    private Choice borderHandlerChoice;

    /**
     * Labels zur Bezeichnung der TextFields.
     */
    private Label heightLabel;
    private Label widthLabel;

    /**
     * Buttons zum Editieren von CAs
     */
    private Button statesButton;
    private Button functionButton;
    private Button neighbourhoodButton;

    /**
     * Aufrufer des NewWindow: ControlWindow
     */
    private ControlWindow control;

    /**
     * Konstruktor der Klasse.
     */
    public NewWindow(ControlWindow c) {
        super("Create new CellularAutomaton");

	control = c;
        GridLayout gridAll = new GridLayout(4,1,2,5);

	choicePanel = new Panel();
	gridChoice = new GridLayout(1,2,2,5);
	choicePanel.setLayout(gridChoice);
        caChoice = new Choice();
        caChoice.addItem("Life");
	caChoice.addItem("Wireworld");
	caChoice.addItem("Banks");
	caChoice.addItem("Modulo5");
	caChoice.addItem("user defined");
	caChoice.addItemListener(this);
        borderHandlerChoice = new Choice();
        borderHandlerChoice.addItem("Torus");
	borderHandlerChoice.addItem("Reflexive");
	borderHandlerChoice.addItem("Constant0");
	borderHandlerChoice.addItemListener(this);
      	choicePanel.add(caChoice);
	choicePanel.add(borderHandlerChoice);

	parPanel = new Panel();
	gridPar = new GridLayout(1,4,2,5);
	parPanel.setLayout(gridPar);
	widthLabel = new Label("Width:");
	heightLabel = new Label("Height:");
	parHeightTextField = new TextField("20",5);
	parWidthTextField = new TextField("20",5);
	parPanel.add(widthLabel);
	parPanel.add(parWidthTextField);
	parPanel.add(heightLabel);
	parPanel.add(parHeightTextField);

	parSpeziellPanel = new Panel();
	gridSpeziellPar = new GridLayout(1,3,2,5);
	parSpeziellPanel.setLayout(gridSpeziellPar);
	neighbourhoodButton = new Button("Neighbourhood");
	neighbourhoodButton.setActionCommand("NEIGHBOURHOOD");
	neighbourhoodButton.addActionListener(this);
	neighbourhoodButton.setEnabled(false);
	statesButton = new Button("States");
	statesButton.setActionCommand("STATES");
	statesButton.addActionListener(this);
	statesButton.setEnabled(false);
	functionButton = new Button("Function");
	functionButton.setActionCommand("FUNCTION");
	functionButton.addActionListener(this);
	functionButton.setEnabled(false);
	parSpeziellPanel.add(neighbourhoodButton);
	parSpeziellPanel.add(statesButton);
	parSpeziellPanel.add(functionButton);

	buttonPanel = new Panel();
	gridButton = new GridLayout(1,2,2,5);
	buttonPanel.setLayout(gridButton);
	okButton = new Button("OK");
	okButton.setActionCommand("OK");
	okButton.addActionListener(this);
	cancelButton = new Button("Cancel");
	cancelButton.setActionCommand("CANCEL");
	cancelButton.addActionListener(this);
	buttonPanel.add(okButton);
	buttonPanel.add(cancelButton);

	setLayout(gridAll);
	add(choicePanel);
	add(parPanel);
	add(parSpeziellPanel);
	add(buttonPanel);

	addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent event) {
		control.cellularAutomatonCreated(null);
		setVisible(false);
		dispose();
	    }
	});
    }

    /**
     * Uebernimmt die Verwaltung der durch das Anwaehlen der Choice anstehenden
     * Veraenderungen.
     *
     * @param itemEvent      Ein ItemEvent, das von der Choice erzeugt wurde.
     */
    public void itemStateChanged(ItemEvent itemEvent) {	    

	int index = caChoice.getSelectedIndex();
	switch (index) {
	case 4:
	    functionButton.setEnabled(true);
	    statesButton.setEnabled(true);
	    neighbourhoodButton.setEnabled(true);
	    break;
	default:
	    functionButton.setEnabled(false);
	    statesButton.setEnabled(false);
	    neighbourhoodButton.setEnabled(false);
	}
    }
    
    /**
     * Uebernimmt die Verwaltung der durch die Mausklicks erzeugten Events.
     *
     * @param event       Ein ActionEvent, das von einem der Buttons erzeugt wurde.
     */
    public void actionPerformed(ActionEvent event) {

	String command = event.getActionCommand();
	if (command == "OK") {
	    try {
		String name = null;
		CellularAutomaton cellularAutomaton;
		BorderHandler borderHandler = null;
		Neighbourhood neighbourhood = null;
		StateChangeFunction stateChangeFunction = null;
		StateTable stateTable = null;
		int caIndex = caChoice.getSelectedIndex();
		int borderHandlerIndex = borderHandlerChoice.getSelectedIndex();
		Integer heightInteger = new Integer(parHeightTextField.getText());
		Integer widthInteger = new Integer(parWidthTextField.getText());
		int parHeight = heightInteger.intValue();
		int parWidth = widthInteger.intValue();
		switch (borderHandlerIndex) {
		case 0:
		    borderHandler = new TorusBorderHandler();
		    break;
		case 1:
		    borderHandler = new ReflexiveBorderHandler();
		    break;
		case 2:
		    borderHandler = new Constant0BorderHandler();
		    break;
		default:
		    borderHandler = new TorusBorderHandler();
		}
		switch (caIndex) {
		case 0:
		    name = "Life";
		    stateTable = new LifeStateTable();
		    neighbourhood = new MooreNeighbourhood1();
		    stateChangeFunction = new LifeStateChangeFunction();
		    break;
		case 1:
		    name = "Wireworld";
		    stateTable = new WireworldStateTable();
		    neighbourhood = new MooreNeighbourhood1();
		    stateChangeFunction = new WireworldStateChangeFunction();
		    break;
		case 2:
		    name = "Banks";
		    stateTable = new BanksStateTable();
		    neighbourhood = new VonNeumannNeighbourhood1();
		    stateChangeFunction = new BanksStateChangeFunction();
		    break;
		case 3:
		    name = "Modulo5";
		    stateTable = new Modulo5StateTable();
		    neighbourhood = new MooreNeighbourhood1();
		    stateChangeFunction = new Modulo5StateChangeFunction();
		    break;
		case 4:
		    name = "user defined";
		    stateTable = new LifeStateTable();
		    neighbourhood = new MooreNeighbourhood1();
		    stateChangeFunction = new LifeStateChangeFunction();
		    break;
		default:
		    name = "Life";
		    stateTable = new LifeStateTable();
		    neighbourhood = new MooreNeighbourhood1();
		    stateChangeFunction = new LifeStateChangeFunction();
		}
		cellularAutomaton = new CellularAutomaton(name, parHeight, parWidth, borderHandler, stateChangeFunction, stateTable, neighbourhood, 0, control);
		control.cellularAutomatonCreated(cellularAutomaton);
		setVisible(false);
		dispose();
	    }
	    catch (Exception e) {
		System.out.println("Fehler beim Erzeugen eines neuen Automaten!");
		System.exit(0);
	    }
	} else {
	    if (command == "CANCEL") {
		control.cellularAutomatonCreated(null);
		setVisible(false);
		dispose();
	    } else {
		if (command == "NEIGHBOURHOOD") {
		    control.displayMessage("Sorry, not yet implemented!");
		} else {
		    if (command == "STATES") {
			control.displayMessage("Sorry, not yet implemented!");
		    } else {
			if (command == "FUNCTION") {
			    control.displayMessage("Sorry, not yet implemented!");
			}
		    }
		}
	    }
	}
    }
}

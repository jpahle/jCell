package jCell;
import jCell.*;

import java.awt.*;
import java.awt.event.*;

public class CellularAutomatonFrame extends Frame {

    private Dimension size;

    public CellularAutomatonFrame(String name, Dimension maxSize) {
	super(name);
	size = maxSize;
    }

    public Dimension getMaximumSize() {
	return size;
    }

    public Dimension getPreferredSize() {
	return size;
    }
}

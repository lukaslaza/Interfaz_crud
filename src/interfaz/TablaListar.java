package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import database.funciones;

import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * TablaListar is just like SimpleTablaListar, except that it uses a custom
 * TableModel.
 */
public class TablaListar extends AbstractTableModel {
	private static final boolean DEBUG = false;
	private String[] columnNames;
	private Object[][] data;

	public TablaListar(Object[][] data, String[] columnNames) {
		this.columnNames = columnNames;
		this.data = data;
	}

	public TablaListar() {
		this.columnNames = funciones.getMetadatosTabla();
		this.data = funciones.getDatos();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for each
	 * cell. If we didn't implement this method, then the last column would contain
	 * text ("true"/"false"), rather than a check box.
	 */
	public Class getColumnClass(int c) {

		if (getValueAt(0, c) != null) {
			return getValueAt(0, c).getClass();
		} else {

			return "".getClass();
		}
	}

	/**
	 * Decides que columnas puede editar, por defecto la ultima de los checkBox es
	 * la unica que se puede editar
	 */
	public boolean isCellEditable(int row, int col) {
		if (col == getColumnCount() - 1) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Don't need to implement this method unless your table's data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
		if (DEBUG) {
			System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of "
					+ value.getClass() + ")");
		}

		data[row][col] = value;
		fireTableCellUpdated(row, col);

		if (DEBUG) {
			System.out.println("New value of data:");
			printDebugData();
		}
	}

	private void printDebugData() {
		int numRows = getRowCount();
		int numCols = getColumnCount();

		for (int i = 0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			for (int j = 0; j < numCols; j++) {
				System.out.print("  " + data[i][j]);
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}
}

package game;
/**
 * Conforma la matriz (arreglo de arreglos de enteros) que trabajan el juego.
 * @author Rodolfo Montaña
 * @version 1.0
 *
 */
public class Matrix {
	/**
	 * Entero. Cantidad de filas.
	 */
	private int rows;
	/**
	 * Emtero. Cantidad de columnas.
	 */
	private int columns;
	/**
	 * Entero. Minima cantidad requerida de filas.
	 */
	private final int minRows = 10;
	/**
	 * Entero. Minima cantida de columnas.
	 */
	private final int minColumns = 5;
	/**
	 * Arreglo de arreglos de enteros. Variablae donde se guarda la matriz creada.
	 */
	private int[][] gameMatrix;
	
	/**
	 * Constructor de la clase Matrix.
	 * @param rows Entero. Cantida de filas.
	 * @param columns Entero. Cantidad de columnas.
	 */
	public Matrix(int rows, int columns) {
		setRows(rows);
		setColumns(columns);
		setGameMatrix(this.rows, this.columns);		
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		//Si es menor al mínimo, se crea una matriz con los valores minimos.
		if(rows < minRows) this.rows = 10;
		else this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		//Si es menor al mínimo, se crea una matriz con los valores minimos.
		if(columns < minColumns) this.columns = 5;
		else this.columns = columns;
	}
	

	public int[][] getGameMatrix() {
		return gameMatrix;
	}

	public void setGameMatrix(int rows, int columns) {
		this.gameMatrix = new int[rows][columns];
		
		int counterX;
		int counterY;
		
		for(counterX = 0; counterX < rows; counterX++) {
			for(counterY = 0; counterY < columns; counterY++) {
				this.gameMatrix[counterX][counterY] = 0;				
			}
		}		
	}
	
	public int getBox(int row, int column) {
		if(row == -1 || column == -1) return -1;
		else return this.gameMatrix[row][column];
	}

	
	public void setBox(int row, int column, int number) {			
		if(row >= 0 && column >= 0) this.gameMatrix[row][column] = number;	
	}	
	
	public int getMinRows() {
		return minRows;
	}

	public int getMinColumns() {
		return minColumns;
	}
	public void printMatrix() {		
		int counterX;
		int counterY;
		
		for(counterX = 0; counterX < this.rows; counterX++) {
			for(counterY = 0; counterY < this.columns; counterY++) {
				System.out.print(this.gameMatrix[counterX][counterY] + " ");				
			}
			System.out.println();
		}		
	}
	/**
	 * Metodo que cambia todos los numeros dentro de la matriz a 0.
	 */
	public void cleanMatrix() {
		
		int counterX;
		int counterY;
		
		for(counterX = 0; counterX < this.rows; counterX++) {
			for(counterY = 0; counterY < this.columns; counterY++) {
				this.gameMatrix[counterX][counterY] = 0;				
			}
		}
	}
}

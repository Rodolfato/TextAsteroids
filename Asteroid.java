package game;
/**
 * Representa cada asteroide encontrado dentro del juego.
 * @author Rodolfo Montaña
 * @version 1.0
 */
public class Asteroid {
	/**
	 * Entero para el tamaño del asteroide.
	 */
	private int size;
	/**
	 * Entero para la direccion del asteroide, expresada en angulos.
	 */
	private int direction;
	/**
	 * Arreglo de 2 enteros para la posicion actual del asteroide.
	 */
	private int[] currentPosition = new int[2];
	/**
	 * Entero para la velocidad del asteroide.
	 */
	private int speed = 1;
	/**
	 * Arreglo de 8 enteros que guarda las direcciones validas que el asteroide puede poseer.
	 */
	public final int[] validDirections = new int[]{135, 90, 45, 180, 0, 225, 270, 315};
	
	/**
	 * Constructor de la clase Asteroid.
	 * Si el tamaño del asteroide es 0 o menor, se le asigna la posicion nula. (-1,-1).
	 * @param size Entero para el tamaño del asteroide.
	 * @param direction Entero para la direccion del asteroide.
	 * @param posX Entero para la posicion horizontal del asteroide; la fila en la cual se encuentra.
	 * @param posY Entero para la posicion vertical del asteroide; la columna en la cual se encuentra.
	 */
	public Asteroid(int size, int direction, int posX, int posY){
		
		int[] position = new int[] {posX, posY};
		int[] nuPos = new int[] {-1, -1};
		setSize(size);
		setDirection(direction);
		
		if(size <= 0) setCurrentPosition(nuPos);
		else setCurrentPosition(position);
	}
	/**
	 * Setter para ingresar la direccion del asteroide.
	 * La direccion ingresada es validada. Si la direccion ingresada no está dentro del arreglo de direcciones validas, se le da la direccion 0.
	 * @param direction Entero que representa la direccion del Asteroide.
	 */
	public void setDirection(int direction) {
		int found = 0;
		int counter;
		for(counter = 0; counter < validDirections.length; counter++) {
			//Si la dirección del asteroide esta dentro de las direcciones validas,
			//la variable found cambia a 1.
			if(validDirections[counter] == direction) {
				found = 1;	
			}
		}
		if(found == 1) {			
			this.direction = direction;			
		}
		else this.direction = 0;
	}
	/**
	 * Setter para ingresar el tamaño del asteroide.
	 * Si el tamaño ingresado del asteroide es menor o igual a 0, se le asigna el tamaño 0.
	 * @param size Entero. El tamaño del asteroide.
	 */
	public void setSize(int size) {
		//El tamaño del asteroide debe ser mayor a 0.
		if(size >= 0) this.size = size;
		//Si se le desea asignar un tamaño menor a 0, se le asigna el tamaño mínimo.
		else this.size = 0;
	}
	
	public void setCurrentPosition(int[] currentMiddlePosition) {
		this.currentPosition = currentMiddlePosition;
	}
	
	public int[] getCurrentPosition() {
		return currentPosition;
	}

	public int getSize() {
		return size;
	}

	public int getDirection() {
		return direction;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int[] getValidDirections() {
		return validDirections;
	}
	/**
	 * Metodo auxiliar para calcular la posicion siguiente del asteroide segun una posicion inicial entregada.
	 * Si la direccion ingresada saca al asteroide de las dimensiones establecidas por la matriz de juego, entonces el asteroide debe aparecer por la dimension contraria.
	 * De ese calculo se encarga este metodo.
	 * @param direction Entero. La direccion que se le desea aplicar al asteroide en cuestion.
	 * @param position Arreglo de enteros. La posicion del asteroide actualmente.
	 * @param maxRow Entero. El limite de las filas de la matriz de juego.
	 * @param maxCol Entero. El limite de las columnas de la matriz de juego.
	 * @return Retorna un arreglo que corresponde a la posicion calculada segun la direccion y posicion inicial entregada.
	 */
	public int[] moveAuxiliarElement(int direction, int[] position, int maxRow, int maxCol) {
		int[] finalPosition = new int[2];
		
		if(direction == 135) {
			if( ((position[0] - 1) < 0) && ((position[1] - 1) < 0) ) {
				finalPosition[0] = maxRow - 1; 
				finalPosition[1] = maxCol - 1;
			}
			if( ((position[0] - 1) < 0) && ((position[1] - 1) >= 0) ) {
				finalPosition[0] = maxRow - 1; 
				finalPosition[1] = position[1] - 1;
			}
			if( ((position[0] - 1) >= 0) && ((position[1] - 1) < 0) ) {
				finalPosition[0] = position[0] - 1; 
				finalPosition[1] = maxCol - 1;
			}
			if( ((position[0] - 1) >= 0) && ((position[1] - 1) >= 0) ) {
				finalPosition[0] = position[0] - 1; 
				finalPosition[1] = position[1] - 1;
			}			
		}		
		if(direction == 45) {
			if( ((position[0] - 1) < 0) && ((position[1] + 1) >=  maxCol) ) {
				finalPosition[0] = maxRow - 1; 
				finalPosition[1] = 0;
			}
			if( ((position[0] - 1) < 0) && ((position[1] + 1) < maxCol) ) {
				finalPosition[0] = maxRow - 1; 
				finalPosition[1] = position[1] + 1;
			}
			if( ((position[0] - 1) >= 0) && ((position[1] + 1) >= maxCol) ) {
				finalPosition[0] = position[0] - 1; 
				finalPosition[1] = 0;
			}
			if( ((position[0] - 1) >= 0) && ((position[1] + 1) < maxCol) ) {
				finalPosition[0] = position[0] - 1; 
				finalPosition[1] = position[1] + 1;
			}			
		}
		if(direction == 225) {
			if( ((position[0] + 1) >= maxRow) && ((position[1] - 1) <  0) ) {
				finalPosition[0] = 0; 
				finalPosition[1] = maxCol - 1;
			}
			if( ((position[0] + 1) >= maxRow) && ((position[1] - 1) >=  0) ) {
				finalPosition[0] = 0; 
				finalPosition[1] = position[1] - 1;
			}
			if( ((position[0] + 1) < maxRow) && ((position[1] - 1) <  0) ) {
				finalPosition[0] = position[0] + 1; 
				finalPosition[1] = maxCol - 1;
			}
			if( ((position[0] + 1) < maxRow) && ((position[1] - 1) >= 0) ) {
				finalPosition[0] = position[0] + 1; 
				finalPosition[1] = position[1] - 1;
			}			
		}
		if(direction == 315) {
			if( ((position[0] + 1) >= maxRow) && ((position[1] + 1) >=  maxCol) ) {
				finalPosition[0] = 0; 
				finalPosition[1] = 0;
			}
			if( ((position[0] + 1) >= maxRow) && ((position[1] + 1) < maxCol) ) {
				finalPosition[0] = 0; 
				finalPosition[1] = position[1] + 1;
			}
			if( ((position[0] + 1) < maxRow) && ((position[1] + 1) >= maxCol) ) {
				finalPosition[0] = position[0] + 1; 
				finalPosition[1] = 0;
			}
			if( ((position[0] + 1) < maxRow) && ((position[1] + 1) < maxCol) ) {
				finalPosition[0] = position[0] + 1; 
				finalPosition[1] = position[1] + 1;
			}			
		}		
		if(direction == 90) {
			if( (position[0] - 1) < 0 ) {
				finalPosition[0] = maxRow - 1;
				finalPosition[1] = position[1];
			}
			if( (position[0] - 1) >= 0 ) {
				finalPosition[0] = position[0] - 1;
				finalPosition[1] = position[1];
			}			
		}
		if(direction == 180) {
			if( (position[1] - 1) < 0 ) {
				finalPosition[0] = position[0];
				finalPosition[1] = maxCol - 1;
			}
			if( (position[1] - 1) >= 0 ) {
				finalPosition[0] = position[0];
				finalPosition[1] = position[1] - 1;
			}			
		}
		if(direction == 0) {
			if( (position[1] + 1) >= maxCol ) {
				finalPosition[0] = position[0];
				finalPosition[1] = 0;
			}
			if( (position[1] + 1) < maxCol ) {
				finalPosition[0] = position[0];
				finalPosition[1] = position[1] + 1;
			}			
		}
		if(direction == 270) {
			if( (position[0] + 1) >= maxRow ) {
				finalPosition[0] = 0;
				finalPosition[1] = position[1];
			}
			if( (position[0] + 1) < maxRow ) {
				finalPosition[0] = position[0] + 1;
				finalPosition[1] = position[1];
			}				
		}		
		return finalPosition;
	}
	/**
	 * Metodo que, junto a la posicion calculada por el metodo moveAuxiliarElement(), modifica la posicion del asteroide actual, segun el movimiento deseado.
	 * Dependiendo de la velocidad del asteroide, este metodo mueve su posicion cuantas veces dicte la velocidad del asteroide, utilizando un ciclo iterativo.
	 * @param direction Entero. La direccion en la cual se requiere mover el asteroide.
	 * @param position Arreglo de enteros. La posicion actual del asteroide.
	 * @param maxRow Entero. El limite de las filas de la matriz de juego.
	 * @param maxCol Entero. El limite de las columnas de la matriz del juego.
	 */
	public void moveElement(int direction, int[] position, int maxRow, int maxCol) {
		
		int moves;
		int[] nuPos = new int[] {-1, -1};		
		if(size <= 0) this.currentPosition = nuPos;
		else {
			for(moves = 0; moves < this.speed; moves++)	this.currentPosition = moveAuxiliarElement(direction, position, maxRow, maxCol);
		}
	}
	/**
	 * Metodo que muestra por pantalla los atributos del asteroide.
	 */
	public void printAsteroid() {		
		System.out.println("Size: " + this.size);
		System.out.println("Direction: " + this.direction);
		System.out.println("Current Pos X: " + this.currentPosition[0]);
		System.out.println("Current Pos Y: " + this.currentPosition[1]);
		System.out.println();
		
	}
}

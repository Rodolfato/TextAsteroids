package game;
/**
 * Modela las naves dentro del juego.
 * @author Rodolfo Montaña
 * @version 1.0
 */
public class SpaceShip {
	/**
	 * Entero. Velocidad de la nave.
	 */
	private int speed;
	/**
	 * Entero. Direccion en la cual la nave se está moviendo.
	 */
	private int direction;
	/**
	 * Entero. Direccion a la cual la nave está apuntando.
	 */
	private int futureDirection;
	/**
	 * Arreglo de enteros. Posicion actual de la nave
	 */
	private int[] currentPosition = new int[2];
	/**
	 * Arreglo de enteros. Direcciones validas que puede tomar la nave.
	 */
	public final int[] validDirections = new int[]{135, 90, 45, 180, 0, 225, 270, 315};
	/**
	 * Constructor de la  clase SpaceShip.
	 * @param speed Entero. Velocidad de la nave.
	 * @param direction Entero. Direcion de la nave.
	 * @param startPosX Entero. Posicion de la fila de la nave.
	 * @param startPosY Entero. Posicion de la columna de la nave.
	 */
	public SpaceShip(int speed, int direction, int startPosX, int startPosY) {
		int[] startPos = new int[] {startPosX, startPosY};
		setSpeed(speed);
		setDirection(direction);
		setCurrentPosition(startPos);
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDirection() {
		return direction;
	}
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
	
	public int getFutureDirection() {
		return futureDirection;
	}

	public void setFutureDirection(int futureDirection) {
		int found = 0;
		int counter;
		for(counter = 0; counter < validDirections.length; counter++) {
			//Si la dirección del asteroide esta dentro de las direcciones validas,
			//la variable found cambia a 1.
			if(validDirections[counter] == futureDirection) {
				found = 1;
				System.out.println("Future direction found: " + futureDirection);
			}
		}
		if(found == 1) {			
			this.futureDirection = futureDirection;			
		}
		else this.futureDirection = 0;
	}
	public int[] getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(int[] currentPosition) {
		this.currentPosition = currentPosition;
	}
	public int[] getValidDirections() {
		return validDirections;
	}
	/**
	 * Metodo auxiliar para calcular la posicion siguiente de la entidad segun una posicion inicial entregada.
	 * Si la direccion ingresada saca a la entidad de las dimensiones establecidas por la matriz de juego, entonces el asteroide debe aparecer por la dimension contraria.
	 * De ese calculo se encarga este metodo.
	 * @param direction Entero. La direccion que se le desea aplicar a la entidad en cuestion.
	 * @param position Arreglo de enteros. La posicion de la entidad actualmente.
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
	 * Metodo que, junto a la posicion calculada por el metodo moveAuxiliarElement(), modifica la posicion de la nave actual, segun el movimiento deseado.
	 * Dependiendo de la velocidad de la nave, este metodo mueve su posicion cuantas veces dicte la velocidad de la nave, utilizando un ciclo iterativo.
	 * @param direction Entero. La direccion en la cual se requiere mover la nave.
	 * @param position Arreglo de enteros. La posicion actual de la nave.
	 * @param maxRow Entero. El limite de las filas de la matriz de juego.
	 * @param maxCol Entero. El limite de las columnas de la matriz del juego.
	 */
	public void moveElement(int direction, int[] position, int maxRow, int maxCol) {
		int moves;
		for(moves = 0; moves < this.speed; moves++) this.currentPosition = moveAuxiliarElement(direction, position, maxRow, maxCol);
	}
	/**
	 * Metodo que muestra por pantalla los atributos de la nave.
	 */
	public void printSpaceShip() {
		System.out.println("Informacion sobre la nave.");
		System.out.println("Nombre: Player Ship");
		System.out.println("Direccion del movimiento: " + this.direction);
		System.out.println("Direccion a la cual esta apuntando: " + this.futureDirection);
		System.out.println("Velocidad: " + this.speed);
		System.out.println();		
	}
}

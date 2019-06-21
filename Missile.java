package game;
/**
 * Modela los misiles disparados por la nave dentro del juego.
 * @author Rodolfo Montaña
 * @version 1.0
 */
public class Missile {
	/**
	 * Entero. Alcance maximo del misil.
	 */
	 private int fireRange;
	 /**
	  * Arreglo de enteros. Posicion inicial del misil.
	  */
	 private int[] currentPosition = new int[2];
	 /**
	  * Entero. Direccion del misil.
	  */
	 private int direction;
	 /**
	  * Entero. Velocidad del misil.
	  */
	 private int speed;
	 /**
	  * Dirreciones validas del misil.
	  */
	 public final int[] validDirections = new int[]{135, 90, 45, 180, 0, 225, 270, 315};
	 /**
	  * Constructor de la clase misil.
	  * @param fireRange Entero. Alcance maximo del misil.
	  * @param firstPos Arreglo de enteros. Posicion inicial del misil.
	  * @param direction Entero. Direccion del misil.
	  * @param speed Entero. Velocidad del misil.
	  */
	 public Missile(int fireRange, int[] firstPos, int direction, int speed) {
		 setFireRange(fireRange);
		 setCurrentPosition(firstPos);
		 setDirection(direction);
		 setSpeed(speed);		 
	 }
	 
	public int getFireRange() {
		return fireRange;
	}

	public void setFireRange(int fireRange) {
		this.fireRange = fireRange;
	}

	public int[] getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int[] currentPosition) {
		int[] nullPos = new int[] {-1,-1};
		if(this.fireRange < 1) this.currentPosition = nullPos;
		else this.currentPosition = currentPosition;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = 1 + speed;
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
	 * Metodo que, junto a la posicion calculada por el metodo moveAuxiliarElement(), modifica la posicion del misil actual, segun el movimiento deseado.
	 * Dependiendo de la velocidad del misil, este metodo mueve su posicion cuantas veces dicte la velocidad del misil, utilizando un ciclo iterativo.
	 * @param direction Entero. La direccion en la cual se requiere mover el misil.
	 * @param position Arreglo de enteros. La posicion actual del misil.
	 * @param maxRow Entero. El limite de las filas de la matriz de juego.
	 * @param maxCol Entero. El limite de las columnas de la matriz del juego.
	 */	
	public void moveElement(int direction, int[] position, int maxRow, int maxCol) {
			int[] nullPos = new int[] {-1,-1};
			int moves;
			
			if(this.fireRange < 1) this.currentPosition = nullPos;
			else {
			for(moves = 0; moves < this.speed; moves++) this.currentPosition = moveAuxiliarElement(direction, position, maxRow, maxCol);
			
			this.fireRange--;
			}
		}


}

package game;
import java.util.Random;
import java.util.Arrays;
import java.lang.Math; 
/**
 * Representa, genera, guarda y modifica todo lo necesario para jugar una partida de Asteroids.
 * @author Rodolfo Montaña
 * @version 1.0
 */
public class GameSpace {
	/**
	 * Entero. La dificultad del juego. Varía entre 1 (Easy) y 4 (Impossible).
	 */
	private int difficulty;
	/**
	 * Entero. El estado actual del juego. 0 (Playing), 3 (Defeat por colision con asteroides), 13 (Defeat por colision con un misil), y 7 (Victory).
	 */
	private int gameState;
	/**
	 * Entero. El puntaje actual del jugador. Asteroide grande 3 puntos, asteroide mediano 2 puntos, y asteroide pequeño 1 punto.
	 */
	private int score;
	/**
	 * Entero. La cantidad de asteroides dentro del juego.
	 */
	private int quantityAsteroids;
	/**
	 * Entero. El maximo de la cantidad de particiones que pueden ser generadas al ser impactado un asteroide.
	 */
	private int partitions;
	/**
	 * Entero. El maximo alcance de los misiles.
	 */
	private int fireRange;
	/**
	 * Entero. La cantidad actual de asteroides particionados que fueron generados por colision.
	 */
	public int currentMaxAstPart = 0;
	/**
	 * Entero. La cantidad actual de misiles que fueron generados por el jugador.
	 */
	public int currentFiredMissiles = 0;
	/**
	 * Clase Matrix. El tablero (matriz) donde transcurre el juego.
	 */
	public Matrix gameBoard;
	/**
	 * Arreglo de Clase Asteroid. Todos los asteroides generados inicialmente.
	 */
	public Asteroid[] allAsteroids;
	/**
	 * Clase SpaceShip. La nave controlada por el jugador.
	 */
	public SpaceShip playerShip;
	/**
	 * Arreglo de Clase Asteroid. Los asteroides generados por colision entre ellos o con misiles.
	 */
	public Asteroid[] maxAsteroidPartitions;
	/**
	 * Arreglo de clase Missile. Todos los misiles disparados en el transcurso del juego.
	 */
	public Missile[] firedMissiles;
	/**
	 * Arreglo de enteros. Direcciones validas que las entidades dentro del juego pueden tomar.
	 */
	public final int[] validDirections = new int[]{135, 90, 45, 180, 0, 225, 270, 315};
	/**
	 * Clase Random. Para generar numeros aleatorios.
	 */
	public Random rand = new Random();
	
	/**
	 * Constructor de la clase GameSpace.
	 * Inicializa todos los elementos dentro del juego.
	 * La nave del jugador comienza en la mitad del tablero.
	 * @param difficulty Entero. La dificultad del juego. Varía entre 1 (Easy) y 4 (Impossible).
	 * @param gameState Entero. El estado en el cual el juego comienza, 1.
	 * @param score Entero. El score inicial del jugador; 0.
	 * @param quantityAsteroids Entero. La cantidad de asteroides iniciales.
	 * @param partitions Entero. La cantidad maxima de particiones que pueden ser generadas al colisionar asteroides.
	 * @param fireRange Entero. El alcance maximo de los misiles.
	 * @param rows Entero. La cantidad de filas que el tablero debe tener.
	 * @param columns Entero. La cantidad de columnas que el tablero debe tener.
	 */
	public GameSpace(int difficulty, int gameState, int score, int quantityAsteroids, int partitions, int fireRange, int rows, int columns){
		setDifficulty(difficulty);
		setGameState(gameState);
		setScore(score);
		setQuantityAsteroids(quantityAsteroids);
		setPartitions(partitions);
		setFireRange(fireRange);
		setGameBoard(rows, columns);
		setPlayerShip(0, 0, rows/2, columns/2);
		setAllAsteroids();
		setMaxAsteroidPartitions();
		setFiredMissiles();
		positionEntities();
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public int getGameState() {
		return gameState;
	}
	public void setGameState(int gameState) {
		this.gameState = gameState;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getQuantityAsteroids() {
		return quantityAsteroids;
	}
	public void setQuantityAsteroids(int quantityAsteroids) {
		this.quantityAsteroids = quantityAsteroids;
	}
	public int getPartitions() {
		return partitions;
	}
	public void setPartitions(int partitions) {
		this.partitions = partitions;
	}
	public int getFireRange() {
		return fireRange;
	}
	public void setFireRange(int fireRange) {
		this.fireRange = fireRange;
	}
	public Matrix getGameBoard() {
		return gameBoard;
	}
	public void setGameBoard(int rows, int columns) {
		this.gameBoard = new Matrix(rows, columns);
	}
	public Asteroid[] getAllAsteroids() {
		return allAsteroids;
	}
	/**
	 * Metodo que genera todos los asteroides iniciales dentro de la partida.
	 * Genera cada objeto Asteroid y lo va guardando en el arreglo allAsteroids.
	 * Las posiciones iniciales de cada asteroide son generadas aleatoriamente, con el cuidado que cada posicion no este ya ocupada por otra entidad (La nave u otro asteroide).
	 */
	public void setAllAsteroids() {
		this.allAsteroids = new Asteroid[this.quantityAsteroids];
		int outside;
		int inside;
		int posX;
		int posY;
		int dir;
		
		int rows = this.gameBoard.getRows();
		int columns = this.gameBoard.getColumns();
				
		for(outside = 0; outside < quantityAsteroids; outside++) {
			posX = rand.nextInt(rows);
			posY = rand.nextInt(columns);
			dir = rand.nextInt(7);
			this.allAsteroids[outside] = new Asteroid(3, validDirections[dir], posX, posY);
			if(outside > 0) {
				for(inside = outside - 1; inside > -1; inside--) {
					if(Arrays.equals(this.allAsteroids[outside].getCurrentPosition(), this.allAsteroids[inside].getCurrentPosition())){
						do{
							posX = rand.nextInt(rows);
							posY = rand.nextInt(columns);
							dir = rand.nextInt(7);
							this.allAsteroids[outside] = new Asteroid(3, validDirections[dir], posX, posY);							
						}while(Arrays.equals(this.allAsteroids[outside].getCurrentPosition(), this.allAsteroids[inside].getCurrentPosition()));						
					}					
				}			
			}					
		}
	}
	
	public SpaceShip getPlayerShip() {
		return playerShip;
	}
	/**
	 * Setter para la nave del jugador.
	 * @param speed Entero. La velocidad inicial del jugador.
	 * @param direction Entero. La direccion inicial de la nave.
	 * @param startPosX Entero. La posicion inicial en las filas.
	 * @param startPosY Entero. La posicion inicial en las columnas.
	 */
	public void setPlayerShip(int speed, int direction, int startPosX, int startPosY) {
		this.playerShip = new SpaceShip(speed, direction, startPosX, startPosY);
	}
	
	public Asteroid[] getMaxAsteroidPartitions() {
		return maxAsteroidPartitions;
	}
	/**
	 * Metodo que genera la cantidad maxima de particiones de asteroides que pueden ser generadas si TODOS los asteroides entran en colision.
	 * Al generar este numero, lo utiliza para darle el largo al arreglo que guarda todos los asteroides particionados por colision.
	 */
	public void setMaxAsteroidPartitions() {
		double doublePartitions = (double) partitions;
		double size = 3.0;
		double power = Math.pow(doublePartitions, size);
		int intPower = (int) power;
		
		this.maxAsteroidPartitions = new Asteroid[quantityAsteroids * intPower];
	}
	/**
	 * Metodo que posiciona todas las entidades u objetos que sean parte de la actual partida de Asteroids.
	 * Utilizando 3 ciclos, el metodo recorre todos los arreglos que poseen objetos que sean parte de la lista, obtiene sus posiciones y los posiciona dentro de la matriz de juego.
	 * Por ultimo posiciona la nave, obteniendo su posicion de la misma forma.
	 */
	public void positionEntities() {
		int counter;
		for(counter = 0; counter < this.allAsteroids.length; counter++) {
			this.gameBoard.setBox(this.allAsteroids[counter].getCurrentPosition()[0], this.allAsteroids[counter].getCurrentPosition()[1], this.allAsteroids[counter].getSize());
		}		
		for(counter = 0; counter < this.currentMaxAstPart; counter++) {
			this.gameBoard.setBox(this.maxAsteroidPartitions[counter].getCurrentPosition()[0], this.maxAsteroidPartitions[counter].getCurrentPosition()[1], this.maxAsteroidPartitions[counter].getSize());
		}
		for(counter = 0; counter < this.currentFiredMissiles; counter++) {
			this.gameBoard.setBox(this.firedMissiles[counter].getCurrentPosition()[0], this.firedMissiles[counter].getCurrentPosition()[1], 13);
		}
		this.gameBoard.setBox(this.playerShip.getCurrentPosition()[0], this.playerShip.getCurrentPosition()[1], 7);
	}
	/**
	 * Metodo simple que, utilizando el metodo pertenecinete a la clase Matrix, elimina todos los objetos posicionados en el tablero actual.
	 */
	public void cleanBoard() {		
		this.gameBoard.cleanMatrix();		
	}	
	/**
	 * Metodo que mueve todos los asteroides, cambiando sus posiciones individualmente.
	 * Utilizando dos ciclos, recorre los dos arreglos que poseen objetos Asteroid y, de acuerdo a su direccion, velocidad y posicion, cambia sus posiciones.
	 */
	public void moveAllAsteroids() {
		int counter;
		for(counter = 0; counter < this.quantityAsteroids; counter++) {
			this.getAllAsteroids()[counter].moveElement(this.getAllAsteroids()[counter].getDirection(), this.getAllAsteroids()[counter].getCurrentPosition(), this.gameBoard.getRows(), this.gameBoard.getColumns());
		}
		for(counter = 0; counter < this.currentMaxAstPart; counter++) {
			this.maxAsteroidPartitions[counter].moveElement(this.maxAsteroidPartitions[counter].getDirection(), this.maxAsteroidPartitions[counter].getCurrentPosition(), this.gameBoard.getRows(), this.gameBoard.getColumns());
		}
	}
	/**
	 * Metodo que cambia la direccion a la cual la nave esta mirando.
	 * @param direction Entero. Direccion nueva de la nave.
	 */
	public void changeShipDirection(int direction) {		
		this.playerShip.setFutureDirection(direction);
	}
	/**
	 * Metodo que mueve la nave, cambiando la posicion dentro del objeto.
	 * Accede al objeto playerShip y cambia su posicion de acuerdo a su direccion y velocidad, y posicion.
	 */
	public void moveShip() {
		this.playerShip.moveElement(this.playerShip.getDirection(), this.playerShip.getCurrentPosition(), this.gameBoard.getRows(), this.gameBoard.getColumns());
	}
	/**
	 * Metodo que, a su vez, llama al metodo que mueve la nave perteneciente a la clase SpaceShip.
	 * Dependiendo de cuanto sea la aceleración, este método llamará al metodo moveShip, que a su vez cambia la posición de la nave de acuerdo a su velocidad.
	 * @param accel Entero. La aceleración que cambiara la ubicacion de la nave.
	 */
	public void accelerateShip(int accel) {
		if(this.playerShip.getDirection() != this.playerShip.getFutureDirection()) this.playerShip.setSpeed(1);
		this.playerShip.setDirection(this.playerShip.getFutureDirection());
		if(this.playerShip.getSpeed() == 0) this.playerShip.setSpeed(1);
		int moves;
		for(moves = 0; moves < accel; moves++) {
			moveShip();
		}
		this.playerShip.setSpeed(accel * this.playerShip.getSpeed());
	}
	/**
	 * Metodo que muestra por pantalla todos los atributos de todos los asteroides.
	 */
	public void printAsteroids() {
		int counter;
		for(counter = 0; counter < this.quantityAsteroids; counter++) {
			this.getAllAsteroids()[counter].printAsteroid();
		}
		for(counter = 0; counter < this.currentMaxAstPart; counter++) {
			this.maxAsteroidPartitions[counter].printAsteroid();
		}
	}	
	public void printSpaceShip() {
		this.playerShip.printSpaceShip();
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
	 * Metodo que busca un posicion libre para colocar una particion de un asteroide que entró en colisión.
	 * Esta posicion buscada debe estar libre. 
	 * Para esto, mediante el uso de un random, elige una direccion aleatoria entre las direcciones validas.
	 * Revisa si es posible poner el asteroide en la posicion de esa direccion.
	 * Sigue aleatorizando numeros hasta que una direccion da una posicion valida.
	 * @param position Arreglo de enteros. La posicion actual del asteroide destruido.
	 * @return Arreglo de enteros. Retorna la posicion encontrada libre donde es posible colocar el pedazo de asteroide.
	 */
	public int[] moveCollisionedAsteroid(int[] position) {
		int found = 0;
		int random;
		int[] probablePos = new int[2];
		int[] foundPos = new int[2];
		
		while(found == 0) {
			random = rand.nextInt(validDirections.length);
			probablePos = moveAuxiliarElement(validDirections[random], position, this.gameBoard.getRows(), this.gameBoard.getColumns());
			if(this.gameBoard.getBox(probablePos[0], probablePos[1]) == 0) {
				found = 1;
				foundPos = probablePos;
			}			
		}
		return foundPos;		
	}
	/**
	 * Metodo que, una vez obtenida la posicion adecuada donde colocar la fraccion de asteroide despues de ser destruido, crea el nuevo objeto del asteroide y lo guarda en el arreglo de asteroides particionados.
	 * @param originalAsteroid Clase Asteroid. El objeto del asteroide que colisiono y fue destruido, generando particiones de asteroides.
	 */
	public void createAsteroidPartitions(Asteroid originalAsteroid) {
		int numberPart;
		int counter;
		int[] positionNewAsteroid = new int[2];
		int[] noPosition = new int[] {-1,-1};
		int randomDirection = validDirections[rand.nextInt(8)];
		numberPart = rand.nextInt(this.partitions) + 1;
		for(counter = 0; counter < numberPart; counter++) {
			positionNewAsteroid = moveCollisionedAsteroid(originalAsteroid.getCurrentPosition());
			this.maxAsteroidPartitions[this.currentMaxAstPart] = new Asteroid(originalAsteroid.getSize() - 1, randomDirection , positionNewAsteroid[0], positionNewAsteroid[1]);
			this.currentMaxAstPart++;
		}
		originalAsteroid.setSize(0);
		originalAsteroid.setCurrentPosition(noPosition);
	}
	/**
	 * Metodo que utiliza varios ciclos iterativos para revisar TODAS las listas que contienen objetos asteroides, comparar sus posiciones (utilizando el metodo Arrays.equals()) y ver si han entrado en colision.
	 * Revisa tambien si la nave ha entrado en colision con los asteroides.
	 * Si existe colision, entrega la posicion nula a los asteroides destruidos y crea los nuevos asteroides como particiones utilizando createAsteroidPartitions().
	 * Si existe colision entre la nave y el asteroide, modifica el atributo gameState a 3; la partida ha sido perdida.
	 */
	public void checkForAsteroidsCollision() {
		int fixedCounter;
		int movingCounter;
		
		for(fixedCounter = 0; fixedCounter < this.allAsteroids.length; fixedCounter++) {
			for(movingCounter = fixedCounter + 1; movingCounter < this.allAsteroids.length; movingCounter++) {
				if(Arrays.equals(this.allAsteroids[fixedCounter].getCurrentPosition(), this.allAsteroids[movingCounter].getCurrentPosition()) && 
								this.allAsteroids[fixedCounter].getCurrentPosition()[0] != -1 &&
								this.allAsteroids[fixedCounter].getCurrentPosition()[1] != -1 &&
								this.allAsteroids[movingCounter].getCurrentPosition()[0] != -1 &&
								this.allAsteroids[movingCounter].getCurrentPosition()[1] != -1) {
					createAsteroidPartitions(this.allAsteroids[fixedCounter]);
					createAsteroidPartitions(this.allAsteroids[movingCounter]);
				}
			}
		}
		
		for(fixedCounter = 0; fixedCounter < this.currentMaxAstPart; fixedCounter++) {
			for(movingCounter = fixedCounter + 1; movingCounter < this.currentMaxAstPart; movingCounter++) {
				if(Arrays.equals(this.maxAsteroidPartitions[fixedCounter].getCurrentPosition(), this.maxAsteroidPartitions[movingCounter].getCurrentPosition()) && 
								this.maxAsteroidPartitions[fixedCounter].getCurrentPosition()[0] != -1 &&
								this.maxAsteroidPartitions[fixedCounter].getCurrentPosition()[1] != -1 &&
								this.maxAsteroidPartitions[movingCounter].getCurrentPosition()[0] != -1 &&
								this.maxAsteroidPartitions[movingCounter].getCurrentPosition()[1] != -1) {
					createAsteroidPartitions(this.maxAsteroidPartitions[fixedCounter]);
					createAsteroidPartitions(this.maxAsteroidPartitions[movingCounter]);
				}
			}
		}
		
		for(fixedCounter = 0; fixedCounter < this.allAsteroids.length; fixedCounter++) {
			for(movingCounter = 0; movingCounter < this.currentMaxAstPart; movingCounter++) {
				if(Arrays.equals(this.allAsteroids[fixedCounter].getCurrentPosition(), this.maxAsteroidPartitions[movingCounter].getCurrentPosition()) && 
								this.allAsteroids[fixedCounter].getCurrentPosition()[0] != -1 &&
								this.allAsteroids[fixedCounter].getCurrentPosition()[1] != -1 &&
								this.maxAsteroidPartitions[movingCounter].getCurrentPosition()[0] != -1 &&
								this.maxAsteroidPartitions[movingCounter].getCurrentPosition()[1] != -1) {
					createAsteroidPartitions(this.maxAsteroidPartitions[movingCounter]);
				}
			}
		}
		
		for(movingCounter = 0; movingCounter < this.currentMaxAstPart; movingCounter++) {
			if(Arrays.equals(this.playerShip.getCurrentPosition(), this.maxAsteroidPartitions[movingCounter].getCurrentPosition()) && 
							this.playerShip.getCurrentPosition()[0] != -1 &&
							this.playerShip.getCurrentPosition()[1] != -1 &&
							this.maxAsteroidPartitions[movingCounter].getCurrentPosition()[0] != -1 &&
							this.maxAsteroidPartitions[movingCounter].getCurrentPosition()[1] != -1) {
				this.gameState = 3;
			}
		}
		
		for(movingCounter = 0; movingCounter < this.allAsteroids.length; movingCounter++) {
			if(Arrays.equals(this.playerShip.getCurrentPosition(), this.allAsteroids[movingCounter].getCurrentPosition()) && 
							this.playerShip.getCurrentPosition()[0] != -1 &&
							this.playerShip.getCurrentPosition()[1] != -1 &&
							this.allAsteroids[movingCounter].getCurrentPosition()[0] != -1 &&
							this.allAsteroids[movingCounter].getCurrentPosition()[1] != -1) {
				this.gameState = 3;
			}
		}
	}
	/**
	 * Metodo que compara las posiciones de los misiles con la posicion de todos los asteroides.
	 * Si existe colision, crea las particiones de los asteroides adecuados, como tambien elimina los misiles que han entrado en contacto.
	 * Si exsite colision con la nave, el jugador pierde; gameState = 13.
	 */
	public void checkForShotAsteroids() {
		
		int fixedCounter;
		int movingCounter;
		//Se revisan los asteroides iniciales.
		for(fixedCounter = 0; fixedCounter < this.currentFiredMissiles; fixedCounter++) {
			for(movingCounter = 0; movingCounter < this.allAsteroids.length; movingCounter++) {
				if(Arrays.equals(this.firedMissiles[fixedCounter].getCurrentPosition(), this.allAsteroids[movingCounter].getCurrentPosition()) && 
								this.firedMissiles[fixedCounter].getCurrentPosition()[0] != -1 &&
								this.firedMissiles[fixedCounter].getCurrentPosition()[1] != -1 &&
								this.allAsteroids[movingCounter].getCurrentPosition()[0] != -1 &&
								this.allAsteroids[movingCounter].getCurrentPosition()[1] != -1) {
					this.score += this.allAsteroids[movingCounter].getSize();
					createAsteroidPartitions(this.allAsteroids[movingCounter]);
					this.firedMissiles[fixedCounter].getCurrentPosition()[0] = -1;
					this.firedMissiles[fixedCounter].getCurrentPosition()[1] = -1;					
				}
			}
		}
		//Se revisan los asteroides particionados.
		for(fixedCounter = 0; fixedCounter < this.currentFiredMissiles; fixedCounter++) {
			for(movingCounter = 0; movingCounter < this.currentMaxAstPart; movingCounter++) {
				if(Arrays.equals(this.firedMissiles[fixedCounter].getCurrentPosition(), this.maxAsteroidPartitions[movingCounter].getCurrentPosition()) && 
								this.firedMissiles[fixedCounter].getCurrentPosition()[0] != -1 &&
								this.firedMissiles[fixedCounter].getCurrentPosition()[1] != -1 &&
								this.maxAsteroidPartitions[movingCounter].getCurrentPosition()[0] != -1 &&
								this.maxAsteroidPartitions[movingCounter].getCurrentPosition()[1] != -1) {
					this.score += this.maxAsteroidPartitions[movingCounter].getSize();
					createAsteroidPartitions(this.maxAsteroidPartitions[movingCounter]);
					this.firedMissiles[fixedCounter].getCurrentPosition()[0] = -1;
					this.firedMissiles[fixedCounter].getCurrentPosition()[1] = -1;
					
				}
			}
		}
		
		for(movingCounter = 0; movingCounter < this.currentFiredMissiles; movingCounter++) {
			if(Arrays.equals(this.firedMissiles[movingCounter].getCurrentPosition(), this.playerShip.getCurrentPosition()) && 
							this.firedMissiles[movingCounter].getCurrentPosition()[0] != -1 &&
							this.firedMissiles[movingCounter].getCurrentPosition()[1] != -1 &&
							this.playerShip.getCurrentPosition()[0] != -1 &&
							this.playerShip.getCurrentPosition()[1] != -1) {
				gameState = 13;				
			}
		}
	}
	
	public Missile[] getFiredMissiles() {
		return firedMissiles;
	}
	
	/**
	 * Metodo que genera el arreglo donde se guardan los misiles disparados.
	 * La cantidad maxima de misiles disparados es la cantidad total de asteroides posibles generados, multiplicados por 100.
	 */
	public void setFiredMissiles() {
		double doublePartitions = (double) partitions;
		double size = 3.0;
		double power = Math.pow(doublePartitions, size);
		int intPower = (int) power;
		
		this.firedMissiles = new Missile[quantityAsteroids * intPower * 100];
	}
	/**
	 * Metodo que dispara misiles desde la nave.
	 * Obtiene la direccion y velocidad de la nave.
	 * Utilizando la direccion y velocidad obtenidas, genera la posicion inicial del misil.
	 * La velocidad del misil es la velocidad de la nave.
	 * La direccion del misil es la direccion en la cual la nave esta apuntando. 
	 */
	public void fireMissile() {
		
		int[] inicialPos;
		int[] shipPos;
		int speed;
		int counter;
		
		shipPos = this.playerShip.getCurrentPosition();
		inicialPos = shipPos;
		speed = this.playerShip.getSpeed();
		
		for(counter = 0; counter <= speed; counter++) {
			inicialPos = moveAuxiliarElement(this.playerShip.getFutureDirection(), inicialPos, this.gameBoard.getRows(), this.gameBoard.getColumns());			
		}
		
		this.firedMissiles[this.currentFiredMissiles] = new Missile(this.fireRange, inicialPos, this.playerShip.getFutureDirection(), this.playerShip.getSpeed());
		this.currentFiredMissiles++;
	}
	/**
	 * Metodo que mueve todos los misiles en la direccion adecuada.
	 */
	public void moveAllMissiles() {		
		int counter;
		for(counter = 0; counter < this.currentFiredMissiles; counter++) {
			this.firedMissiles[counter].moveElement(this.firedMissiles[counter].getDirection(), this.firedMissiles[counter].getCurrentPosition(), this.gameBoard.getRows(), this.gameBoard.getColumns());
		}
	}
	/**
	 * Metodo que revisa toda la matriz de juego para ver si el jugador ganó la partida o si el juego sigue.
	 */
	public void checkForTheWin() {
		int counterX;
		int counterY;
		int found = 0;
		
		for(counterX = 0; counterX < this.gameBoard.getRows(); counterX++) {
			for(counterY = 0; counterY < this.gameBoard.getColumns(); counterY++) {
				if(this.gameBoard.getBox(counterX, counterY) == 3 || 
				this.gameBoard.getBox(counterX, counterY) == 2 || 
				this.gameBoard.getBox(counterX, counterY) == 1){
					found = 1;				
				}				
			}
		}
		
		if(found == 0) this.gameState = 7;
	}
	/**
	 * Metodo que transforma el tablero en un string muy largo para ser mostrado por pantalla.
	 * @return Clase String. String largo que contiene todo el tablero del juego.
	 */
	public String textifySpace() {
	
		String finalString = "";
		String bordeSuperiorEInferior = " ";
		

		int counterX;
		int counterY;
		
		for(counterY = 0; counterY < this.gameBoard.getColumns(); counterY++) {
			bordeSuperiorEInferior += "___";
			
		}
		
		bordeSuperiorEInferior += "\n";
		
		finalString += bordeSuperiorEInferior;
		
		for(counterX = 0; counterX < this.gameBoard.getRows(); counterX++) {
			finalString += "|";
			for(counterY = 0; counterY < this.gameBoard.getColumns(); counterY++) {
				if(this.gameBoard.getBox(counterX, counterY) == 0) finalString += ("   ");
				if(this.gameBoard.getBox(counterX, counterY) == 1) finalString += (" . ");
				if(this.gameBoard.getBox(counterX, counterY) == 2) finalString += (" O ");
				if(this.gameBoard.getBox(counterX, counterY) == 3) finalString += ("( )");
				if(this.gameBoard.getBox(counterX, counterY) == 7) finalString += (" N ");
				if(this.gameBoard.getBox(counterX, counterY) == 13) finalString += (" * ");					
			}
			finalString += "|";
			finalString += ("\n");
		}
		
		finalString += bordeSuperiorEInferior;
	
		return finalString;
		
	}
}

package game;
import java.util.Scanner;
import java.util.Random;
/**
 * Clase que pone en marcha el juego.
 * @author Rodolfo Montaña
 * @version 1.0
 * 
 */

public class Gaming {
	/**
	 * Entero. Dificultad del juego.
	 */
	public int difficulty;
	/**
	 * Clase GameSpace. El juego actual y todas sus características es guardado en esta variable.
	 */
	public GameSpace currentGame;
	/**
	 * Clase Random. Para generar numeros aleatorios.
	 */
	public Random rand = new Random();
	/**
	 * Clase Scanner. Para ingresar desde el usuario a traves del teclado.
	 */
	public Scanner reader = new Scanner(System.in);
	/**
	 * Arreglo de enteros. Direcciones validas para las entidades dentro del juego.
	 */
	public final int[] validDirections = new int[]{135, 90, 45, 180, 0, 225, 270, 315};
	/**
	 * Clase String. String muy largo que contiene la matriz de juego, para ser impresa.
	 */
	public String textifiedSpace;

	/**
	 * Constructor de la clase Gaming.
	 */
	public Gaming() {
		
	}
	/**
	 * Metodo createSpace. Segun datos entregados, se crea el espacio de juego con todas sus entidades puestas, instanciando la clase GameSpace.
	 * @param N Entero. Cantidad de filas.
	 * @param M Entero. Cantidad de columnas.
	 * @param numAsteroids Entero. Cantidad de asteroides inciales.
	 * @param numPartitions Entero. Cantidad de particiones maxima al entrar en colisón un asteroide.
	 * @param fireRange Entero. Alcance máximo de los misiles disparados.
	 */
	public void createSpace(int N, int M, int numAsteroids, int numPartitions, int fireRange) {
		this.currentGame = new GameSpace(1, 1, 0, numAsteroids, numPartitions, fireRange, N, M);		
	}
	
	public void displayMoves() {
		
		System.out.println();
		System.out.println("Puede elegir entre los siguientes angulos . . .");
		System.out.println(" _________________");
		System.out.println("|                 |");
		System.out.println("| 135    90    45 |");
		System.out.println("|                 |");
		System.out.println("|                 |");
		System.out.println("| 180    N     0  |");
		System.out.println("|                 |");
		System.out.println("|                 |");
		System.out.println("| 225   270   315 |");
		System.out.println("|_________________|");
		System.out.println();
		System.out.print("Por favor realizar una eleccion:" + "\n" + ">> ");
	}
	
	public void displayOnlyMoves() {
		System.out.println();
		System.out.println("Direcciones:");
		System.out.println(" _________________");
		System.out.println("|                 |");
		System.out.println("| 135    90    45 |");
		System.out.println("|                 |");
		System.out.println("|                 |");
		System.out.println("| 180    N     0  |");
		System.out.println("|                 |");
		System.out.println("|                 |");
		System.out.println("| 225   270   315 |");
		System.out.println("|_________________|");
		System.out.println();
		
	}
	
	public void displayAccelOptions() {
		
		System.out.println("Cuanto desea acelerar . . . ");
		System.out.println("1. Aceleracion Minima");
		System.out.println("2. Aceleracion Media");
		System.out.println("3. Aceleracion Maxima");
		System.out.println("4. Inicializar > > > EPSTEIN JUMP DRIVE < < <");
		System.out.print("Por favor realizar una eleccion:" + "\n" + ">> ");
	}
	
	public void whatIsJumpDrive() {
		System.out.println("El > > > EPSTEIN JUMP DRIVE < < < es una nueva tecnologia creada por el cientifico originario de Marte, Solomon Epstein.");
		System.out.println("Todavia en etapas tempranas de su desarrollo el > > > EPSTEIN JUMP DRIVE < < < genera un movimiento de entre 2*X y 10*X, donde 'X' es la maxima aceleracion de la nave, en forma aleatoria.");
		System.out.println("Usar con precaucion.");
	}
	
	public void displayMenu() {
		
		System.out.println();
		System.out.println("Puede elegir entre las siguientes jugadas . . .");
		System.out.println("1. Acelerar la nave en la direccion actual.");
		System.out.println("2. Cambiar la direccion de la nave.");
		System.out.println("3. Disparar un proyectil en la direccion actual.");
		System.out.println("4. Hacer nada.");
		System.out.println();
		System.out.print("Por favor realizar una eleccion:" + "\n" + ">> ");
	}
	
	public void displayStartingMenu() {		
		System.out.println("BIENVENIDO A ASTEROIDS");
		System.out.println("1. Comenzar partida.\n2. Informacion acerca del juego.\n3. Salir.");
	}
	
	public void displayAboutGame() {
		System.out.println("La presente version de Asteroids es un juego de texto plano basado en el popular juego arcade del mismo nombre.");
		System.out.println("El objetivo es destruir todos los asteroides que rodean el espacio de juego con la ayuda de misiles disparados desde la nave controlada por el jugador.");
		System.out.println("El jugador pierde la partida cuando la nave entra en contacto con cualquier asteroide o tambien con su propio misil.");
		System.out.println("Los bordes del juego estan unidos, es decir, al llegar al final de uno de los lados, se aparece en el lado opuesto.");
		System.out.println();

		System.out.println("Sobre los asteroides:");
		System.out.println("\tLos asteroides al entrar en contacto entre ellos o con un misil, se separan en particiones aleatorias de menor tamaño, la cantidad de estas dadas por la dificultad del juego escogida.");
		System.out.println("\tCada asteroide necesita 3 disparos en total para ser destruido por completo.");
		System.out.println("\tLos asteroides vienen en 3 tamaños distintos: Grande '( )', mediano 'O', y pequeño '.' ");
		System.out.println();

		System.out.println("Sobre el movimiento de la nave:");
		System.out.println("\tLa nave se mueve a una velocidad constante 'S' a menos que esta sea afectada por la aceleracion de esta misma, por orden del jugador");
		System.out.println("\tCada nivel de aceleracion multiplica por un factor la velocidad de la nave.");
		System.out.println("\tAceleracion minima: Multiplica a la velocidad de la nave por 2.");
		System.out.println("\tAceleracion media: Multuplica a la velocidad de la nave por 3.");
		System.out.println("\tAceleracion maxima: Multiplica a la velocidad de la nave por 4.");
		System.out.println("\tLa nave se seguira moviendo en la direccion actual a menos que se escoga una direccion distinta y se acelere en esa direccion.");
		System.out.println("\tSi se cambia de direccion y se acelera en la nueva direccion, la nave reduce su velocidad a 1.");
		System.out.println("\tEn general el juego respeta la fisica del vacio en el espacio.");
		System.out.println();
		whatIsJumpDrive();
		System.out.println();
	}
	
	public void displayDifficulty() {
		System.out.println("1. Dificultad Fundamentos de Programacion (Easy): \n");
		System.out.println("\tEspacio de tamaño 10x10.");
		System.out.println("\tAsteroides: 4");
		System.out.println("\tAlcance maximo de los misiles: 5");
		System.out.println("\tParticiones maxima de los asteroides al estrellarse: 2");
		System.out.println();
		System.out.println("2. Dificultad Metodos de Programacion (Normal): \n");
		System.out.println("\tEspacio de tamaño 15x15.");
		System.out.println("\tAsteroides: 6");
		System.out.println("\tAlcance maximo de los misiles: 4");
		System.out.println("\tParticiones maxima de los asteroides al estrellarse: 3");
		System.out.println();
		System.out.println("3. Dificultad Paradigmas de Programacion (Hard): \n");
		System.out.println("\tEspacio de tamaño 20x20.");
		System.out.println("\tAsteroides: 8");
		System.out.println("\tAlcance maximo de los misiles: 3");
		System.out.println("\tParticiones maxima de los asteroides al estrellarse: 4");
		System.out.println();
		System.out.println("4. Dificultad Estudiar en Chile y terminar trabajando en el Silicon Valley (Impossible): \n");
		System.out.println("\tEspacio de tamaño 30x30.");
		System.out.println("\tAsteroides: 15");
		System.out.println("\tAlcance maximo de los misiles: 2");
		System.out.println("\tParticiones maxima de los asteroides al estrellarse: 5");
		System.out.println("Elegir una opcion:\n>> ");
	}
	
	public String getTextifiedSpace() {
		return textifiedSpace;
	}

	public void setTextifiedSpace(String textifiedSpace) {
		this.textifiedSpace = textifiedSpace;
	}
	
	public void printSpace() {
		System.out.println(this.textifiedSpace);
	}
	/**
	 * Dependiendo del atributo gameState que tenga dentro el objeto currentGame, se retorna el string adecuado.
	 * 7 for VICTORY
	 * 3 ó 13 for DEFEAT
	 * Si no: PLAYING
	 * @return String. Con el estado del juego.
	 */
	public String checkState() {
		
		String win = " \n V I C T O R Y \n ";
		String lost = " \n D E F E A T \n ";
		String keep = " \n P L A Y I N G \n ";
		
		if(currentGame.getGameState() == 7) return win;
		if(currentGame.getGameState() == 13 || currentGame.getGameState() == 3) return lost;
		else return keep;
	}
	/**
	 * Metodo gameLoop. Este metodo hace entrar en marcha el juego en el metodo main de la clase Gaming.
	 */
	public void gameLoop() {
		
		int condition = 1;
		int choice;
		int dirChange;
		int accel;
		int eps;
		int optionFromMenu = 0;
		int difficulty = 1;
		
		while(optionFromMenu == 0) {
			
			displayStartingMenu();
			System.out.println("Elegir una opcion:\n>> ");
			optionFromMenu = reader.nextInt();
			while(optionFromMenu > 3 || optionFromMenu < 1) {
				displayStartingMenu();
				System.out.print("Opcion invalida. Por favor elegir una opcion valida. \n>> ");
				optionFromMenu = reader.nextInt();
			}
			if(optionFromMenu == 1 ) {
				displayDifficulty();
				difficulty = reader.nextInt();
				while(difficulty > 4 || difficulty < 1) {
					displayDifficulty();
					System.out.print("Opcion invalida. Por favor elegir una opcion valida. \n>> ");
					difficulty = reader.nextInt();
				}				
			}
			if(optionFromMenu == 2) {
				displayAboutGame();
				optionFromMenu = 0;
			}
			if(optionFromMenu == 3) System.exit(0);
		}		
		
		if(difficulty == 1) createSpace(10, 10, 4, 2, 5);
		if(difficulty == 2) createSpace(15, 15, 6, 3, 4);
		if(difficulty == 3) createSpace(20, 20, 8, 4, 3);
		if(difficulty == 4) createSpace(30, 30, 15, 5, 2);
		
		currentGame.setDifficulty(difficulty);		
		setTextifiedSpace(currentGame.textifySpace());
		System.out.println(checkState());
		printSpace();
		
		currentGame.printSpaceShip();
		System.out.println("Puntaje actual: " + currentGame.getScore());
		displayOnlyMoves();
		while(condition == 1) {
			
			
			if(currentGame.getGameState() == 7) {
				System.out.println("V I C T O R Y");
				System.out.println("GANO la partida!");
				System.out.println("Puntaje final: " + currentGame.getScore());
				do {
					System.out.print("Ingrese el numero 777 para salir del programa:\n>> ");
					condition = reader.nextInt();
					
				}while(condition != 777);				
			}
			
			if(currentGame.getGameState() == 3 || currentGame.getGameState() == 13) {
				System.out.println("D E F E A T ");
				System.out.println("PERDIO la partida!");
				System.out.println("Puntaje final: " + currentGame.getScore());
				do {
					System.out.print("Ingrese el numero 777 para salir del programa:\n>> ");
					condition = reader.nextInt();
					
				}while(condition != 777);				
			}
			
			
			displayMenu();
			choice = reader.nextInt();
			while(choice > 4 || choice < 1) {
				System.out.println("Opcion invalida. Por favor elegir una opcion valida.");
				displayMenu();
				choice = reader.nextInt();
			}
			
			if(choice == 1) {
				displayAccelOptions();
				accel = reader.nextInt();
				while(accel > 5 || accel < 1) {
					System.out.println("Opcion invalida. Por favor elegir una opcion valida.");
					displayAccelOptions();
					accel = reader.nextInt();
				}
				if(accel == 1) currentGame.accelerateShip(2);
				if(accel == 2) currentGame.accelerateShip(3);
				if(accel == 3) currentGame.accelerateShip(4);
				if(accel == 4) {
					
					eps = rand.nextInt(10) + 2;
					System.out.println("> > > EPSTEIN JUMP DRIVE < < < INICIALIZADO");
					System.out.println("ACELERACION OBTENIDA: " + eps * 4 + " cuadros/jugada^2");
					currentGame.accelerateShip(eps * 4);
				}
				
			}
			if(choice == 2) {
				displayMoves();
				dirChange = reader.nextInt();
				while(dirChange != 90 && dirChange != 45 && dirChange != 0 && dirChange != 315 && dirChange != 270 && dirChange != 225 && dirChange != 180 && dirChange != 135) {
					System.out.println("Opcion invalida. Por favor elegir una opcion valida.");
					System.out.println(">> ");
					dirChange = reader.nextInt();
				}
				
				if(dirChange == 90) currentGame.changeShipDirection(90);
				if(dirChange == 45) currentGame.changeShipDirection(45);
				if(dirChange == 0) currentGame.changeShipDirection(0);
				if(dirChange == 315) currentGame.changeShipDirection(315);
				if(dirChange == 270) currentGame.changeShipDirection(270);
				if(dirChange == 225) currentGame.changeShipDirection(225);
				if(dirChange == 180) currentGame.changeShipDirection(180);
				if(dirChange == 135) currentGame.changeShipDirection(135);
				currentGame.moveShip();
			}
			if(choice == 3) {
				currentGame.fireMissile();
			}
			if(choice == 4) {
				currentGame.moveShip();
			}
			currentGame.moveAllAsteroids();
			currentGame.moveAllMissiles();
			currentGame.checkForAsteroidsCollision();
			currentGame.checkForShotAsteroids();
			currentGame.cleanBoard();
			currentGame.printSpaceShip();
			System.out.println("Puntaje actual: " + currentGame.getScore());
			displayOnlyMoves();
			currentGame.positionEntities();
			setTextifiedSpace(currentGame.textifySpace());
			System.out.println(checkState());
			printSpace();
		}
	}

	 public static void main(String[] args) {
		 
		 Gaming gameOn = new Gaming();
		 gameOn.gameLoop();
	}
}
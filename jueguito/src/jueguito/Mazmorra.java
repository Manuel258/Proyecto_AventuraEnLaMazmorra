package jueguito;

import java.util.Scanner; /*LIBRERIA QUE PERMITE INTRODUCIR VALORES*/



public class Mazmorra {
	/* este es basicamente el mapa */

	private static final int Tamaño = 8; // Tamaño de la matriz
	private static int x = 0; // Posición inicial en X
	private static int y = 0; // Posición inicial en Y
	private static char[][] Dentro = new char[Tamaño][Tamaño];
	private static Jugador jugador = new Jugador();
	private static Enemigo enemigo; // Enemigo actual
    


	public void iniciarJuego(Scanner scanner) {

		System.out.println("Iniciando un nuevo juego...");
		  inicializarMapa();
		boolean continuar = true;

		while (continuar) {
			mostrarMatriz();
			
			System.out.print("Ingrese un comando (W=arriba, S=abajo, A=izquierda, D=derecha) ");
			System.out.println();
			System.out.print("Ingrese un comando (Z=usar posion, Salir=salir, Q= ver inventqario)");
		    System.out.println();
			System.out.print("Ingrese un comando (E= mostrar estado del jugador):");

			String comando = scanner.nextLine().toUpperCase();

			switch (comando) {
			case "W":
				mover(-1, 0); // Arriba
				break;
			case "S":
				mover(1, 0); // Abajo
				break;
			case "A":
				mover(0, -1); // Izquierda
				break;
			case "D":
				mover(0, 1); // Derecha
				break;
			case "Z":
                jugador.usarPocion(); // Usar poción
                break;
			case "Q":
                jugador. mostrarInventario(); // ver inventario
                break;
			case "E":
				Mazmorra mazmorri = new Mazmorra();
                mazmorri.mostrarEstadoJugador();; // mostrar estado
                break;
			case "SALIR":
				continuar = false;
				System.out.println("¡Gracias por jugar!");
				break;
			default:
				System.out.println("Comando inválido. Intente nuevamente.");
			}
			System.out.println(); // Línea en blanco para mayor claridad
		}

		scanner.close();

		/* Aqui se imprime la mazmorra y los objetos */

	}
	 public void inicializarMapa() {
	        for (int i = 0; i < Tamaño; i++) {
	            for (int j = 0; j < Tamaño; j++) {
	                Dentro[i][j] = '.'; // Inicializar el mapa
	            }
	        }
	        // Colocar enemigos y objetos en el mapa
	        Dentro[0][1] = 'C'; // chalan
	        Dentro[5][1] = 'C'; // chalan
	        Dentro[2][4] = 'P'; // poción de vida
	        Dentro[3][1] = 'M'; // Mai
	        Dentro[1][1] = 'A'; // arma
	        Dentro[4][3] = 'E'; // armadura
	        Dentro[5][6] = 'P'; // poción de vida
	        Dentro[7][7] = 'B'; // Jefe
	    }
	 
	public void mostrarMatriz() {
		for (int i = 0; i < Tamaño; i++) {
			for (int j = 0; j < Tamaño; j++) {

				if (i == x && j == y) {
					System.out.print("X\t"); // Marcador de posición del jugador
				} else {
					System.out.print(Dentro[i][j] + "\t "); // Espacio vacío
				}
			}
			System.out.println();

		}

	}
	
	
    public void mostrarEstadoJugador() {
        System.out.println("Vida: " + jugador.getSalud());
        System.out.println("Escudo: "+ jugador.getEscudo());
    }

//se mueve dentro de la matriz y evita que salgas del mapa 
	public void mover(int deltaX, int deltaY) {
		// Calcular nueva posición
		int nuevaX = x + deltaX;
		int nuevaY = y + deltaY;

		// Verificar límites
		if (nuevaX >= 0 && nuevaX < Tamaño && nuevaY >= 0 && nuevaY < Tamaño) {
			x = nuevaX;
			y = nuevaY;
			 interactuar();
		} else {
			System.out.println("No puedes moverte fuera de la matriz.");
		}
	}

	/* Aqui van las modalidades de ataque */
	
	   public void interactuar() {
	        char celda = Dentro[x][y];

	        if (celda == 'C') {
	            enemigo = new Chalan();
	            System.out.println("¡Has encontrado a Chalan!");
	            iniciarCombate(enemigo);
	            Dentro[x][y] = '.'; 
	        } else if (celda == 'P') {
	            PocionDeVida pocion = new PocionDeVida("Poción de Vida", 20);
	            jugador.añadirAlInventario(pocion);
	            Dentro[x][y] = '.'; /*Deberi de eliminar del mapa, pero no lo hace :c*/
	        } else if (celda == 'M') {
	            enemigo = new Mai();
	            System.out.println("¡Has encontrado a Mai!");
	            iniciarCombate(enemigo);
	            Dentro[x][y] = '.'; 
	        } else if (celda == 'A') {
	            Arma arma = new Arma("Espada", 15);
	            jugador.añadirAlInventario(arma);
	            Dentro[x][y] = '.'; 
	        } else if (celda == 'E') {
	            Armadura armadura = new Armadura("Chaleco", 50);
	            jugador.añadirAlInventario(armadura);
	            Dentro[x][y] = '.'; 
	        } else if (celda == 'B') {
	            enemigo = new ElPatron();
	            System.out.println("¡Has encontrado al jefe, El Patrón!");
	            iniciarCombate(enemigo);
	            Dentro[x][y] = '.'; 
	        }

	   }
    // Inicia el combate con un enemigo
	   public void iniciarCombate(Enemigo enemigo) {
		    System.out.println("¡Comienza el combate contra " + enemigo.getClass().getSimpleName() + "!");

		    while (jugador.getSalud() > 0 && enemigo.getSalud() > 0) {
		        // Mostrar salud y escudo actual del jugador y del enemigo
		        System.out.println("Tu salud: " + jugador.getSalud());
		        System.out.println("Tu escudo: " + jugador.getEscudo());
		        System.out.println("Salud de " + enemigo.getClass().getSimpleName() + ": " + enemigo.getSalud());

		        // Turno del jugador
		        enemigo.restarSalud(jugador.getAtaque());
		        System.out.println("Has atacado a " + enemigo.getClass().getSimpleName() + ", su salud ahora es: " + enemigo.getSalud());

		        // Verificar si el enemigo fue derrotado
		        if (enemigo.getSalud() <= 0) {
		            System.out.println("¡Has derrotado a " + enemigo.getClass().getSimpleName() + "!");
		            Dentro[x][y] = '.'; // Eliminar enemigo del mapa
		            if (enemigo instanceof ElPatron) {
		                System.out.println("¡Has ganado! Fin del juego.");
		                System.exit(0); // Termina el juego
		            }
		            return; // Salir del combate
		        }

		        // Turno del enemigo
		        jugador.restarSalud(enemigo.getAtaque());
		        System.out.println(enemigo.getClass().getSimpleName() + " te ha atacado, tu salud ahora es: " + jugador.getSalud());

		        // Mostrar escudo después del ataque del enemigo
		        System.out.println("Tu escudo restante: " + jugador.getEscudo());

		        // Verificar si el jugador fue derrotado
		        if (jugador.getSalud() <= 0) {
		            System.out.println("¡Has sido derrotado!");
		            System.out.println("Fin del juego.");
		            System.exit(0); // Termina el juego
		        }
		    }
		}
	   
	   

	}

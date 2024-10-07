package jueguito;

import java.util.Scanner;/*Esta cosa es una libreria para poder usar el scanner*/

public class Menu {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Scanner scanner1 = new Scanner(System.in);

		System.out.println("/////////////////////////////////////////////");
		System.out.println("BELLAKOS EN LA PLAZA OSCURA");
		System.out.println("Precione cualquier letra para continuar");
		System.out.println("/////////////////////////////////////////////");

		String primero = scanner.nextLine();

		boolean salir = false;

		while (!salir) {
			// Mostrar el menú principal
			System.out.println("=== MENÚ PRINCIPAL ===");
			System.out.println("1. Iniciar un nuevo juego");
			System.out.println("2. Ver instrucciones");
			System.out.println("3. Salir");
			System.out.print("Seleccione una opción: ");

			int opcion = scanner.nextInt();

			switch (opcion) {
			case 1:
				Mazmorra mazmorra = new Mazmorra();
				mazmorra.iniciarJuego(scanner1);
				break;
			case 2:
				Menu menu = new Menu();
				menu.verInstrucciones(scanner1);
				break;
			case 3:
				salir = true;
				System.out.println("Gracias por jugar. ¡Hasta luego!");
				break;
			default:
				System.out.println("Opción inválida. Por favor, elija nuevamente.");
			}
			System.out.println();
		}

		scanner.close();
	}

	public void verInstrucciones(Scanner scanner) {
		System.out.println("=== INSTRUCCIONES ===");
		System.out.println(
				"El jugador cuenta con un porcentaje de vida y equipamiento para acabar con los enemigos que se le pongan enfrente.");
		System.out.println(
				"La misión principal es derrotar a los enemigo e ir consiguiendo objetos y vida conforme el jugador vaya avanzando, ");
		System.out.println(
				"el último reto será vencer al jefe final, denominado “el patrón”, una vez hayas eliminado a los enemigos y haber ");
		System.out.println("recogido los objetos habrás ganado la plaza y con ello el juego. Suerte.");
		System.out.println("Presiona 'Enter' para regresar al menú.");
		scanner.nextLine();
		scanner.nextLine();
	}

}

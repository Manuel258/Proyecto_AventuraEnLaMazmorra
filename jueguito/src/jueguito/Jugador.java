package jueguito;

import java.util.ArrayList;

class Jugador {
	private int salud;
	private int ataque;
	private int escudo;
	private ArrayList<Objeto> inventario;

	public Jugador() {
		this.salud = 100;
		this.ataque = 25;
		this.escudo = 0;
		this.inventario = new ArrayList<>();
	}

	public int getSalud() {
		return salud;
	}

	public void restarSalud(int daño) {
		if (escudo > 0) {
			// Primero, el daño se reduce por el escudo
			int dañoAbsorbido = Math.min(daño, escudo);
			escudo -= dañoAbsorbido; // Reducir el escudo
			daño -= dañoAbsorbido; // Reducir el daño restante
		}
		// Aplicar el daño restante a la salud
		this.salud = Math.max(0, salud - daño);
	}

	public int getAtaque() {
		return ataque;
	}

	public void setDefensa(int defensa) {
		this.escudo = defensa;
	}

	public int getEscudo() {
		return escudo;
	}

	public void añadirAlInventario(Objeto objeto) {
		inventario.add(objeto);
		System.out.println("¡Has recogido: " + objeto.getNombre() + "!");

		/*
		 * aumenta daño y escudo, sin necesidad de utilizarlo, para efectos mas
		 * practicosXD
		 */
		/* PD: ya me canseeeee */
		if (objeto instanceof Arma) {
			ataque += ((Arma) objeto).getIncrementoAtaque();
			System.out.println("¡Tu ataque ha aumentado a " + ataque + "!");
		} else if (objeto instanceof Armadura) {
			escudo += ((Armadura) objeto).getDefensa();
			System.out.println("¡Tu escudo ha aumentado a " + escudo + "!");
		}
	}

	public void usarPocion() {
		for (Objeto objeto : inventario) {
			if (objeto instanceof PocionDeVida) {
				PocionDeVida pocion = (PocionDeVida) objeto;
				salud += pocion.getCantidadRestaurada();
				inventario.remove(objeto);
				System.out.println("¡Has usado la " + pocion.getNombre() + "!");
				return;
			}
		}
		System.out.println("No tienes pociones en el inventario.");
	}

	public void mostrarInventario() {
		System.out.println("Inventario:");
		if (inventario.isEmpty()) {
			System.out.println("El inventario está vacío.");
		} else {
			for (Objeto objeto : inventario) {
				System.out.println("- " + objeto.getNombre());
			}
		}
	}
}
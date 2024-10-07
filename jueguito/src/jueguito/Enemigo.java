package jueguito;

abstract class Enemigo {
	protected String nombre;
	protected int salud;
	protected int ataque;

	public Enemigo(String nombre, int salud, int ataque) {
		this.nombre = nombre;
		this.salud = salud;
		this.ataque = ataque;
	}

	public int getSalud() {
		return salud;
	}

	public void restarSalud(int daño) {
		this.salud = Math.max(0, salud - daño);
	}

	public int getAtaque() {
		return ataque;
	}
}

class Chalan extends Enemigo {
	public Chalan() {
		super("Chalan", 50, 20);
	}
}

class Mai extends Enemigo {
	public Mai() {
		super("Mai", 70, 40);
	}
}

class ElPatron extends Enemigo {
	public ElPatron() {
		super("El Patrón", 120, 50);
	}
}
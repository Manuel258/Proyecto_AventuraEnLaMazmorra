package jueguito;

abstract class Objeto {
	protected String nombre;

	public Objeto(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
}

class PocionDeVida extends Objeto {
	private int cantidadRestaurada;

	public PocionDeVida(String nombre, int cantidadRestaurada) {
		super(nombre);
		this.cantidadRestaurada = cantidadRestaurada;
	}

	public int getCantidadRestaurada() {
		return cantidadRestaurada;
	}
}

class Arma extends Objeto {
	private int incrementoAtaque;

	public Arma(String nombre, int incrementoAtaque) {
		super(nombre);
		this.incrementoAtaque = incrementoAtaque;
	}

	public int getIncrementoAtaque() {
		return incrementoAtaque;
	}
}

class Armadura extends Objeto {
	private int defensa;

	public Armadura(String nombre, int defensa) {
		super(nombre);
		this.defensa = defensa;
	}

	public int getDefensa() {
		return defensa;
	}
}

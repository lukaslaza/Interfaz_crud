package clases;

public class Vehiculo {
	// VALORES
	private String matricula, marca, modelo, color, id_cliente, id_vehiculo_tipo;
	private int anno;

	// CONSTRUCTORES
	public Vehiculo(String matricula, String marca, String modelo, int anno, String color, String id_cliente,
			String id_vehiculo_tipo) {
		setMatricula(matricula);
		setMarca(marca);
		setModelo(modelo);
		setAnno(anno);
		setColor(color);
		setId_cliente(id_cliente);
		setId_vehiculo_tipo(id_vehiculo_tipo);
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getId_vehiculo_tipo() {
		return id_vehiculo_tipo;
	}

	public void setId_vehiculo_tipo(String id_vehiculo_tipo) {
		this.id_vehiculo_tipo = id_vehiculo_tipo;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

}

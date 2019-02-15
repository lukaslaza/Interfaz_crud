package clases;

public class Vehiculo_Tipo {
	
	//VALORES
	private String id, descripcion;

	//CONSTRUCTORES
	public Vehiculo_Tipo(String id, String descripcion) {
		setId(id);
		setDescripcion(descripcion);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

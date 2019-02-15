package database;

import interfaz.Principal;

public class funciones {

	/**
	 * La posicion del primer elemento de la pagina en funcion de su pagina. Por
	 * ejemplo, de una pagina de 20 elementos, para la pagina 2 el primer elemento
	 * que necesitamos es el 41. A partir del 41, recogemos 20 elementos y ya
	 * tenemos nuestra pagina. Para ello podemos utilizar este metodo
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public static int paginaActual(int currentPage, int pageSize) {
		return Math.max((pageSize * (currentPage - 1)) + 1, 1);
	}
	
	public static int paginasMaximas(int registrosTotales, int registrosPorPagina) {
		return (int) Math.ceil(registrosTotales/registrosPorPagina);
	}
	

	public static void main(String[] args) {
		System.out.println(paginasMaximas(1000, 10));
	}

}

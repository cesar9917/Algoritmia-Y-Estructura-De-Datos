package Actividades_1;

public class Principal {
	public static void main(String[] args) {
		
		//creamos la lista bolsaCho de tipo Chocolatina y le asignamos un tope de 4 elementos
		Bolsa <Chocolatina> bolsaCho =  new Bolsa <Chocolatina>(4);
		
		//creamos los objetos que seran nuestros elementos de nuestra lista
		Chocolatina c1 = new Chocolatina("MIlka");
		Chocolatina c2 = new Chocolatina("MIlka");
		Chocolatina c3 = new Chocolatina("Ferrero");
		
		
		//agregamos a la lista los objetos
		bolsaCho.add(c1);
		bolsaCho.add(c2);
		bolsaCho.add(c3);

		//recorremos la lista y usamos el metodo para obetener la marca de cada chocolatina
		for (Chocolatina c : bolsaCho) {
			System.out.println(c.getMarca());
		}
		
		Bolsa <Golosina> bolsaGolosinas = new Bolsa <Golosina> (3);
		
		Golosina g1 = new Golosina("Caramelo de Limon", 14);
		Golosina g2 = new Golosina("Chupetin", 15);
		Golosina g3 = new Golosina("Chicle", 16);
		
		bolsaGolosinas.add(g1);
		bolsaGolosinas.add(g2);
		bolsaGolosinas.add(g3);
		
		for (Golosina g : bolsaGolosinas) {
			System.out.println(g.getNombre() + "-" + g.getPeso());
		}
	}
}

// Agrega esto dentro de tu método main()

// EJERCICIO 01
System.out.println("\n¿Está 't1' en la lista de completadas? " +
    UtilidadesGenericas.buscarElemento(gestor.tareasCompletadas, t1));

// EJERCICIO 02
List<Tarea> listaOriginal = new ArrayList<>();
listaOriginal.add(t1);
listaOriginal.add(t2);
listaOriginal.add(t3);
List<Tarea> listaInvertida = UtilidadesGenericas.invertirLista(listaOriginal);
System.out.println("\nLista invertida:");
for (Tarea t : listaInvertida) {
    System.out.println(t);
}

// EJERCICIO 03
Nodo<Tarea> lista = new Nodo<>(t1);
lista = UtilidadesGenericas.insertarAlFinal(lista, t2);
lista = UtilidadesGenericas.insertarAlFinal(lista, t3);
System.out.println("\nLista luego de insertar al final:");
Nodo<Tarea> aux = lista;
while (aux != null) {
    System.out.println(aux.dato);
    aux = aux.siguiente;
}

// EJERCICIO 04
int cantidad = UtilidadesGenericas.contarNodos(lista);
System.out.println("\nCantidad de nodos en la lista: " + cantidad);

// EJERCICIO 05
Nodo<Tarea> lista2 = new Nodo<>(t1);
lista2 = UtilidadesGenericas.insertarAlFinal(lista2, t2);
lista2 = UtilidadesGenericas.insertarAlFinal(lista2, t3);
System.out.println("\n¿Son iguales lista y lista2?: " +
    UtilidadesGenericas.compararListas(lista, lista2));

// EJERCICIO 06
Nodo<Tarea> listaConcatenada = UtilidadesGenericas.concatenarListas(lista, lista2);
System.out.println("\nLista concatenada:");
Nodo<Tarea> auxConcat = listaConcatenada;
while (auxConcat != null) {
    System.out.println(auxConcat.dato);
    auxConcat = auxConcat.siguiente;
}

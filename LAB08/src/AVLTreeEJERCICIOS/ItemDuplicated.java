package AVLTree; // Paquete del proyecto

// Excepción personalizada para elementos duplicados en el árbol
public class ItemDuplicated extends Exception {

    // Constructor que recibe un mensaje de error
    public ItemDuplicated(String message) {
        super(message); // Llama al constructor de la clase Exception
    }
}

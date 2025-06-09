package Graph;

public class Edge<E> {
    private Vertex<E> refDest;
    private int weight;

    public Edge(Vertex<E> refDest) {
        this.refDest = refDest;
        this.weight = 1;
    }

    public Edge(Vertex<E> refDest, int weight) {
        this.refDest = refDest;
        this.weight = weight;
    }

    public Vertex<E> getRefDest() {
        return refDest;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Edge<?>) {
            Edge<?> other = (Edge<?>) o;
            return this.refDest.equals(other.refDest);
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + refDest.getData() + ", peso=" + weight + ")";
    }
}
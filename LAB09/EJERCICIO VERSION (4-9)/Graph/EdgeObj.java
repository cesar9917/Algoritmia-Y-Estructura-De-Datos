package Graph;

import java.util.Objects; 

public class EdgeObj<V, E> {
    private VertexObj<V, E> sourceVertex;      
    private VertexObj<V, E> destinationVertex; 
    private E weight;                          
    private int index;                         
    private boolean isDirected;                

   
    public EdgeObj(VertexObj<V, E> source, VertexObj<V, E> destination, E weight, int index, boolean isDirected) {
        this.sourceVertex = source;
        this.destinationVertex = destination;
        this.weight = weight;
        this.index = index;
        this.isDirected = isDirected;
    }

    public EdgeObj(VertexObj<V, E> v1, VertexObj<V, E> v2, E weight, int index) {
        this(v1, v2, weight, index, false); 
    }

    public boolean connects(VertexObj<V, E> v1, VertexObj<V, E> v2) {
        if (isDirected) {

            return sourceVertex.equals(v1) && destinationVertex.equals(v2);
        } else {

            return (sourceVertex.equals(v1) && destinationVertex.equals(v2)) ||
                   (sourceVertex.equals(v2) && destinationVertex.equals(v1));
        }
    }
    
    public VertexObj<V, E> getSource() {
        return sourceVertex;
    }

    public VertexObj<V, E> getDestination() {
        return destinationVertex;
    }
    public E getWeight() {
        return weight;
    }
    public boolean isDirected() {
        return isDirected;
    }
    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeObj<?, ?> edgeObj = (EdgeObj<?, ?>) o;
        if (isDirected) {
            return Objects.equals(sourceVertex, edgeObj.sourceVertex) &&
                   Objects.equals(destinationVertex, edgeObj.destinationVertex) &&
                   isDirected == edgeObj.isDirected;
        } else {
            return ((Objects.equals(sourceVertex, edgeObj.sourceVertex) && Objects.equals(destinationVertex, edgeObj.destinationVertex)) ||
                    (Objects.equals(sourceVertex, edgeObj.destinationVertex) && Objects.equals(destinationVertex, edgeObj.sourceVertex))) &&
                   isDirected == edgeObj.isDirected;
        }
    }

    @Override
    public int hashCode() {
        if (isDirected) {
            return Objects.hash(sourceVertex, destinationVertex, isDirected);
        } else {
            return Objects.hash(Math.min(sourceVertex.hashCode(), destinationVertex.hashCode()),
                                Math.max(sourceVertex.hashCode(), destinationVertex.hashCode()), isDirected);
        }
    }

    @Override
    public String toString() {
        if (isDirected) {
            return sourceVertex.getInfo() + " -> " + destinationVertex.getInfo();
        } else {
            return sourceVertex.getInfo() + " -- " + destinationVertex.getInfo();
        }
    }
}

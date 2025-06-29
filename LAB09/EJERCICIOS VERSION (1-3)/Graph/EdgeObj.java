package Graph;

public class EdgeObj<V, E> {
    protected E info;
    protected VertexObj<V, E> endVertex1;
    protected VertexObj<V, E> endVertex2;
    protected int position;

    public EdgeObj(VertexObj<V, E> vert1, VertexObj<V, E> vert2, E info, int position) {
        this.endVertex1 = vert1;
        this.endVertex2 = vert2;
        this.info = info;
        this.position = position;
    }

    public boolean connects(VertexObj<V, E> v1, VertexObj<V, E> v2) {
        return (endVertex1 == v1 && endVertex2 == v2) || (endVertex1 == v2 && endVertex2 == v1);
    }
}

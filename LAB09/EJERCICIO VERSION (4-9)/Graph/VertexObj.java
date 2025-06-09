package Graph;
import java.util.Objects;
public class VertexObj<V, E> {
    protected V info;
    protected int position;

    public VertexObj(V info, int position) {
        this.info = info;
        this.position = position;
    }

    public V getInfo() {
        return info;
    }

    public int getPosition() {
        return position;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VertexObj<?, ?> vertexObj = (VertexObj<?, ?>) o;
        return Objects.equals(info, vertexObj.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }

    @Override
    public String toString() {
        return info.toString();
    }
}
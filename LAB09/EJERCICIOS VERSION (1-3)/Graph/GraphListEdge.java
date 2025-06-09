package Graph;

import java.util.*;

public class GraphListEdge<V, E> {
    ArrayList<VertexObj<V, E>> secVertex;
    ArrayList<EdgeObj<V, E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    public void insertVertex(V v) {
        if (!searchVertex(v)) {
            secVertex.add(new VertexObj<>(v, secVertex.size()));
        }
    }

    public void insertEdge(V v, V z) {
        if (!searchEdge(v, z)) {
            VertexObj<V, E> v1 = getVertexObj(v);
            VertexObj<V, E> v2 = getVertexObj(z);
            if (v1 != null && v2 != null) {
                secEdge.add(new EdgeObj<>(v1, v2, null, secEdge.size()));
            }
        }
    }

    public boolean searchVertex(V v) {
        return getVertexObj(v) != null;
    }

    public boolean searchEdge(V v, V z) {
        VertexObj<V, E> v1 = getVertexObj(v);
        VertexObj<V, E> v2 = getVertexObj(z);
        if (v1 == null || v2 == null) return false;

        for (EdgeObj<V, E> edge : secEdge) {
            if (edge.connects(v1, v2)) return true;
        }
        return false;
    }

    public void bfs(V v) {
        VertexObj<V, E> start = getVertexObj(v);
        if (start == null) return;

        Set<VertexObj<V, E>> visited = new HashSet<>();
        Queue<VertexObj<V, E>> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            VertexObj<V, E> current = queue.poll();
            System.out.println(current.getInfo());

            for (EdgeObj<V, E> edge : secEdge) {
                VertexObj<V, E> neighbor = null;
                if (edge.endVertex1 == current) {
                    neighbor = edge.endVertex2;
                } else if (edge.endVertex2 == current) {
                    neighbor = edge.endVertex1;
                }

                if (neighbor != null && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    private VertexObj<V, E> getVertexObj(V v) {
        for (VertexObj<V, E> vertex : secVertex) {
            if (vertex.getInfo().equals(v)) {
                return vertex;
            }
        }
        return null;
    }
}


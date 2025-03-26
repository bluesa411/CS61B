import main.DirectGraph;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class TestGraph {
    @Test
    void testGraph() {
        DirectGraph graph = new DirectGraph();
        graph.createNode();
        graph.createNode();
        graph.createNode();
        graph.createNode();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
    }
}

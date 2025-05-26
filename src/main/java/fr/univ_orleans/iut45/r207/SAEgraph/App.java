package fr.univ_orleans.iut45.r207.SAEgraph;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.util.SupplierUtil;

/**
 * Hello JGraphT!
 */
public class App {

    public static Set<String> collaborateursProches(Map<String, Set<String>> G, String u, int k) {
        if (!G.containsKey(u)) {
            return null;
        }
        Set<String> collaborateurs = new HashSet<>();
        collaborateurs.add(u);
        for (int i = 1; i <= k; i++) {
            Set<String> collaborateursDirects = new HashSet<>();
            for (String c : collaborateurs) {
                Set<String> voisins = G.getOrDefault(c, new HashSet<>());
                for (String v : voisins) {
                    if (!collaborateurs.contains(v)) {
                        collaborateursDirects.add(v);
                    }
                }
            }
            collaborateurs.addAll(collaborateursDirects);
        }
        return collaborateurs;
    }

	
	public static void main(String[] args) {
		
		Graph<String, DefaultEdge> graph = GraphTypeBuilder
				.directed()
				.allowingMultipleEdges(true)
				.allowingSelfLoops(true)
				.vertexSupplier(SupplierUtil.createStringSupplier())
				.edgeSupplier(SupplierUtil.createDefaultEdgeSupplier())
				.buildGraph();

		String v0 = graph.addVertex();
		String v1 = graph.addVertex();
		String v2 = graph.addVertex();

		graph.addEdge(v0, v1);
		graph.addEdge(v1, v2);
		graph.addEdge(v0, v2);

		for (String v : graph.vertexSet()) {
			System.out.println("vertex: " + v);
		}

		for (DefaultEdge e : graph.edgeSet()) {
			System.out.println("edge: " + e);
		}
		
	}
	
}

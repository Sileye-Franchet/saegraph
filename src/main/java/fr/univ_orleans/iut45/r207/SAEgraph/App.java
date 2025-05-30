package fr.univ_orleans.iut45.r207.SAEgraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.util.SupplierUtil;

import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.AttributeType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Hello JGraphT!
 */
public class App {
	
	public static void main(String[] args) {
        List<Film> films = new ArrayList<>();
		
<<<<<<< HEAD
		Graph<Film, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

=======
		Graph<Map<List<String>, String>, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
>>>>>>> 95419e8 (supression de se qui ne sert pas)
		
		// lecture json file
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("data.txt")));

            Gson gson = new Gson();

            Type listType = new TypeToken<List<Film>>(){}.getType();

            // Parser le JSON en liste d'objets Personne
<<<<<<< HEAD
            films = gson.fromJson(jsonString, listType);
=======
            List<Film> films = gson.fromJson(jsonString, listType);

            // ???
            for (Film p : films) {
                // trouver quoi faire
            }
>>>>>>> 95419e8 (supression de se qui ne sert pas)

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Film film : films){
            graph.addVertex(film);
        }

        for (Film film : graph.vertexSet()) {
            for (Film coFilm : films) {
                List<String> coCast = film.getCast();
                if (!film.equals(coFilm) && coCast.retainAll(film.getCast())) {
                    graph.addEdge(film, coFilm);
                }
            }
        }

        try{
			DOTExporter<Film, DefaultEdge> exporter = new DOTExporter<Film, DefaultEdge>();
			exporter.setVertexAttributeProvider((x) -> Map.of("label", new DefaultAttribute<>(x, AttributeType.STRING)));
			exporter.exportGraph(graph, new FileWriter("graph.dot"));
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
}

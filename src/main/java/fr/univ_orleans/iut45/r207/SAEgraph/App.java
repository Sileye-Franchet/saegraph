package fr.univ_orleans.iut45.r207.SAEgraph;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Scanner;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Hello JGraphT!
 */
public class App {

    public static List<String> getNeighbors(Graph<String, DefaultEdge> g, String u) {
        List<String> neighbors = new ArrayList<>();
        for (DefaultEdge edge : g.edgesOf(u)){
            String source = g.getEdgeSource(edge);
            String target = g.getEdgeTarget(edge);
            if (!source.equals(u)) {
                neighbors.add(source);
            }
            if (!target.equals(u)) {
                neighbors.add(target);
            }
        }
        return neighbors;
    }

	
	public static void main(String[] args) throws IOException {
        List<Film> films = new ArrayList<>();
        Set<String> casts = new HashSet<>();

        Queue<String> file = new LinkedList<>();
        Set<String> visites = new HashSet<>();
        int taille = 1;

        Scanner scanner = new Scanner(System.in);
		
		Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
		
		// lecture json file
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("data_100.txt")));

            Gson gson = new Gson();

            Type listType = new TypeToken<List<Film>>(){}.getType();

            // Parser le JSON en liste d'objets Film
            films = gson.fromJson(jsonString, listType);

            }
        catch (Exception e) {
            e.printStackTrace();
        }

        // ajout des sommets et création de l'ensemble des casts
        for (Film film : films) {
            for (String cast : film.getCast()){
                graph.addVertex(cast);
                casts.add(cast);
            }
        }

        // ajout des arrêtes
        for (String castSommet : graph.vertexSet()){
            for (Film film : films) {
                if (film.getCast().contains(castSommet)){
                    for (String cast : film.getCast()){
                        if (!cast.equals(castSommet) && !graph.containsEdge(cast, castSommet) && !graph.containsEdge(castSommet, cast)){
                            graph.addEdge(cast, castSommet);
                        }
                    }
                }
            }
        }




        System.out.print("Acteur départ: ");
        String depart = scanner.nextLine();

        System.out.print("Acteur final: ");
        String fin = scanner.nextLine();
        scanner.close();

        file.add(depart);

        while (!file.isEmpty() && !visites.contains(fin)) {
            String sommet = file.poll();
            taille += 1;

            for (String voisin : getNeighbors(graph, sommet)) {
                if (!visites.contains(voisin)) {
                    file.add(voisin);
                    visites.add(voisin);
                }
            }
        }

        System.out.println("Chemin: " + visites + " taille: " + taille);
		
	}
	
}

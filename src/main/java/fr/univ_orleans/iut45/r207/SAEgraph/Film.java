package fr.univ_orleans.iut45.r207.SAEgraph;

import java.util.List;

public class Film {
    String title;
    List<String> cast;
    List<String> directors;
    List<String> producers;
    List<String> companies;
    int year;

    public Film(String title, List<String> cast, List<String> directors, List<String> producers, List<String> companies, int year) {
        this.title = title;
        this.cast = cast;
        this.directors = directors;
        this.producers = producers;
        this.companies = companies;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getCast() {
        return cast;
    }
    public List<String> getDirectors() {
        return directors;
    }
    public List<String> getProducers() {
        return producers;
    }
    public List<String> getCompanies() {
        return companies;
    }
    public int getYear() {
        return year;
    }
}

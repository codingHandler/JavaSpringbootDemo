package com.ch.Comparable与Comparator的区别;

import java.util.Comparator;

/**
 * @className: ComparatorTest
 * @Auther: ch
 * @Date: 2022/4/21 22:18
 * @Description: TODO
 */
public class ComparatorTest {
    public static void main(String[] args) {

    }

}

class Movie2 implements Comparator<Movie2> {

    private double rating;
    private String name;
    private int year;

    // Constructor
    public Movie2(String nm, double rt, int yr) {
        this.name = nm;
        this.rating = rt;
        this.year = yr;
    }

    // Getter methods for accessing private data
    public double getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int compare(Movie2 o1, Movie2 o2) {
        if (o1.getRating() < o2.getRating()) return -1;
        if (o1.getRating() > o2.getRating()) return 1;
        else return 0;
    }
}

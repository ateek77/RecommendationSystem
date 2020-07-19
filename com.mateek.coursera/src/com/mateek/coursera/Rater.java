/**
 * 
 */
package com.mateek.coursera;

import java.util.Set;

/**
 * @author mateek
 *
 */
public interface Rater {

    /**
     * add movie rating
     * @param item
     * @param rating
     */
    public void addRating(String item, double rating);

    /**
     * has movie rated
     * @param item
     * @return boolean
     */
    public boolean hasRating(String item);

    /**
     * get rater Id
     * @return String
     */
    public String getID();

    /**
     * return rating of movie , return -1 if movie not found 
     * @param item
     * @return Double
     */
    public double getRating(String item);

    /**
     * return total rating rater have
     * @return int
     */
    public int numRatings();

    /**
     * return set of movie rated by rater
     * @return Set<movie>
     */
    public Set<String> getItemsRated();
}

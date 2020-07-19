/**
 * 
 */
package com.mateek.coursera;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mateek
 *
 */

public class PlainRater implements Rater {
    private String myID;
    private ArrayList<Rating> myRatings;

    public PlainRater(String id) {
        myID = id;
        myRatings = new ArrayList<Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.add(new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return true;
            }
        }
        
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return myRatings.get(k).getValue();
            }
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public Set<String> getItemsRated() {
        Set<String> movieSet = new HashSet<String>();
        for(int k=0; k < myRatings.size(); k++){
            movieSet.add(myRatings.get(k).getItem());
        }
        
        return movieSet;
    }
}

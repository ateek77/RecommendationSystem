/**
 * 
 */
package com.mateek.coursera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * @author mateek
 *
 */
public class EfficientRater implements Rater {
	private String myID;
    private HashMap<String, Rating> myRatings;

	/**
	 * 
	 */
	public EfficientRater(String id) {
		
		myID = id;
		myRatings = new HashMap<String, Rating>();
	}

	/* (non-Javadoc)
	 * @see com.mateek.coursera.Rater#addRating(java.lang.String, double)
	 */
	@Override
	public void addRating(String item, double rating) {
		// TODO Auto-generated method stub
		myRatings.put(item, new Rating(item, rating) );
	}

	/* (non-Javadoc)
	 * @see com.mateek.coursera.Rater#hasRating(java.lang.String)
	 */
	@Override
	public boolean hasRating(String item) {
		Rating rating = myRatings.get(item);
		if(rating != null)
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mateek.coursera.Rater#getID()
	 */
	@Override
	public String getID() {
		return myID;
	}

	/* (non-Javadoc)
	 * @see com.mateek.coursera.Rater#getRating(java.lang.String)
	 */
	@Override
	public double getRating(String item) {
		Rating rating  = myRatings.get(item);
		if(rating != null) {
			return rating.getValue();
		}
		return -1;
	}

	/* (non-Javadoc)
	 * @see com.mateek.coursera.Rater#numRatings()
	 */
	@Override
	public int numRatings() {
		return myRatings.size();
	}

	/* (non-Javadoc)
	 * @see com.mateek.coursera.Rater#getItemsRated()
	 */
	@Override
	public Set<String> getItemsRated() {
		Set<String> movieSet = myRatings.keySet();
		return movieSet;
	}

}

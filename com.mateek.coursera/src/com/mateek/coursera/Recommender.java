package com.mateek.coursera;

import java.util.List;

public interface Recommender {
	
	/**
	 * returns a list of strings representing movie IDs
	 * @return List-String
	 */
	public List getItemsToRate();
	
	public void printRecommendationsFor(String webRaterID);
}

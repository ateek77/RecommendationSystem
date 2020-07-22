/**
 * 
 */
package com.mateek.coursera.filters;

/**
 * @author mateek
 *
 */
public interface Filter {

	/**
	 * returns true if the movie satisfies the criteria in the method and returns false otherwise
	 * @param movieId
	 * @return
	 */
	public Boolean satisfies(String movieId);
}

/**
 * 
 */
package com.mateek.coursera.filters;

/**
 * @author mateek
 *
 */
public class TrueFilter implements Filter {

	/*
	 * select every movie from MovieDatabase
	 */
	@Override
	public Boolean satisfies(String movieId) {
		return true;
	}

}

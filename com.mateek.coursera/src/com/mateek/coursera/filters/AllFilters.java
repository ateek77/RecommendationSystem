package com.mateek.coursera;

import java.util.ArrayList;

/**
 * @author mateek
 *
 */
public class AllFilters implements Filter{
	
	private ArrayList<Filter> filters;
	
	public AllFilters() {
		filters = new ArrayList<Filter>();
	}
	/**
	 * add a Filter to the ArrayList filters
	 * @param f
	 */
	public void addFilter(Filter f) {
		filters.add(f);
	}

	/**
	 * returns true if the movie satisfies the criteria of all the filters in the filters ArrayList.
	 *  Otherwise this method returns false.
	 * @param movieId
	 * @return
	 */
	@Override
	public Boolean satisfies(String movieId) {		
		for (Filter filter : filters) {
			if(!filter.satisfies(movieId)) {
				return false;
			}
		}
		return true;
	}
	
}

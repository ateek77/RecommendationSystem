/**
 * 
 */
package com.mateek.coursera;

/**
 * @author mateek
 *
 */
public class YearsAfterFilter implements Filter {
	
	Integer year;
	
	public YearsAfterFilter(Integer year) {
		super();
		this.year = year;
	}

	@Override
	public Boolean satisfies(String movieId) {
		Integer myear = MovieDatabase.getYear(movieId);
		if(myear < year)
			return false;
		return true;
	}

}

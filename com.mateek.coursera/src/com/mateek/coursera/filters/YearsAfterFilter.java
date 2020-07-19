/**
 * 
 */
package com.mateek.coursera;

/**
 * @author mateek
 *
 */
public class YearsAfterFilter implements Filter {
	
	private Integer year;
	

	public YearsAfterFilter(Integer year) {
		super();
		this.setYear(year);
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
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

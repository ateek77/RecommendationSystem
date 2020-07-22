package com.mateek.coursera.filters;

import com.mateek.coursera.MovieDatabase;

/**
 * @author mateek
 *
 */
public class MinutesFilter implements Filter {

	private Integer min_time,max_time;
	

	@Override
	public Boolean satisfies(String movieId) {
		Integer mtime = MovieDatabase.getMinutes(movieId);
		if(max_time >= mtime && mtime >= min_time)
			return true;		
		return false;
	}

	public MinutesFilter(Integer min_time, Integer max_time) {
		super();
		this.setMin_time(min_time);
		this.setMax_time(max_time);
	}

	public Integer getMin_time() {
		return min_time;
	}

	public void setMin_time(Integer min_time) {
		this.min_time = min_time;
	}

	public Integer getMax_time() {
		return max_time;
	}

	public void setMax_time(Integer max_time) {
		this.max_time = max_time;
	}
}

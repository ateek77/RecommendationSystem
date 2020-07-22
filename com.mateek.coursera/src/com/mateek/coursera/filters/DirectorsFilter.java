package com.mateek.coursera.filters;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mateek.coursera.MovieDatabase;

public class DirectorsFilter implements Filter {

	private Set<String> directors;
	public DirectorsFilter() {
		directors = new HashSet<String>();
	}
	public DirectorsFilter(String names) {
		super();
		directors = new HashSet<String>();
		this.setDirectors(names);
	}
	/* true if a movie has at least one of these directors as one of its directors.
	 * @see com.mateek.coursera.Filter#satisfies(java.lang.String)
	 */
	@Override
	public Boolean satisfies(String movieId) {
		List<String> mdirectors = MovieDatabase.getDirector(movieId);
		for (String dname : mdirectors) {
			if(directors.contains(dname))
				return true;
		}
		return false;
	}
	
	public Set<String> getDirectors() {
		return directors;
	}
	public void setDirectors(String directors) {
		String[] director = directors.split(",");
		for (String dname : director) {
			this.directors.add(dname);
		}
	}

}

/**
 * 
 */
package com.mateek.coursera.filters;

import java.util.List;

import com.mateek.coursera.MovieDatabase;

/**
 * @author mateek
 *
 */
public class GenreFilter implements Filter {
	
	private String genre;
	
	public GenreFilter(String genre) {
		super();
		this.setGenre(genre);
	}

	/* (non-Javadoc)
	 * @see com.mateek.coursera.Filter#satisfies(java.lang.String)
	 */
	@Override
	public Boolean satisfies(String movieId) {
		List<String> gernes = MovieDatabase.getGenres(movieId);
		for (String mgenre : gernes) {
			if(genre.equals(mgenre)) {
				return true;
			}
		}
		return false;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}

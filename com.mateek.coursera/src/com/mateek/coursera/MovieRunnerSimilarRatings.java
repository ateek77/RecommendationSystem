package com.mateek.coursera;

import java.util.List;

import com.mateek.coursera.filters.AllFilters;
import com.mateek.coursera.filters.DirectorsFilter;
import com.mateek.coursera.filters.Filter;
import com.mateek.coursera.filters.GenreFilter;
import com.mateek.coursera.filters.MinutesFilter;
import com.mateek.coursera.filters.YearsAfterFilter;
import com.mateek.coursera.rating.FourthRatings;
import com.mateek.coursera.rating.Rating;

public class MovieRunnerSimilarRatings {
	FourthRatings fourthRatings;
	
	public MovieRunnerSimilarRatings() {
		fourthRatings = new FourthRatings();
	}
	public void printAverageRatings() {		
		Integer minimalRaters = 35;
		
		System.out.println("number of rater =" + fourthRatings.getRaterSize());
		System.out.println("number of movies =" + MovieDatabase.size());
		
		List<Rating> ratingList = fourthRatings.getAverageRatings(minimalRaters);
		System.out.println(ratingList.size());
//		ratingList.sort(null);
//		for (Rating rating : ratingList) {
//			System.out.println(rating.getValue() +" " + MovieDatabase.getTitle((rating.getItem())));
//		}		
	}
	public void printAverageRatingsByYearAfterAndGenre() {
		Integer minimalRaters = 8,year = 1990;
		String genre = "Drama";
		
		AllFilters allFilters = new AllFilters();		
		allFilters.addFilter(new YearsAfterFilter(year));
		allFilters.addFilter(new GenreFilter(genre));
		
		List<Rating> ratingList = fourthRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
		System.out.println(ratingList.size());
//		ratingList.sort(null);
//		for (Rating rating : ratingList) {
//			System.out.println(rating.getValue() 
//			+ " "  + MovieDatabase.getTitle(rating.getItem())
//			+" "+ MovieDatabase.getMinutes(rating.getItem())
//			+" "+ MovieDatabase.getGenres(rating.getItem()));
//		}		
	}
	public void printSimilarRatings() {
		String raterId ="71";
		Integer minimalRaters = 5 ,
				numSimilarRaters = 20;
		
		List ratingList = fourthRatings.getSimilarRatings(raterId, numSimilarRaters, minimalRaters);
		for (Object object : ratingList) {
			Rating rating = (Rating) object;
			String movieName = MovieDatabase.getMovie(rating.getItem());
			System.out.println("movie = " + movieName + " weighted_avg = " + rating.getValue());
		}		
	}
	public void printSimilarRatingsByGenre () {	
		String raterId ="964" ,
				genre = "Mystery";
		Integer minimalRaters = 5 ,
				numSimilarRaters = 20;
		Filter filter = new GenreFilter(genre);
		
		List ratingList = fourthRatings.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters, filter);
		for (Object object : ratingList) {
			Rating rating = (Rating) object;
			String movieName = MovieDatabase.getMovie(rating.getItem());
			System.out.println("movie = " + movieName + " weighted_avg = " + rating.getValue());
		}
	}
	public void printSimilarRatingsByDirector () {
		String raterId ="120" ,
				directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
		Integer minimalRaters = 2 ,
				numSimilarRaters = 10;
		Filter filter = new DirectorsFilter(directors);
		
		List ratingList = fourthRatings.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters, filter);
		for (Object object : ratingList) {
			Rating rating = (Rating) object;
			String movieName = MovieDatabase.getMovie(rating.getItem());
			System.out.println("movie = " + movieName + " weighted_avg = " + rating.getValue());
		}
	}
	public void printSimilarRatingsByGenreAndMinutes () {
		String raterId ="168" ,
				genre = "Drama";
		Integer minimalRaters = 3 ,
				numSimilarRaters = 10,
				min_time = 80,
				max_time = 160;
		Filter filter = new GenreFilter(genre);
		Filter filter2 = new  MinutesFilter(min_time, max_time);
		AllFilters allfilter =new AllFilters();
		allfilter.addFilter(filter);
		allfilter.addFilter(filter2);
		List ratingList = fourthRatings.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters, allfilter);
		for (Object object : ratingList) {
			Rating rating = (Rating) object;
			String movieName = MovieDatabase.getMovie(rating.getItem());
			System.out.println("movie = " + movieName + " weighted_avg = " + rating.getValue());
		}
	}
	public void printSimilarRatingsByYearAfterAndMinutes () {
		String raterId ="314";
		Integer minimalRaters = 5 ,
				numSimilarRaters = 10,
				min_time = 70,
				max_time = 200,
				year  = 1975 ;
		
		Filter filter = new YearsAfterFilter(year);
		Filter filter2 = new  MinutesFilter(min_time, max_time);
		AllFilters allfilter =new AllFilters();
		allfilter.addFilter(filter);
		allfilter.addFilter(filter2);
		List ratingList = fourthRatings.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters, allfilter);
		for (Object object : ratingList) {
			Rating rating = (Rating) object;
			String movieName = MovieDatabase.getMovie(rating.getItem());
			System.out.println("movie = " + movieName + " weighted_avg = " + rating.getValue());
		}
	}
}

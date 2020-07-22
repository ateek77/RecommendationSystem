package com.mateek.coursera;

import java.util.List;

import com.mateek.coursera.filters.AllFilters;
import com.mateek.coursera.filters.DirectorsFilter;
import com.mateek.coursera.filters.Filter;
import com.mateek.coursera.filters.GenreFilter;
import com.mateek.coursera.filters.MinutesFilter;
import com.mateek.coursera.filters.YearsAfterFilter;
import com.mateek.coursera.rating.Rating;
import com.mateek.coursera.rating.ThirdRatings;

public class MovieRunnerWithFilters {
	ThirdRatings thirdRatings;
	
	public MovieRunnerWithFilters() {
		thirdRatings = new ThirdRatings();
	}
	
	public void printAverageRatings() {		
		Integer minimalRaters = 35;
		
		System.out.println("number of rater =" + thirdRatings.getRaterSize());
		System.out.println("number of movies =" + MovieDatabase.size());
		
		List<Rating> ratingList = thirdRatings.getAverageRatings(minimalRaters);
		System.out.println(ratingList.size());
//		ratingList.sort(null);
//		for (Rating rating : ratingList) {
//			System.out.println(rating.getValue() +" " + MovieDatabase.getTitle((rating.getItem())));
//		}		
	}
	public void printAverageRatingsByYear() {
		
		Integer minimalRaters = 20;
		Integer year = 2000;
		
		Filter filter = new YearsAfterFilter(year); 
		List<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(minimalRaters, filter);
		System.out.println(ratingList.size());
//		ratingList.sort(null);
//		for (Rating rating : ratingList) {
//			System.out.println(rating.getValue() +" "+ MovieDatabase.getYear(rating.getItem())
//			+ " "  + MovieDatabase.getTitle((rating.getItem())));
//		}
	}
	public void printAverageRatingsByGenre() {		
		Integer minimalRaters = 5;
		String genre = "Comedy";
		
		Filter filter = new GenreFilter(genre); 
		List<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(minimalRaters, filter);
		System.out.println(ratingList.size());
//		ratingList.sort(null);
//		for (Rating rating : ratingList) {
//			System.out.println(rating.getValue() 
//			+ " "  + MovieDatabase.getTitle((rating.getItem()))
//			+" "+ MovieDatabase.getGenres((rating.getItem())));
//		}
	}
	public void printAverageRatingsByMinutes() {
		Integer minimalRaters = 5, min_time = 105, max_time = 135;
		
		Filter filter = new MinutesFilter(min_time, max_time); 
		List<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(minimalRaters, filter);
		System.out.println(ratingList.size());
//		ratingList.sort(null);
//		for (Rating rating : ratingList) {
//			System.out.println(rating.getValue() 
//			+ " "  + MovieDatabase.getTitle((rating.getItem()))
//			+" "+ MovieDatabase.getMinutes((rating.getItem())));
//		}
	}
	public void printAverageRatingsByDirectors() {
		Integer minimalRaters = 4;
		String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
		
		Filter filter = new DirectorsFilter(directors); 
		List<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(minimalRaters, filter);
		System.out.println(ratingList.size());
//		ratingList.sort(null);
//		for (Rating rating : ratingList) {
//			System.out.println(rating.getValue() 
//			+ " "  + MovieDatabase.getTitle((rating.getItem()))
//			+" "+ MovieDatabase.getDirector((rating.getItem())));
//		}
	}
	public void printAverageRatingsByYearAfterAndGenre() {
		Integer minimalRaters = 8,year = 1990;
		String genre = "Drama";
		
		AllFilters allFilters = new AllFilters();		
		allFilters.addFilter(new YearsAfterFilter(year));
		allFilters.addFilter(new GenreFilter(genre));
		
		List<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
		System.out.println(ratingList.size());
//		ratingList.sort(null);
//		for (Rating rating : ratingList) {
//			System.out.println(rating.getValue() 
//			+ " "  + MovieDatabase.getTitle(rating.getItem())
//			+" "+ MovieDatabase.getMinutes(rating.getItem())
//			+" "+ MovieDatabase.getGenres(rating.getItem()));
//		}		
	}
	public void printAverageRatingsByDirectorsAndMinutes() {
		Integer minimalRaters = 3,min_time = 90, max_time = 180;
		String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
		
		AllFilters allFilters = new AllFilters();		
		allFilters.addFilter(new MinutesFilter(min_time, max_time));
		allFilters.addFilter(new DirectorsFilter(directors));
		
		List<Rating> ratingList = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
		System.out.println(ratingList.size());
//		ratingList.sort(null);
//		for (Rating rating : ratingList) {
//			System.out.println(rating.getValue() 
//			+ " "  + MovieDatabase.getTitle(rating.getItem())
//			+" "+ MovieDatabase.getMinutes(rating.getItem())
//			+" "+ MovieDatabase.getGenres(rating.getItem()));
//		}
		
	}
	
}

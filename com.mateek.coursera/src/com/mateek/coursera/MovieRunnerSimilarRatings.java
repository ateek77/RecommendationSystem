package com.mateek.coursera;

import java.util.List;

import com.mateek.coursera.filters.AllFilters;
import com.mateek.coursera.filters.GenreFilter;
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
}

package com.mateek.coursera;

import java.util.List;

public class MovieRunnerAverage {
	public void printAverageRatings() {
		SecondRatings secondRatings = new SecondRatings();
//		System.out.println("MovieRunnerAverage.printMovie() = " + secondRatings.getMovieSize());
//		System.out.println("MovieRunnerAverage.printRatings() = " + secondRatings.getRaterSize());
		
		List<Rating> ratingList = secondRatings.getAverageRatings(12);
		ratingList.sort(null);
		for (Rating rating : ratingList) {
			System.out.println(rating.getValue() +" " + secondRatings.getTitle(rating.getItem()));
		}
		
	}
	public void getAverageRatingOneMovie() {
		SecondRatings secondRatings = new SecondRatings();
		String mtitle = "Vacation";
		String mId  = secondRatings.getID(mtitle);
		//System.out.println(mId);
		Double avgRating = secondRatings.getMovieAverageRating(mId);
		System.out.println(mtitle + "- movie avg rating = " + avgRating);
	}
}
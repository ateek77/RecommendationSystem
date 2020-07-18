package com.mateek.coursera;

public class MainMethod {
	public static void main(String[] args) {
//		FirstRatings finalRatings = new FirstRatings();
//		// pass the file name for load the movies.
//		finalRatings.testLoadMovies();
//		finalRatings.testLoadRaters();
		MovieRunnerAverage movieRunnerAverage = new MovieRunnerAverage();
		movieRunnerAverage.printAverageRatings();
		movieRunnerAverage.getAverageRatingOneMovie();
	}
}

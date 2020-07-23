package com.mateek.coursera;

public class MainMethod {
	public static void main(String[] args) {
//		FirstRatings finalRatings = new FirstRatings();
//		// pass the file name for load the movies.
//		finalRatings.testLoadMovies();
//		finalRatings.testLoadRaters();
		String movieFilePath =  "C:\\\\Users\\mateek\\Downloads\\coursera\\StepOneStarterProgram\\data\\ratedmoviesfull.csv";
		//String movieFilePath = "C:\\\\Users\\mateek\\Downloads\\coursera\\StepOneStarterProgram\\data\\ratedmovies_short.csv";
		MovieDatabase.init(movieFilePath);
		//MovieRunnerAverage movieRunnerAverage = new MovieRunnerAverage();
		//movieRunnerAverage.printAverageRatings();
		//movieRunnerAverage.getAverageRatingOfMovie();
//		MovieRunnerWithFilters movieRunnerWithFilters = new MovieRunnerWithFilters();
		//movieRunnerWithFilters.printAverageRatings();
		//movieRunnerWithFilters.printAverageRatingsByYear();
//		movieRunnerWithFilters.printAverageRatingsByGenre();
//		movieRunnerWithFilters.printAverageRatingsByMinutes();
//		movieRunnerWithFilters.printAverageRatingsByDirectors();
//		movieRunnerWithFilters.printAverageRatingsByYearAfterAndGenre();
		//movieRunnerWithFilters.printAverageRatingsByDirectorsAndMinutes();
		MovieRunnerSimilarRatings movieRunnerSimilarRatings = new MovieRunnerSimilarRatings();
		movieRunnerSimilarRatings.printSimilarRatings();
	}
}

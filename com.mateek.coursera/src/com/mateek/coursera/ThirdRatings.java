package com.mateek.coursera;

import java.util.ArrayList;
import java.util.List;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this( "C:\\\\Users\\mateek\\Downloads\\coursera\\StepOneStarterProgram\\data\\ratings.csv");
        //this("C:\\\\Users\\mateek\\Downloads\\coursera\\StepOneStarterProgram\\data\\ratings_short.csv");
    }
    public ThirdRatings(String ratingsFileLoc) {
    	FirstRatings firstRatings = new FirstRatings();
    	myRaters = (ArrayList<Rater>) firstRatings.loadRaters(ratingsFileLoc);
    	
    }
    public Integer getRaterSize() {
    	return myRaters.size();
    }
    
    /**
     * average movie rating for this ID if there are at least minimalRaters ratings.
     *  If there are not minimalRaters ratings, then it returns 0.0.
     * @param movieId
     * @param miniRating
     * @return Double
     */
    private Double getAverageByID(String movieId, Integer minimalRaters) {
    	Integer count =0;

    	Double avgRating = (double) 0;
    	for (Rater rater : myRaters) {
    		Double rating = rater.getRating(movieId);
    		if(rating != -1) {
    			avgRating += rating;
    			count++;
    		}			
		}
    	
    	if(count >= minimalRaters && count != 0) {
    		avgRating = avgRating / count;
    		return avgRating;
    	}
    	return (double) 0;
    }
    public Double getMovieAverageRating(String movieId) {
		return getAverageByID(movieId, 0);
	}
    /**
     * find the average rating for every movie that has been rated by at least minimalRaters raters
     * @param minimalRaters
     * @return List<Rating>
     */
    public List getAverageRatings(Integer minimalRaters) {
    	List<Rating> ratingList = new ArrayList<Rating>();
    	List<String> moviesList = MovieDatabase.filterBy(new TrueFilter());
    	
    	for (String movieId : moviesList) {
			Double rating = getAverageByID(movieId, minimalRaters);
			if(!rating.equals(new Double(0))) {
				Rating rating2 = new Rating(movieId, rating);
				ratingList.add(rating2);
			}
		}
    	return ratingList;
    }
    /**
     * return an ArrayList of type Rating of all the movies that have at least minimalRaters ratings and satisfies the filter criteria
     * @param minimalRaters
     * @param filter
     * @return
     */
    public List getAverageRatingsByFilter (Integer minimalRaters, Filter filter) {
    	List<Rating> ratingList = new ArrayList<Rating>();
    	List<String> movieList = MovieDatabase.filterBy(filter);
    	for (String movieId : movieList) {
    		Double rating = getAverageByID(movieId, minimalRaters);
			if(!rating.equals(new Double(0))) {
				Rating rating2 = new Rating(movieId, rating);
				ratingList.add(rating2);
			}			
		}    	
    	return ratingList;		
	}
    
    /**
     * the title of the movie with that ID
     * @param movieId
     * @return String
     *
    public String getTitle(String movieId) {
    	
    	for (Movie movie : myMovies) {
			if(movie.getID().equals(movieId))
				return movie.getTitle();
		}
    	return movieId + "not found";
    }
    
    public String getID(String title) {
    	for (Movie movie : myMovies) {
			String mtitle = movie.getTitle();
			if((!mtitle.isEmpty()) && mtitle.equals(title)) {
				return movie.getID();
			}
		}
    	return "NO SUCH TITLE.";
    }
    */
    
}

package com.mateek.coursera.rating;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mateek.coursera.Movie;
import com.mateek.coursera.MovieDatabase;
import com.mateek.coursera.filters.Filter;
import com.mateek.coursera.filters.TrueFilter;
import com.mateek.coursera.rater.Rater;
import com.mateek.coursera.rater.RaterDatabase;

public class FourthRatings {
        
    public FourthRatings() {
        // default constructor
        this( "C:\\\\Users\\mateek\\Downloads\\coursera\\StepOneStarterProgram\\data\\ratings.csv");
        //this("C:\\\\Users\\mateek\\Downloads\\coursera\\StepOneStarterProgram\\data\\ratings_short.csv");
    }
    public FourthRatings(String ratingsFileLoc) {
    	RaterDatabase.initialize(ratingsFileLoc);
    	//FirstRatings firstRatings = new FirstRatings();
    	//myRaters = (ArrayList<Rater>) firstRatings.loadRaters(ratingsFileLoc);
    	
    }
    public Integer getRaterSize() {
    	return RaterDatabase.size();
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
    	ArrayList<Rater>  ratersList= RaterDatabase.getRaters();
    	Double avgRating = (double) 0;
    	for (Rater rater : ratersList) {
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
    
    /**
     * get movie avg rating
     * @param movieId
     * @return
     */
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
     * get similarities between 2 rater
     * @param mRater
     * @param rRater
     * @return Double
     */
    private Double dotProduct(Rater mRater, Rater rRater) {
    	if(mRater.getID().equals(rRater.getID()))
    		return (double) -1;
    	
    	Set<String> mRatedmovie = mRater.getItemsRated();
    	Set<String> rRatedmovie = rRater.getItemsRated();
    	Double similaritiesIndex = (double) 0;
    	Integer indexValue = 5;
    	
    	for (String movie : mRatedmovie) {
			if(rRatedmovie.contains(movie)) {
				Double mRating = mRater.getRating(movie) - indexValue;
				Double rRating = rRater.getRating(movie) - indexValue;
				similaritiesIndex += mRating * rRating ; 
			}
		}
    	return similaritiesIndex;
    }
    
    /**
     * returns an ArrayList of type Rating sorted by ratings 
     * from highest to lowest rating with the highest rating 
     * first and only including those raters who have a 
     * positive similarity rating since those with negative
     *  values are not similar in any way.
     * @param raterId
     * @return
     */
    private List getSimilarities(String raterId) {
		List ratersList = RaterDatabase.getRaters();
		List ratingList = new ArrayList<Rating>();
		Rater mRater = RaterDatabase.getRater(raterId);
		Iterator it = ratersList.iterator();
		while(it.hasNext()){
			Rater tRater = (Rater) it.next();
			Double similar = dotProduct(mRater, tRater);
			if(similar >= 0) {
				Rating rating = new Rating(tRater.getID(),similar);
				ratingList.add(rating);
			}			
		}   	
		ratingList.sort(null);
    	return ratingList;
	}
    /**
     * This method should return an ArrayList of type Rating, 
     * of movies and their weighted average ratings using only 
     * the top numSimilarRaters with positive ratings and including only 
     * those movies that have at least minimalRaters ratings from
     *  those most similar raters (not just minimalRaters ratings overall)
     * @param raterId
     * @param numSimilarRaters
     * @param minimalRaters
     * @return
     */
    public List getSimilarRatings(String raterId,Integer numSimilarRaters,Integer minimalRaters) {
    	List ratingList = new ArrayList<Rating>();
    	List similarRater = getSimilarities(raterId);// return rater id and similarity
    	for(int i = numSimilarRaters; i< similarRater.size(); ++i ) {
    		similarRater.remove(i);
    	}
    	
    	List movieList = MovieDatabase.filterBy(new TrueFilter());
    	
    	for (Object movieId : movieList) {			
			Integer ratingCount = 0;
			Double weightedRating = new Double(0);
			for (Object object : similarRater) {
				Rating rating = (Rating) object;//raterId-simiValue
				Rater rater = RaterDatabase.getRater(rating.getItem());
				Double movieRating = rater.getRating((String) movieId);
				weightedRating += movieRating * rating.getValue();
				++ratingCount;
			}
			if(ratingCount>=minimalRaters) {
				Rating rating = new Rating((String) movieId, weightedRating/ratingCount);
				ratingList.add(rating);				
			}			
		}    	
    	return ratingList;
    }
    
    
    public List getSimilarRatingsByFilter(String raterId,Integer numSimilarRaters,Integer minimalRaters, Filter filterCriteria) {
    	List ratingList = new ArrayList<Rating>();
    	List similarRater = getSimilarities(raterId);// return rater id and similarity
    	for(int i = numSimilarRaters; i< similarRater.size(); ++i ) {
    		similarRater.remove(i);
    	}
    	
    	List movieList = MovieDatabase.filterBy(filterCriteria);
    	
    	for (Object tMovie : movieList) {
			Movie movie = (Movie) tMovie;
			Integer ratingCount = 0;
			Double weightedRating = new Double(0);
			for (Object object : similarRater) {
				Rating rating = (Rating) object;//raterId-simiValue
				Rater rater = RaterDatabase.getRater(rating.getItem());
				Double movieRating = rater.getRating(movie.getID());
				weightedRating += movieRating * rating.getValue();
				++ratingCount;
			}
			if(ratingCount>=minimalRaters) {
				Rating rating = new Rating(movie.getID(), weightedRating/ratingCount);
				ratingList.add(rating);				
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

/**
 * 
 */
package com.mateek.coursera;


import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import edu.duke.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
/**
 * @author mateek
 *
 */

public class FirstRatings {
    /**
     * load the movie class data 
     * @param filePath
     * @return List of movie
     */
    public List loadMovies(String filePath){
    	List<Movie> movieList = new ArrayList<Movie>();
    	try{
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            reader.read();
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withSkipHeaderRecord(true));
            Iterator itr  = csvParser.iterator();
            itr.next();
            while(itr.hasNext()) {
            	CSVRecord csvRecord = (CSVRecord) itr.next();
            	String anID  = csvRecord.get(0);
            	String aTitle = csvRecord.get(1);
            	String aYear = csvRecord.get(2);
            	String aCountry = csvRecord.get(3);            	
            	String theGenres = csvRecord.get(4);            	
            	String aDirector = csvRecord.get(5);
            	int theMinutes = Integer.parseInt(csvRecord.get(6));
            	String aPoster = csvRecord.get(7);
            	
            	
            	Movie movie = new Movie(anID, aTitle, aYear, theGenres, aDirector, aCountry, aPoster, theMinutes);
            	movieList.add(movie);
            }
        }catch(Exception e) {
        	System.out.println("Error :" + e.getMessage());
        }
        return movieList;
    }
    
    /**
     * load the rater file data
     * @param filePath
     * @return list of rater
     */
    public List loadRaters(String filePath) {
    	List<Rater> raterList = new ArrayList<Rater>();
    	Map<String,Rater> raterMap = new HashMap<String, Rater>();
    	try{
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            reader.read();
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withSkipHeaderRecord(true));
            Iterator itr  = csvParser.iterator();
            itr.next();
            while(itr.hasNext()) {
            	CSVRecord csvRecord= (CSVRecord) itr.next();
            	String raterId = csvRecord.get(0);
            	String movieId = csvRecord.get(1);
            	Double rating = Double.parseDouble(csvRecord.get(2));
            	Double time = Double.parseDouble(csvRecord.get(3));
            	Rater rater = raterMap.get(raterId);
            	if(rater == null) {
            		rater = new Rater(raterId);
            	}
            	rater.addRating(movieId, rating);  
            	raterMap.put(raterId, rater);
                        	
            }
            Set<String> keySet = raterMap.keySet();
            for (String key : keySet) {
            	Rater rater = raterMap.get(key);
				raterList.add(rater);						
			}
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	return raterList;
    }
    
    /**
     * test the movies class function
     */
    public void testLoadMovies() {
    	
    	//String filePath = "C:\\\\Users\\mateek\\Downloads\\coursera\\StepOneStarterProgram\\data\\ratedmovies_short.csv";
    	String filePath = "C:\\\\Users\\mateek\\Downloads\\coursera\\StepOneStarterProgram\\data\\ratedmoviesfull.csv";
    	List movieList = loadMovies(filePath);
    	
    	System.out.println("Number of Movie =" + movieList.size());
    	
    	int gen = listOfMovieWithGivenGenre(movieList, "Comedy");
    	System.out.println("Number of Comedy genre =" + gen);
    	
    	int mini = listOfMoviesGreterThenGivenLength(movieList, 150);
    	System.out.println("Number of lenth > 150 =" + mini);
    	
    	List<String> dirCount = maxNoOfMovieByAnyDirector(movieList);
    	System.out.println("maximum number of movies by any director =" + dirCount.get(0));
    	System.out.println("maximum number of movies by any director name =" + dirCount.get(1));
    	
    	Set<String> dirName =  allDirector(movieList);
    	System.out.println("Number of director=" + dirName.size());
//    	for (String string : dirName) {
//    		System.out.println("Number of director=" + string.toString());
//		}
    	
    	//Iterator itr = movieList.iterator();
    	/* print all movie name
    	while(itr.hasNext()) {
    		Movie movie = (Movie) itr.next();
    		System.out.println(movie.toString());
    	}
    	*/  
    	
    }
    
    /**
     * test the rater class function
     */
    public void testLoadRaters() {
    	
    	//String filePath = "C:\\\\Users\\mateek\\Downloads\\coursera\\StepOneStarterProgram\\data\\ratings_short.csv";
    	String filePath = "C:\\\\Users\\mateek\\Downloads\\coursera\\StepOneStarterProgram\\data\\ratings.csv";
    	
    	List ratersList = loadRaters(filePath);
    	System.out.println("total no of rater = "  + ratersList.size());
    	
    	int ratingCount = ratingCountByGivenRater(ratersList, 193);
    	System.out.println("rating count by rater "+ "193 = "  + ratingCount );
    	
    	List<String> maxRaterId = maxNoOFRatingGivenBy(ratersList);
    	for (String string : maxRaterId) {
    		System.out.println("max rating given by = "  + string );
		}
    	
    	List movieRating = ratingOfMovie(ratersList, "1798709" );
    	System.out.println("total number of rating movie  = "  + movieRating.size() );
//    	for (Object object : movieRating) {
//    		System.out.println("rating of 1798709 = "  + object.toString() );
//		}
    	List ratedMovieList = ratedMovieList(ratersList);
    	System.out.println("total number rated movie  = "  + ratedMovieList.size() );
//    	for (Object object : ratedMovieList) {
//    		System.out.println("all rated movie list = "  + object.toString() );
//		}
    	
    	/*
    	Iterator itr = ratersList.iterator();
    	while(itr.hasNext()) {
    		Rater rater = (Rater) itr.next();
    		String ID  = rater.getID();
    		System.out.print(ID);
    		List itemRated = rater.getItemsRated();
    		for (Object object : itemRated) {
				Double rating  = rater.getRating((String)object);
				System.out.print(" " + object.toString() + " " + rating + ",");
			}
    		System.out.println();
    	}
    	*/
    }
    
    /**
     * return the number of movie with given genre
     * @param movieList
     * @param genre
     * @return Integer
     */
    public Integer listOfMovieWithGivenGenre(List movieList, String genre) {
    	int count = 0;
    	Iterator itr = movieList.iterator();
    	while(itr.hasNext()) {
    		Movie movie = (Movie) itr.next();
    		if(movie.getGenres().contains(genre)) {
    			count+=1;
    		}
    	}
    	return count;
    }
    /**
     * return count of movies who's length grater then given length 
     * @param movieList
     * @param length
     * @return Integer
     */
    public Integer listOfMoviesGreterThenGivenLength(List movieList, Integer length) {
    	int count = 0;
    	Iterator itr = movieList.iterator();
    	while(itr.hasNext()) {
    		Movie movie = (Movie) itr.next();
    		if(movie.getMinutes() >length) {
    			count+=1;
    		}
    	}
    	return count;		
	}
    
    /**
     * return max no of movies directed by any director
     * @param movieList
     * @return Integer
     */
    public List maxNoOfMovieByAnyDirector(List movieList){
    	HashMap<String, Integer> directorMovie = new HashMap();
    	Iterator itr = movieList.iterator();
    	
    	while(itr.hasNext()) {
    		Movie movie = (Movie) itr.next();
    		String directors = movie.getDirector();
    		String[] directorStrings = directors.split(","); 
    		for (String string : directorStrings) {
    			Integer dirMovieCount = directorMovie.get(string);
    			dirMovieCount = dirMovieCount ==null ? 0 : dirMovieCount; 
    			directorMovie.put(string, dirMovieCount+1);    			
    		}
    	}
    	Set<String> keySet = directorMovie.keySet();
    	Integer maxCount = 0;
    	String dirName = "";
    	for (String object : keySet) {
			Integer count = directorMovie.get(object);
			if(maxCount< count) {
				maxCount = count;
				dirName =object;
			}
		}
    	List<String> result = new ArrayList<String>();
    	result.add(maxCount.toString());
    	result.add(dirName);
    	return result;
    }
    /**
     * return all director name 
     * @param movieList
     * @return Set<String>
     */
    private Set<String> allDirector(List movieList){
    	Set<String> directorSet = new HashSet<String>();
    	Iterator itr = movieList.iterator();
    	while(itr.hasNext()) {
    		Movie movie = (Movie) itr.next();
    		String director = movie.getDirector();
    		String[] directorStrings = director.split(","); 
    		for (String string : directorStrings) {
				directorSet.add(string);
			}
    		
    	}
    	return directorSet;    	
    }
    /**
     * return count of rating given by given user
     * @param raters
     * @param raterId
     * @return Integer
     */
    public Integer ratingCountByGivenRater(List raters,Integer raterId) {
    	Iterator itr  = raters.iterator();
    	
    	while(itr.hasNext()) {
    		Rater rater = (Rater) itr.next();
    		if(rater.getID().equals(raterId.toString())){
    			List rating = rater.getItemsRated();
    			return rating.size();
    		}
    	}
    	return 0;
    }
    
    /** return list rater id who give max no of rating
     * @param raterList
     * @return list of string
     */
    public List maxNoOFRatingGivenBy(List raterList) {
    	Iterator itr = raterList.iterator();
    	List<String> fianlRaterList = new ArrayList<String>();
    	Integer maxRating = 0 ;
    	while(itr.hasNext()) {
    		Rater rater = (Rater) itr.next();
    		Integer itemRated = rater.getItemsRated().size();
    		if(itemRated > maxRating) {
    			maxRating = itemRated;
    			fianlRaterList.clear();
    			fianlRaterList.add(rater.getID());
    		}else if (itemRated == maxRating) {
    			fianlRaterList.add(rater.getID());				
			}    		
    	}
    	return fianlRaterList;
    }
    
    /**
     * return the avg movie rating given by rater
     *
     * @param raterList
     * @param movieID
     * @return Double
     */
    public List<Double> ratingOfMovie(List raterList, String movieID) {
    	List<Double> movieRatingList = new ArrayList<Double>();
    	Iterator itr = raterList.iterator();
    	while(itr.hasNext()) {
    		Rater rater = (Rater) itr.next();
    		Double movieRating = rater.getRating(movieID);
    		if(movieRating != -1) {
    			movieRatingList.add(movieRating);
    		}
    	}
    	Double ratingSum = (double) 0;
    	for (Double double1 : movieRatingList) {
    		ratingSum += double1;			
		}
    	return movieRatingList;
//    	Double movieRating = ratingSum/movieRatingList.size();
//    	return movieRating;
    }
    /**
     * all movie list rated by rater
     * @param raterList
     * @return movieSet
     */
    public List<String> ratedMovieList(List raterList){
    	Iterator itr = raterList.iterator();
    	Set<String> ratedMovieSet = new HashSet<String>();
    	while(itr.hasNext()) {
    		Rater rater = (Rater) itr.next();
    		List<String> itemList = rater.getItemsRated();
    		ratedMovieSet.addAll(itemList);
    	}
    	List<String> mr = new ArrayList<>(ratedMovieSet);
    	// return set value 
    	return mr;
    }
}

/**
 * 
 */
package com.mateek.coursera;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * @author mateek
 *
 */
public class MovieDatabase {
	private static HashMap<String, Movie> movieMap ;//= new HashMap<String, Movie>();
	
	public static void init(String movieFilePath) {
		if(movieFilePath!= null && !movieFilePath.isEmpty())
			loadMovies(movieFilePath);
		else
			init();
	}
	private static void init() {
		String filePath = "C:\\\\Users\\mateek\\Downloads\\coursera\\StepOneStarterProgram\\data\\ratedmoviesfull.csv";
		loadMovies(filePath);
	}
	private static void loadMovies(String filePath) {
    	
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
            	movieMap.put(anID, movie);
            }
        }catch(Exception e) {
        	System.out.println("Error :" + e.getMessage());
        }
    }
	/**
	 * returns true if the id is a movie in the database, and false otherwise
	 * @param movieId
	 * @return
	 */
	public static Boolean containsID(String movieId) {
		Movie movie= movieMap.get(movieId);
		if(movie != null)
			return true;		
		return false;
	}
	
	public static Integer getYear(String movieId) {
		Movie movie= movieMap.get(movieId);
		if(movie != null)
			return movie.getYear();
		return -1;
	}
	public static String getTitle(String movieId) {
		Movie movie= movieMap.get(movieId);
		if(movie != null)
			return movie.getTitle();
		return "Movie not found in DB";
		
	}
	public static String getMovie(String movieId) {
		Movie movie= movieMap.get(movieId);
		if(movie != null)
			return movie.getTitle();
		return "Movie not found in DB";
		
	}
	public static String getPoster(String movieId) {
		Movie movie= movieMap.get(movieId);
		if(movie != null)
			return movie.getPoster();
		return "Movie not found in DB";
	}
	public static int getMinutes(String movieId) {
		Movie movie= movieMap.get(movieId);
		if(movie != null)
			return movie.getMinutes();
		return -1;
	}
	public static String getCountry(String movieId) {
		Movie movie= movieMap.get(movieId);
		if(movie != null)
			return movie.getCountry();
		return "Movie not found in DB";
	}
	public static List<String> getGenres(String movieId) {
		Movie movie= movieMap.get(movieId);
		if(movie != null) {
			String[] movieGenres = null;
			String genres = movie.getGenres();
			movieGenres = genres.split(",");
			List<String> genresList = new ArrayList<String>();
			for (String string : movieGenres) {
				genresList.add(string);
			}
			return genresList;
		}
		return null;
		
	}
	public static  List<String> getDirector(String movieId) {
		Movie movie= movieMap.get(movieId);
		if(movie != null) {
			String[] movieGenres = null;
			String genres = movie.getDirector();
			movieGenres = genres.split(",");
			List<String> genresList = new ArrayList<String>();
			for (String string : movieGenres) {
				genresList.add(string);
			}
			return genresList;
		}
		return null;
	}
	public static Integer size() {
		return movieMap.size();
	}
	public static List<String> filterBy(Filter f) {
		
	}
	
}
 
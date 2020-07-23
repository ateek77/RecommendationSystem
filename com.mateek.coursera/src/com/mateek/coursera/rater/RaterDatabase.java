package com.mateek.coursera.rater;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class RaterDatabase {
    private static HashMap<String,Rater> ourRaters;
     
	private static void initialize() {
	    // this method is only called from addRatings 
		if (ourRaters == null) {
			ourRaters = new HashMap<String,Rater>();
		}
	}

    public static void initialize(String filename) {
 		if (ourRaters == null) {
 			ourRaters= new HashMap<String,Rater>();
 			try { 				
				addRatings(filename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 		}
 	}	
 	
    public static void addRatings(String filePath) throws IOException {
        initialize(); 
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        reader.read();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withSkipHeaderRecord(true));
        //CSVParser csvp = fr.getCSVParser();
        Iterator itr  = csvParser.iterator();
        itr.next();
        while(itr.hasNext()) {
        	CSVRecord rec= (CSVRecord) itr.next();
        	String id = rec.get(0);
            String item = rec.get(1);
            String rating = rec.get(2);
            addRaterRating(id,item,Double.parseDouble(rating));                   	
        }
    }
    
    /**
     * This function can be used to add one rater and their movie rating to the database
     * @param raterID
     * @param movieID
     * @param rating
     */
    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize(); 
        Rater rater =  null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID); 
        } 
        else { 
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID,rater);
         }
         rater.addRating(movieID,rating);
    } 
	         
    /**
     * returns a Rater that has this ID.
     * @param id
     * @return
     */
    public static Rater getRater(String id) {
    	initialize();
    	
    	return ourRaters.get(id);
    }
    
    /**
     * This method returns an ArrayList of Raters from the database.
     * @return
     */
    public static ArrayList<Rater> getRaters() {
    	initialize();
    	ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());
    	
    	return list;
    }
 
    public static int size() {
	    return ourRaters.size();
    }
    
    
        
}

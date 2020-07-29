package com.mateek.coursera;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.mateek.coursera.filters.TrueFilter;
import com.mateek.coursera.rater.RaterDatabase;
import com.mateek.coursera.rating.FourthRatings;
import com.mateek.coursera.rating.Rating;

public class RecommendationRunner implements Recommender {

	@Override
	public List getItemsToRate() {
		List movieList = MovieDatabase.filterBy(new TrueFilter());
		List finalList = new ArrayList<String>();
		Integer finalListSize = 40;
		for(int i=0 ; i < finalListSize ; ++i) {
			finalList.add(movieList.get(i));
		}		
		return finalList;
	}

	@Override
	public void printRecommendationsFor(String webRaterID) {
		FourthRatings fourthRatings = new FourthRatings(); // Initialized the rating database
		
		Scanner sc = new Scanner(System.in);
		Boolean enterNext = true;
		System.out.println("enter comma separated movieID and rating value  ");
		while(enterNext){
			enterNext = false;
			String movieID ;
			Double rating ; 
			String[] input = sc.nextLine().split(",");
			movieID = input[0];
			rating = Double.parseDouble(input[1]);
			RaterDatabase.addRaterRating(webRaterID, movieID, rating);
			System.out.println("YES: if you want to rate any other movie else NO");
			if("YES".equals(sc.nextLine()))
				enterNext = true;
		}			
		
		List movieRatingList = fourthRatings.getSimilarRatings(webRaterID, 3 , 1);
		List finalMovieList = new ArrayList<String>();
		Set userRatedMovieId = RaterDatabase.getRater(webRaterID).getItemsRated();
		for(int i =0;i < movieRatingList.size() && 20 > finalMovieList.size(); ++i) {
			Rating movieRating = (Rating) movieRatingList.get(i);
			if(userRatedMovieId.contains(movieRating.getItem()))
				continue;
			finalMovieList.add(movieRating.getItem());
		}
		printHTMLFile(finalMovieList);
//		System.out.println(" \n RECOMMENDED movie LIST :");
//		for (Object object : finalMovieList) {
//			System.out.println(MovieDatabase.getMovie((String) object));
//		}
		
	}
	private void printHTMLFile(List movieList) {
		String HTML_page  = "<html><head><title>Recommendation Movie</title></head>"
				+ "<body><p><table>" + 
				"  <tr><th>Recommended Movie List</th></tr>"
				+ "";
		for (Object object : movieList) {
			HTML_page += "<tr><td>" + MovieDatabase.getMovie((String) object) +"</td></tr>";
		}		
		HTML_page += "</table></body></html>";	
		System.out.println(HTML_page);
	}
	

}

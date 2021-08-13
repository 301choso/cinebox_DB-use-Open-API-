package com.tj;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader; 
import java.io.IOException; 

public class ApiExplorer { 
	
	public static void main(String[] searchWord) throws IOException {

		BufferedReader rd=null; 
		HttpURLConnection conn = null;
		
		try{
			for(int j=0;j<searchWord.length;j++) {
			URL url = new URL("http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/"
					+ "search_json2.jsp?collection=kmdb_new2&detail=Y&ServiceKey=서비스키&listCount=3&title="+searchWord[j]);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET"); 
			conn.setRequestProperty("Content-type", "application/json"); 
			System.out.println("Response code: " + conn.getResponseCode()); 
		
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) { 
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream())); 
			} 
			
			StringBuilder sb = new StringBuilder(); 
			String result="";
		
			while ((result = rd.readLine()) != null) {
					sb.append(result); 
			}
			
			JSONParser jsonParser = new JSONParser();
		
        	JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
        	System.out.println(jsonObject);
        
        	JSONArray DataResult = (JSONArray)jsonObject.get("Data");
        	JSONObject _DataResult = (JSONObject)DataResult.get(0);
        	JSONArray _Result = (JSONArray)_DataResult.get("Result");
        	
        	for(int k=0;k<_Result.size();k++) {
        	JSONObject _Result2 = (JSONObject)_Result.get(k);
        	
        	String title =  (String)_Result2.get("title");  
        	String nation = (String)_Result2.get("nation"); 
        	String runtime =  (String)_Result2.get("runtime"); 
        	
        	JSONObject plots = (JSONObject)_Result2.get("plots");
        	JSONArray _plots = (JSONArray)plots.get("plot");
        	JSONObject plot =  (JSONObject)_plots.get(0);
        	String plotText = (String)plot.get("plotText");
        	
        	
        	JSONObject directors = (JSONObject)_Result2.get("directors");
        	JSONArray _directors = (JSONArray)directors.get("director");
        	JSONObject director =  (JSONObject)_directors.get(0);
        	String directorNm = (String)director.get("directorNm");
        	
        	
        	JSONObject actors = (JSONObject)_Result2.get("actors");
        	JSONArray _actors = (JSONArray)actors.get("actor");
        	JSONObject actor =  (JSONObject)_actors.get(0);
        	String actorNm = (String)actor.get("actorNm");
        	
        		int end =0;
	        	if(_actors.size()>=4) {
	        		end = 4;
	        	}else {
	        		end = _actors.size();
	        	}
	        	
	        	for(int i=1;i<end;i++) {
	        		actor =  (JSONObject)_actors.get(i);
	        		 actorNm += ","+ (String)actor.get("actorNm");
	        	}
        	
        	String genre =  (String)_Result2.get("genre");
      
        	
        	JSONObject ratings = (JSONObject)_Result2.get("ratings");
        	JSONArray _ratings = (JSONArray)ratings.get("rating");
        	JSONObject rating =  (JSONObject)_ratings.get(0);
        	String ratingGrade = (String)rating.get("ratingGrade");
        	
        	String releaseDate = (String)rating.get("releaseDate");
        	
        	String posters =  (String)_Result2.get("posters"); 
        	String stlls =  (String)_Result2.get("stlls"); 
        	
        	System.out.println(" "+title);
        	System.out.println(""+nation);
        	System.out.println(""+runtime);
        	System.out.println(""+directorNm);
        	System.out.println(""+actorNm);
        	System.out.println(" "+plotText);
        	System.out.println(""+genre);
        	System.out.println(""+ratingGrade);
        	System.out.println(" "+releaseDate);
        	System.out.println(""+posters);
        	System.out.println(""+stlls);
        	

        	List<DTO> dtos = new ArrayList<DTO>();
        		
        	DTO dto = new DTO();   	

		       	//set
        		dto.setMovie_sort(genre);
        		dto.setMovie_title(title);
        		dto.setMovie_director(directorNm);
        		dto.setMovie_actor(actorNm);
        		dto.setMovie_age_grade(ratingGrade);
        		dto.setMovie_runningtime(runtime);
        		dto.setMovie_country(nation);
        		dto.setMovie_open_date(releaseDate);
        		dto.setMovie_content(plotText);
        		dto.setPosterUrl(posters);
        		dto.setStillUrl(stlls);
        		if(dto !=null) dtos.add(dto);
        		
        		
        		DAO dao = new DAO();
        		dao.insertMovie(dtos);
        	}
        	}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		rd.close(); 
			conn.disconnect(); 
    	}
    }


	
}

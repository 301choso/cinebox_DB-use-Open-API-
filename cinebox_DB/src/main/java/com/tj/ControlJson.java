package com.tj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class ControlJson {
    public static void main2(String[] a) {
    	
	    	// 인증키 (개인이 받아와야함)
	    	String key = "";

	    	// 파싱한 데이터를 저장할 변수
	    	String result = "";

	    	try {
	    		for(int i=0;i<a.length;i++) {
	    		URL url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key="
	    				+ key + "&movieCd="+a[i]);

	    		BufferedReader bf;

	    		bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

	    		result = bf.readLine();

	        	JSONParser jsonParser = new JSONParser();
	        	JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
	        	System.out.println(result);
	        	
	        	JSONObject movieInfoResult = (JSONObject)jsonObject.get("movieInfoResult");
	        	JSONObject movieInfo = (JSONObject)movieInfoResult.get("movieInfo");

	        	List<DTO> dtos = new ArrayList<DTO>();
	        		
	        	DTO dto = new DTO();
	        	
	        	//genres
	        	JSONArray genres = (JSONArray)movieInfo.get("genres");
	        	String genreNm="";
	        	if(genres.size()>0) {
	        	
	        	
	        	JSONObject genres_genreNm0 = (JSONObject)genres.get(0);
	        	 genreNm = (String) genres_genreNm0.get("genreNm");
	        		
	        	for(int k = 1; k < genres.size(); k++) {
	        		JSONObject genres_genreNm = (JSONObject)genres.get(k);
	            	genreNm += ","+genres_genreNm.get("genreNm");
	        		}
	        	}
	        	
	        	//directors
	        	JSONArray directors = (JSONArray)movieInfo.get("directors");
	        	String directors_peopleNm = "";
	        	if(directors.size()>0) {
		        	JSONObject directors_peopleNm2 = (JSONObject)directors.get(0);
		        	directors_peopleNm = (String) directors_peopleNm2.get("peopleNm");
		        	System.out.println("감독명 : " + directors_peopleNm2.get("peopleNm"));
	        	}
	        	
	        	//actors
	        	String peopleNm = "";
	        	
	        	JSONArray actors = (JSONArray)movieInfo.get("actors");	        	
		       	if(actors.size()>0) {
		       		JSONObject actors_peopleNm0 = (JSONObject)actors.get(0);
		       		peopleNm = (String) actors_peopleNm0.get("peopleNm");
		       		
		       		if(actors.size()<4) {
		       			for(int m = 1; m < actors.size(); m++) { 
			        		JSONObject actors_peopleNm = (JSONObject)actors.get(m);
			        		peopleNm += ","+actors_peopleNm.get("peopleNm");
			        	}
		       		}else {
		       			for(int m = 1; m < 4; m++) { 
			        		JSONObject actors_peopleNm = (JSONObject)actors.get(m);
			        		peopleNm += ","+actors_peopleNm.get("peopleNm");
			        	}
		       		}
		       	}
		       	
	        	//watchGradeNm
	        	JSONArray audits = (JSONArray)movieInfo.get("audits");	        	
	        	String watchGradeNm="";	        	
	        	if(audits.size()>0) {		        	
	        		JSONObject watchGradeNm2=(JSONObject)audits.get(0);
	        		watchGradeNm= (String) watchGradeNm2.get("watchGradeNm");
		        	System.out.println("등급 : " + (String) watchGradeNm2.get("watchGradeNm"));
	        	}
	        	
		      //nations
	        	JSONArray nations = (JSONArray)movieInfo.get("nations");
	        	JSONObject nations_nationNm = null;
	        	if(!nations.isEmpty()) {
	        		nations_nationNm = (JSONObject)nations.get(0);
	        		System.out.println("제작국가명 : " + nations_nationNm.get("nationNm"));
	        	}
		       		//print
			       	System.out.println("영화코드 : " + movieInfo.get("movieCd"));
		        	System.out.println("영화명(한글) : " + movieInfo.get("movieNm"));
		        	System.out.println("재생시간 : " + movieInfo.get("showTm"));
		        	System.out.println("개봉일 : " + movieInfo.get("openDt"));
			       	System.out.println("출연배우 : " + peopleNm);
			       	System.out.println("장르 : " + genreNm);
			       	
			       	//set
	        		dto.setMovie_sort(genreNm);
	        		dto.setMovie_title((String) movieInfo.get("movieNm"));
	        		dto.setMovie_director(directors_peopleNm);
	        		dto.setMovie_actor(peopleNm);
	        		dto.setMovie_age_grade(watchGradeNm);
	        		dto.setMovie_runningtime((String) movieInfo.get("showTm"));
	        		dto.setMovie_country((String) nations_nationNm.get("nationNm"));
	        		if(dto !=null) dtos.add(dto);
	        		
	        		
	        		DAO dao = new DAO();
	        		dao.insert(dtos);
	        	}
	        	
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}

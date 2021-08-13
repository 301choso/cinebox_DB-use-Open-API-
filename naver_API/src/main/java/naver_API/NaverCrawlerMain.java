package naver_API;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map; 
 
public class NaverCrawlerMain { 
	
	public static void main(String searchWord) {
		
		String id = "키1"; 
		String secret = "키2";
	try {
		NaverCrawler crawler = new NaverCrawler(); //생성자 생성
		String url = URLEncoder.encode(searchWord, "UTF-8");
		String response = crawler.search(id, secret, url); //인자 전달
		String[] fields = {"title", "link","image"}; //가져올 값
		Map<String, Object> result = crawler.getResult(response, fields); //함수에 값전달하면서 호출 , 가져온 값 담음
		
			if(result.size() > 0) {
				System.out.println("total -> " + result.get("total")); 
			}
		
		List<Map<String, Object>> items = (List<Map<String, Object>>) result.get("result");
		
			for(Map<String, Object> item : items) { 
				System.out.println("===================================================="); 
			for(String field : fields) 
				System.out.println(field + "->" + item.get(field)); 
			} 
		} catch (Exception e) {	
			e.printStackTrace();	
		} 
	} 
}

		


package com.tj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	Connection con;
	PreparedStatement psmt;
	ResultSet res;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	
	public void insert(List<DTO> dto) throws SQLException  {	
		try {	
		Class.forName(driver);
		con=DriverManager.getConnection(url, "tjsw", "tj311");
		String sql = "insert into cinebox_movie(movie_id, movie_sort, movie_title, movie_director, movie_actor, "
				+ "movie_age_grade,movie_runningtime, movie_country, movie_content, movie_status,"
				+" movie_admin_id) values(movie_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
		List<DTO> dto2 = new ArrayList<DTO>();
				for (int i = 0; i < dto.size(); i++) {
					DTO str = dto.get(i);
			
			psmt=con.prepareStatement(sql);
			psmt.setString(1, str.getMovie_sort()); 
			psmt.setString(2, str.getMovie_title());
			psmt.setString(3, str.getMovie_director());
			psmt.setString(4, str.getMovie_actor()); 
			psmt.setString(5, str.getMovie_age_grade());
			psmt.setString(6,str.getMovie_runningtime());
			psmt.setString(7, str.getMovie_country()); 
			psmt.setString(8, "none"); 
			psmt.setString(9, "none");  
			psmt.setString(10, "Api"); 
			psmt.executeUpdate();

			}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}finally {
		if(psmt!=null)psmt.close();
		if(con!=null)con.close();
		
			}
	}
}

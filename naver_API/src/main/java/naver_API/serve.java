package naver_API;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/serve")
public class serve extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public serve() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("searchWord"));
		String searchWord = request.getParameter("searchWord");
		NaverCrawlerMain ncm = new NaverCrawlerMain();
		ncm.main(searchWord);
	}
}

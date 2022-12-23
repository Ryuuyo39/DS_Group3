

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BmiB
 */
@WebServlet("/Server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Server() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /*
     * 第二種做法:Bmi頁面接get request&送post表單
     * BmiResult頁面回應post request丟過來的表單
     */
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 回應get request&送post表單
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("Singer")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Song.jsp").forward(request, response);
			return;
		}
		GetInput input = new GetInput(request.getParameter("name"),request.getParameter("Singer"),request.getParameter("Producer"));
		
		
//		GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
//		HashMap<String, String> query = google.query();
//		
//		String[][] s = new String[query.size()][2];
//		request.setAttribute("query", s);
//		int num = 0;
//		for(Entry<String, String> entry : query.entrySet()) {
//		    String key = entry.getKey();
//		    String value = entry.getValue();
//		    s[num][0] = key;
//		    s[num][1] = value;
//		    num++;
//		}
//		request.getRequestDispatcher("googleitem.jsp")
//		 .forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

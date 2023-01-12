import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Server() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (request.getParameter("Singer") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Song.jsp").forward(request, response);
			return;
		}
		
		GetInput input = new GetInput(request.getParameter("name"), request.getParameter("Singer"));

		GoogleCaller google = new GoogleCaller(input);

		ArrayList<Website> list = google.getResult();
		String[][] s = new String[25][2];
		int num = 0;
		try {
			for (Website entry : list) {
				String name = entry.name;
				String url = entry.url;

				s[num][0] = name;
				s[num][1] = url;
				num++;
			}
		}catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			request.setAttribute("query", s);
			request.getRequestDispatcher("SongResult.jsp").forward(request, response);
		}
		request.setAttribute("query", s);
		request.getRequestDispatcher("SongResult.jsp").forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

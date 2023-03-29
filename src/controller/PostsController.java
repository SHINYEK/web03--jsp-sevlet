package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//simple라이브러리에 있는 객체
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.PostDAO;
import model.PostVO;

/**
 * Servlet implementation class PostsController
 */
@WebServlet(value={"/posts","/posts/insert","/posts/update","/posts/delete","/posts/read","/posts/json"})
public class PostsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostDAO dao = new PostDAO();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글자깨짐 방지 (json)
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		RequestDispatcher dis = request.getRequestDispatcher("/home.jsp");
		switch(request.getServletPath()) {
		case "/posts":
//			request.setAttribute("total", dao.total());
//			request.setAttribute("list", dao.list());
			request.setAttribute("pageName", "/post/list.jsp");
			dis.forward(request, response);
			break;
		case "/posts/json":
			//jArray에는 jsonobject로 넣어야 해서 vo를 변환해서 담아줌
			//핸들바는 json데이터로 출력할 수 있다.
			int page = Integer.parseInt(request.getParameter("page"));
			
			JSONArray jArray = new JSONArray();
			JSONObject object = new JSONObject();
			for(PostVO vo : dao.list(page)){
				JSONObject obj = new JSONObject();
				obj.put("id", vo.getId());
				obj.put("title", vo.getTitle());
				obj.put("writer", vo.getWriter());
				obj.put("date", vo.getDate().toString());
				jArray.add(obj);
			}
			object.put("total", dao.total());
			object.put("list",jArray);
			out.println(object);
			
			break;
		case "/posts/read":
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("post", dao.read(id));
			request.setAttribute("pageName", "/post/read.jsp");
			dis.forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
	}

}

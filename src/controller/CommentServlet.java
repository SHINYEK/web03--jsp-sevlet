package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.CommentDAO;
import model.CommentVO;

@WebServlet(value={"/comments.json","/comments/insert","/comments/delete"})

public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CommentDAO dao = new CommentDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
			//json데이터 출력하기 위해 printWriter, 인코딩 먼저 해야한다.
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			int postid = Integer.parseInt(request.getParameter("postid"));
			ArrayList<CommentVO> array = dao.list(postid);
			JSONObject object = new JSONObject();
			JSONArray jArray = new JSONArray();
			
			object.put("list", jArray);
			for(CommentVO vo: array) {
				JSONObject obj = new JSONObject();
				obj.put("id", vo.getId());
				obj.put("body", vo.getBody());
				obj.put("writer", vo.getWriter());
				obj.put("uname", vo.getUname());
				obj.put("date", sdf.format(vo.getDate()));
				jArray.add(obj);
			}
			out.println(object);
		}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		switch(request.getServletPath()) {
		case "/comments/insert":
			CommentVO vo = new CommentVO();
			vo.setBody(request.getParameter("body"));
			vo.setWriter(request.getParameter("writer"));
			vo.setPostid(Integer.parseInt(request.getParameter("postid")));
			dao.insert(vo);
			break;
		}
	}

}

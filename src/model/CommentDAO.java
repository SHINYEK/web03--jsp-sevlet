package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CommentDAO {
	//´ñ±Û µî·Ï
	public void insert(CommentVO vo) {
		try {
			String sql = "insert into comments(body, writer, postid) values(?,?,?)";
			PreparedStatement ps = DB.CON.prepareStatement(sql);
			ps.setString(1, vo.getBody());
			ps.setString(2, vo.getWriter());
			ps.setInt(3, vo.getPostid());
			ps.execute();
		}catch(Exception e) {
			System.out.println("´ñ±Ûµî·Ï¿À·ù"+e.toString());
		}
	}

	//´ñ±Û ¸ñ·ÏÃâ·Â
	public ArrayList<CommentVO> list(int postid){
		ArrayList<CommentVO> array = new ArrayList<CommentVO>();
		try {
			String sql = "select * from view_comments where postid=? order by id desc";
			PreparedStatement ps = DB.CON.prepareStatement(sql);
			ps.setInt(1, postid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CommentVO vo = new CommentVO();
				vo.setId(rs.getInt("id"));
				vo.setBody(rs.getString("body"));
				vo.setWriter(rs.getString("writer"));
				vo.setUname(rs.getString("uname"));
				vo.setDate(rs.getTimestamp("date"));
				array.add(vo);
			}
			
		}catch(Exception e) {
			System.out.println();
		}
		return array;
	}
}

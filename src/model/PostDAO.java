package model;

import java.util.*;
import java.sql.*;


public class PostDAO {
	//게시글정보
	public PostVO read(int id) {
		PostVO vo = new PostVO();
		try {
			String sql = "select * from posts where id =?";
			PreparedStatement ps = DB.CON.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {			
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setDate(rs.getTimestamp("date"));
				vo.setBody(rs.getString("body"));
			}
		}catch(Exception e) {
			System.out.println("게시글 정보오류"+e.toString());
		}
		return vo;
	}
	//전체 게시글 수
	public int total() {
		int total = 0;
		try {
			String sql = "select count(*) total from posts";
			PreparedStatement ps = DB.CON.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				total = rs.getInt("total");
			}
		}catch(Exception e){
			System.out.println("게시글수오류"+e.toString());
		}
		return total;
		
	}
	//게시글목록
	public ArrayList<PostVO> list(int page){
		ArrayList<PostVO> array = new ArrayList<PostVO>();
		try {
			String sql = "select * from posts order by id desc limit ?,10";
			PreparedStatement ps = DB.CON.prepareStatement(sql);
			ps.setInt(1, (page-1)*10);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PostVO vo = new PostVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setDate(rs.getTimestamp("date"));
				array.add(vo);
			}
		}catch(Exception e) {
			System.out.println("게시글 목록오류"+e.toString());
		}
		return array;
	}
}

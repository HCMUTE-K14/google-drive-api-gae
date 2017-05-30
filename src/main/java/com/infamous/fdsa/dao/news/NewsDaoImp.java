package com.infamous.fdsa.dao.news;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.infamous.fdsa.bean.News;
import com.infamous.fdsa.dao.DatabaseHelper;

public class NewsDaoImp implements NewsDao {
	DatabaseHelper database;
	
	 public NewsDaoImp() {
		// TODO Auto-generated constructor stub
		 
		 database=DatabaseHelper.getInstance();
	}

	@Override
	public int addNews(News model) throws SQLException {
		String sql = "";
		sql = "INSERT INTO news(id,title,content,attactlink) values(?,?,?,?)";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.database.connection.prepareStatement(sql);
			pre.setString(1, model.getId());
			pre.setString(2, model.getTitle());
			pre.setString(3, model.getContent());
			pre.setString(4, model.getAttactLink());

		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeUpdate();
	}

	@Override
	public ResultSet findNewsById(String id) throws SQLException {
		String sql = "";
		sql = "Select * from news where id=?";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.database.connection.prepareStatement(sql);
			pre.setString(1, id);
	

		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeQuery();
	}

	@Override
	public ResultSet getAll() throws SQLException{
		String sql = "";
		sql = "select * from news";

		PreparedStatement pre = null;
		
		try {
			pre = (PreparedStatement) this.database.connection.prepareStatement(sql);
	
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}
		return pre.executeQuery();
	}
}

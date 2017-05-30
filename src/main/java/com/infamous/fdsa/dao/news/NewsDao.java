package com.infamous.fdsa.dao.news;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.infamous.fdsa.bean.News;

public interface NewsDao {

	int addNews(News model) throws SQLException;
	
	ResultSet findNewsById(String id) throws SQLException;
	
	ResultSet getAll() throws SQLException;
}

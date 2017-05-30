package com.infamous.fdsa.model.news;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.infamous.fdsa.bean.News;
import com.infamous.fdsa.dao.news.NewsDao;
import com.infamous.fdsa.dao.news.NewsDaoImp;

public class NewsModel {

	NewsDao dao;

	public NewsModel() {
		dao = new NewsDaoImp();
	}

	public int addNews(News model) throws SQLException {
		return 0;

	}

	public News findNewsById(String id) throws SQLException {
		News news = null;

		try {
			ResultSet rs = dao.findNewsById(id);

			if (rs != null) {
				if (rs.next()) {
					String _id = rs.getString(1);
					String title = rs.getString(2);
					String content = rs.getString(3);
					String downloadlink = rs.getString(4);
					news = new News(_id, title, content, downloadlink);

				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return news;
	}

	public ArrayList<News> getAll() throws SQLException {
		ArrayList<News> list = new ArrayList<>();

		try {
			ResultSet rs = dao.getAll();

			if (rs != null) {
				while (rs.next()) {
					String _id = rs.getString(1);
					String title = rs.getString(2);
					String content = rs.getString(3);
					String downloadlink = rs.getString(4);
					News news = new News(_id, title, content, downloadlink);

					list.add(news);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}

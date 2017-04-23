package com.jkxy.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.jkxy.web.model.Poetries;


/**
 * @author 诗歌查询接口
 */
public interface PoetriesDao {
	public List titleSearchList(String title) throws SQLException;
	public Poetries titleSearch(String title,Integer id) throws SQLException;
	public List authorList(Integer id,String name) throws SQLException;
	public List bodySearch(String body) throws SQLException;
}

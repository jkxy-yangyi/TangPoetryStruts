package com.jkxy.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.jkxy.web.model.Poets;


/**
 * @author 作者查询接口
 */
public interface PoetsDao {
	public Poets poet(Integer id) throws SQLException;
	public List<Poets> poetAuthor(String author) throws SQLException;
}

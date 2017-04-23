package com.jkxy.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jkxy.web.dao.PoetriesDao;
import com.jkxy.web.model.Poetries;
import com.jkxy.web.model.Poets;
import com.jkxy.web.uitl.ConnectionFactory;

public class PoetriesDaoImpl implements PoetriesDao{
	Connection conn = ConnectionFactory.getInstace().makeConnction();
	
	/**
	 * @param 标题查询诗歌
	 * @return List集合
	 */
	@Override
	public List<Poetries> titleSearchList(String title)
			throws SQLException {
		PreparedStatement ps = conn
				.prepareCall("select * from poetries where title like '%" + title + "%' ");
        List<Poetries> poes = new ArrayList<Poetries>();
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			Poetries poe = setPoetries(rs);
			Poets poests = new PoetsDaoImpl().poet(poe.getPoet_id());
			poe.setName(poests.getName());
			poes.add(poe);
		}
		setClose(ps, rs);
		return poes;
	}


	/**
	 * 标题和ID查询诗歌
	 * @param title
	 * @param id
	 * @return Poetries实体
	 */
	@Override
	public Poetries titleSearch(String title, Integer id)
			throws SQLException {
		PreparedStatement ps = conn
				.prepareCall("select * from poetries where poet_id=? and title=?");
		ps.setInt(1,id);
		ps.setString(2,title);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			Poetries poe = setPoetries(rs);
			setClose(ps, rs);
			return poe;
		}else {
			return null;
		}
	}

	/**
	 * 根据作者ID号查询所有的诗歌
	 * @param id
	 * @param name
	 * @return List集合
	 */
	@Override
	public List<Poetries> authorList(Integer id ,String name)
			throws SQLException {
		PreparedStatement ps = conn
				.prepareCall("select * from poetries where poet_id=?");
		List<Poetries> poes = new ArrayList<Poetries>();
		ps.setInt(1,id);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			Poetries poe = setPoetries(rs);
			poe.setName(name);
			poes.add(poe);
			//setClose(ps, rs);
		}
		return poes;
	}

	/**
	 * 根据诗歌内容查询
	 * @param body
	 * @return Poetries实体
	 */
	@Override
	public List<Poetries> bodySearch(String body)
			throws SQLException {
		PreparedStatement ps = conn
				.prepareCall("select * from poetries where content like '%" + body + "%' ");
		ResultSet rs=ps.executeQuery();
		List<Poetries> poes = new ArrayList<Poetries>();
		while (rs.next()) {
			Poetries poe = setPoetries(rs);
			Poets poests = new PoetsDaoImpl().poet(poe.getPoet_id());
			poe.setName(poests.getName());
			poes.add(poe);
		}
		setClose(ps, rs);
		return poes;
	}
	
	private Poetries setPoetries(ResultSet rs) throws SQLException {
		Poetries poe = new Poetries();
		poe.setId(rs.getInt(1));
		poe.setPoet_id(rs.getInt(2));
		poe.setContent(rs.getString(3));
		poe.setTitle(rs.getString(4));
		if (rs.getDate(5) != null)
			poe.setCreated_at(rs.getDate(5));
		if (rs.getDate(6) != null)
			poe.setUpdated_at(rs.getDate(6));
		return poe;
	}

	private void setClose(PreparedStatement ps, ResultSet rs)
			throws SQLException {
		rs.close();
		ps.close();
		conn.close();
	}
}

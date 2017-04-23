package com.jkxy.web.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jkxy.web.dao.impl.PoetriesDaoImpl;
import com.jkxy.web.dao.impl.PoetsDaoImpl;
import com.jkxy.web.model.Poetries;
import com.jkxy.web.model.Poets;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TangPoetryAction extends ActionSupport{
	
	public String show() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		//HttpServletRequest req = (HttpServletRequest)request;
		request.setCharacterEncoding("utf-8");
		String selects = request.getParameter("selects");
		String keyword = request.getParameter("keyword");
		String id = request.getParameter("id");  //作者id,用于标题相同时查询
		//String keyword=new String(keyword.getBytes("iso-8859-1"),"utf-8"); //修改乱码
		
		if(selects.equals("lbl_Title")){  //题目查询
			Poetries poetries=null;
			List<Poetries> poetriesList = null;
			if(id!=null && !"".equals(id)){
				try {
					poetries= new PoetriesDaoImpl().titleSearch(keyword,Integer.parseInt(id));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else {
				try {
					poetriesList = new PoetriesDaoImpl().titleSearchList(keyword);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(poetries != null){
				Poets poets;
				try {
					poets = new PoetsDaoImpl().poet(poetries.getPoet_id());
					HttpSession session = request.getSession();
					poetries.setName(poets.getName());
					session.setAttribute("poetries", poetries);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return "title";
			}else if (poetriesList.size()>0) {
				HttpSession session = request.getSession();
				session.setAttribute("poetriesList", poetriesList);
				return "title";
			}else {
				request.setAttribute("message", "查询失败,请核对查询内容是否匹配!");
				return "success";
			}
		}else if(selects.equals("lbl_Author")){	//作者查询
			HttpSession session = request.getSession();
			try {
				if(keyword == null){
					keyword = (String) session.getAttribute("name");
				}
				List poets = new PoetsDaoImpl().poetAuthor(keyword);
				if(poets != null && poets.size()>0){
					
					session.setAttribute("poets", poets);
					session.setAttribute("name", keyword);
					return "author";
				}else {
					request.setAttribute("message", "查询失败,请核对查询内容是否匹配!");
					return "success";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(selects.equals("lbl_Body")){	//诗歌内容查询
			try {
				List poetrieList = new PoetriesDaoImpl().bodySearch(keyword);
				if(poetrieList.size() > 0){
					HttpSession session = request.getSession();
					session.setAttribute("poetrieList", poetrieList);
					return "body";
				}else {
					request.setAttribute("message", "查询失败,请核对查询内容是否匹配!");
					return "success";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("message", "查询失败,请核对查询内容是否匹配!");
			return "success";
		}
		return "";
	}//List poes = new PoetriesService().poetryList();

}



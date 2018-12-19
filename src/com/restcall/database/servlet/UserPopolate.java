package com.restcall.database.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.ManagerUser;
import com.model.ModelUser;

/**
 * Servlet implementation class UserPopolate
 */
@WebServlet("/UserPopolate")
public class UserPopolate extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManagerUser managerUser = ManagerUser.getManagerUser();
		String messaggio = "";
		try{
			managerUser.popolateTable();
			messaggio = "Popolamento tabella avvenuto con successo...";
		}catch (RuntimeException e){
			messaggio = messaggio + e.getMessage().toString();
		}
		
		response.getWriter().println(messaggio);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

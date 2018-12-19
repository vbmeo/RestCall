package com.restcall.database.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.logic.ManagerDB;

/**
 * Servlet implementation class CreateDb
 */
@WebServlet("/CreateDb")
public class CreateDb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateDb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeDb = request.getParameter("nomeDb");		
		if (StringUtils.isNotBlank(nomeDb)){
			ManagerDB managerDB =  ManagerDB.getManagerDB();
			managerDB.createDb(nomeDb);
			//response.getWriter().println(jsonInString);
		}else{
			response.getWriter().println("Nome Db Da creare non specificato");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

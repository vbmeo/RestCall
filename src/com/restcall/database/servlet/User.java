package com.restcall.database.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logic.ManagerUser;
import com.model.ModelUser;
import com.util.MyUtil;

@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * DELETE
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");;
		Map<String, String> parametri =  MyUtil.getmapFromPUTStringBufferReader(data);
		String id = parametri.get("id");
		String messaggio = "";
		
		if (StringUtils.isNotBlank(id))
			if (MyUtil.isNumeric(id)){
				int intId=Integer.valueOf(id);
				ManagerUser managerUser = ManagerUser.getManagerUser();
				ModelUser modelUser = managerUser.read(intId);
				if (modelUser==null) 
					messaggio= "Utente non trovato...";
				else{
					managerUser.delete(intId);
					messaggio= "Utente " + modelUser.getNome() +" " + modelUser.getCognome() + " calcellato correttamente...";
				}
			}
		else{
			
			messaggio = "Necessario indicare id valido per selezionare l'utente...";
		}
		
		response.setContentType("text/plain");
		response.getWriter().write(messaggio);
	}

	/**
	 * READ - SELECT <br>
	 * senza parametri select *
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");	
		String messaggio = "";
		
		
		if (StringUtils.isNotBlank(id))
			if (MyUtil.isNumeric(id)){
				ManagerUser managerUser = ManagerUser.getManagerUser();
				ModelUser modelUser = managerUser.read(Integer.valueOf(id));
				if (modelUser==null) 
					messaggio= "Utente non trovato...";
				else{
					ObjectMapper mapper = new ObjectMapper();
					String userInjson = mapper.writeValueAsString(modelUser);
					//request.setAttribute("utente", userInjson);
				}
			}else
				messaggio = "Necessario indicare id valido per selezionare l'utente...";
		else{
			//passo a lista totale			
			ManagerUser managerUser = ManagerUser.getManagerUser();
			List<ModelUser> listaUser = managerUser.selectAll();
			ObjectMapper mapper = new ObjectMapper();
			String userInjson = mapper.writeValueAsString(listaUser);
			//request.setAttribute("listaUser", userInjson);
			messaggio = userInjson;
			//request.getRequestDispatcher("").forward(req, resp);
		}
		
		response.setContentType("text/plain");
		response.getWriter().write(messaggio);
	}

	/**CREATE INSERT
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");	
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		ManagerUser managerUser = ManagerUser.getManagerUser();
		ModelUser userFound = managerUser.getFromEmail(email);
		String messaggio = "";
		
		if (userFound!=null)
			messaggio = "Utente già presente con la stessa EMail (" + email  +")";
		else{
			ModelUser modelUser = new ModelUser();
			modelUser.setNome(nome);
			modelUser.setCognome(cognome);
			modelUser.setEmail(email);
			modelUser.setPassword(password);
			managerUser.create(modelUser);
			messaggio = "Utente " + nome + " inserito correttamente...";
		}
		response.setContentType("text/plain");
		response.getWriter().write(messaggio);
	}

	/**
	 * PUD - UPDATE
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");;
		Map<String, String> parametri =  MyUtil.getmapFromPUTStringBufferReader(data);
		
		String id = parametri.get("id");
		String nome = parametri.get("nome");	
		String cognome = parametri.get("cognome");
		String email = parametri.get("email");
		String password = parametri.get("password");
		String messaggio = "";
		
		if (StringUtils.isNotBlank(id))
			if (MyUtil.isNumeric(id)){
				int intId=Integer.valueOf(id);
				ManagerUser managerUser = ManagerUser.getManagerUser();
				ModelUser userFound = managerUser.read(intId);
				if (userFound!=null){
					ModelUser modelUser = new ModelUser();
					modelUser.setId(intId);
					modelUser.setNome(nome);
					modelUser.setCognome(cognome);
					modelUser.setEmail(email);
					modelUser.setPassword(password);
					managerUser.update(modelUser);
					messaggio = "<p  style='color: geen;'>OPERAZIONE RIUSCITA. Utente modificato correttamente...</p>";
				}else{
					messaggio = "<p  style='color: red;'>OPERAZIONE ANNULLATA. Utente non trovato</p>";
				}
			}
			else
				messaggio = "<p  style='color: red;'>OPERAZIONE ANNULLATA. Id utente non valido</p>";
		
		response.setContentType("text/plain");
		response.getWriter().write(messaggio);
	}
    
 
	
	
	
	

}

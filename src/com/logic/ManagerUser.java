package com.logic;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;

import com.model.ModelUser;

import restcall.dao.UserDao;

public class ManagerUser {

	private static ManagerUser managerUser;

	@Autowired
	private UserDao userDao;

	@SuppressWarnings("unchecked")
	public List<Object> executeQuery(Object queryXbatis) throws BadSqlGrammarException{
			 return (List<Object>) userDao.executeQuery(queryXbatis);
	}

	public static ManagerUser getManagerUser() {
		if (managerUser == null)
			managerUser = MySpring.loadManagerUser();

		return managerUser;
	}

	
	
//	------------------------------------     METODI    ----------------------------------------
	public void createTable() throws RuntimeException{
		 userDao.createTable();
	}
	
	public void popolateTable() throws RuntimeException{
		 userDao.popolauser();
	}
	
	
	public ModelUser getFromEmail(String email) {
		return userDao.getFromEMail(email);
	}
	
	
	/**
	 * select...
	 * @param id
	 * @return
	 */
	public ModelUser read(int id) {
		return (ModelUser) userDao.read(id);
	}
	
	
	public void update(ModelUser user) {
		 userDao.update(user);
	}
	
	/**
	 * insert
	 * @param user
	 */
	public void create(ModelUser user) {
		 userDao.create(user);
	}
	
	
	public void delete(int id) {
		 userDao.delete(id);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ModelUser> selectAll(){
		return (List<ModelUser>) userDao.selectAll();
	}
		
}

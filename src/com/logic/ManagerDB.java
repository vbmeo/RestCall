package com.logic;

import org.springframework.beans.factory.annotation.Autowired;
import restcall.dao.DbMySQLDao;


public class ManagerDB {
	
	private static ManagerDB managerDB;
	
	@Autowired
	private DbMySQLDao dbMySQL;
	
	
	
	public void createDb(String nomeDb){
		dbMySQL.Create(nomeDb);
	}
	
	
	public static ManagerDB getManagerDB(){
		if (managerDB==null)
			managerDB= MySpring.loadManagerDB();
			
		return managerDB;
	}
	
}

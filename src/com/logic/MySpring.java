package com.logic;

import java.util.logging.Logger;
import org.apache.commons.dbcp.BasicDataSource;
//necessita di commons-logging-1.2 e spring
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MySpring {

	private static ApplicationContext contestoMySql = null;
	private static ManagerDB managerDB = null;
	private static ManagerUser managerUser = null;
	private static final Logger logger = Logger.getLogger(MySpring.class.getName());		
	
	
	/**
	 * ritorna datasource del sigleton contesto mysql
	 * @return
	 */
	public static BasicDataSource getDataSouerceMySql() {	
		if (contestoMySql==null)
				contestoMySql = loadContextMySqlManager();
		BasicDataSource dataSource = (org.apache.commons.dbcp.BasicDataSource) contestoMySql.getBean("connessioneMySql");						
		return dataSource;
	}
	
	
	/**
	 * il contesto è un sigleton
	 * c'è solo una istanza statica, quindi viene fatta solo la prima volta
	 * @return
	 */
	public static synchronized  ApplicationContext loadContextMySqlManager() {
		if (contestoMySql==null){
			logger.info("Inizio contestualizzazione spring MySql");
			String fileContextMySql = "classpath:conf/applicationContext.xml";
			contestoMySql = new ClassPathXmlApplicationContext(fileContextMySql);			
			logger.info("Fine contestualizzazione spring MySql");
			return contestoMySql;
		}else{
			return contestoMySql;
		}
	}

	
	
	/**
	 * il manager è un sigleton
	 * c'è solo una istanza statica, quindi viene fatta solo la prima volta
	 * @return
	 */
	public static synchronized ManagerDB loadManagerDB() {		
		if (managerDB==null){
			contestoMySql = loadContextMySqlManager();	
			logger.info("Inializzazione Manager spring MySql");
			managerDB = (ManagerDB) contestoMySql.getBean("ManagerDb");		
			logger.info("Fine Inializzazione Manager spring MySql");
			return managerDB;
		}
		else{
			return managerDB;
		}
	}
	
	
	/**
	 * il manager è un sigleton
	 * c'è solo una istanza statica, quindi viene fatta solo la prima volta
	 * @return
	 */
	public static synchronized ManagerUser loadManagerUser() {		
		if (managerUser==null){
			contestoMySql = loadContextMySqlManager();	
			logger.info("Inializzazione managerUser spring MySql");
			managerUser = (ManagerUser) contestoMySql.getBean("ManagerUser");		
			logger.info("Fine Inializzazione managerUser spring MySql");
			return managerUser;
		}
		else{
			return managerUser;
		}
	}
	
	
	

}

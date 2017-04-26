package com.spring.jdbcTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringJDBCTemplateMain {
	public static void main(String [] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		UserDao userDaoImpl = (UserDao) context.getBean("userDaoImpl");
		
		/*insert operation using jdbcTemplate update() method
		userDaoImpl.insertUser(new Users("rajan", "chauhan", "011rajchauhan@gmail.com"));*/
		
		/*select single row or column data from a row
		Users user = userDaoImpl.findUserById(6);
		System.out.println(user.getUsername() + " " + user.getEmail());*/
		
		/*use of queryForObject() method for simple type data access
		System.out.println(userDaoImpl.totalUsers());*/
		
		/*use of queryForObject() for simple type data access
		System.out.println(userDaoImpl.fetchUserName(6));*/
		
		/*use of beanPropertyRowMapper() to fetch row data
		Users user = userDaoImpl.findUserByIdUsingBeanPropertyRowMapper(6);
		System.out.println(user.getUsername() + " " + user.getPassword());*/
		
		//user of queryForList() to fetch multiple records from the table
		for(Users user : userDaoImpl.getAll()) {
		System.out.println(user.getUsername() + " " + user.getEmail());
		}
	}
}

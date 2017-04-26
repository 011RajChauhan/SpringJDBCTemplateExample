package com.spring.jdbcTemplate;

import java.util.List;
import java.util.Map;

public interface UserDao {

	void insertUser(Users user);
	
	Users findUserById(int userId);
	
	Users findUserByIdUsingBeanPropertyRowMapper(int userId);
	
	int totalUsers();
	
	String fetchUserName(int userId);
	
	List<Users> getAll();
}

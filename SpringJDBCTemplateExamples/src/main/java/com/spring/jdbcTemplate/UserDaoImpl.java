package com.spring.jdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataSource dataSource;
	
	// for insert operation use jdbcTemplate.update() method
	
	@Override
	public void insertUser(Users user) {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "INSERT INTO USERS (username, password, email) VALUES (?,?,?)";
		int val = jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getPassword(), user.getEmail()});
		System.out.println(val);
	}
	

	/*to get data from a row whether its a data from single column or an entire row
	we use queryForObject, it its simple type i.e integer, long or string provide
	only type of data to be retrieved as second argument, if its a custom object
	we can use RowMapper interface of JdbcTemplate as third argument*/
	
	@Override
	public Users findUserById(int userId) {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM USERS WHERE user_id = ?";
		
		Users user = (Users)jdbcTemplate.queryForObject(sql,new Object[] {userId}, new RowMapper<Users>(){

			@Override
			public Users mapRow(ResultSet rs, int arg) throws SQLException {
				Users user = new Users();
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				return user;
			}});
		
		return user;
	}
	
	//use of queryForObject() for simple type 

	@Override
	public int totalUsers() {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT count(*) from users";
		
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	//use of queryForObject() for simple type 
	
	@Override
	public String fetchUserName(int userId) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT username from users where user_id = ?";
		
		String name = jdbcTemplate.queryForObject(sql, new Object[]{userId},String.class);
		return name;
	}


	/*beanPropertyRowMapper() can also be used to get a row from db
	is it as same as using queryForObject() to get an entire row 
	corrosponding to the supplied type, the only difference is we 
	should use prior one in case of low no of data and when 
	performance is not a big issue*/
	
	@Override
	public Users findUserByIdUsingBeanPropertyRowMapper(int userId) {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
				
				String sql = "SELECT * FROM USERS WHERE user_id = ?";
				
				Users user = (Users)jdbcTemplate.queryForObject(sql,new Object[] {userId}, new BeanPropertyRowMapper<Users>(Users.class));
		
		return user;
	}


	/*user of queryForList() method to get all the rows in a list baked in each row in single map entry,
	for each map object in the list, key for every element will be the column name and value will be 
	the column data*/
	@Override
	public List<Users> getAll() {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		/*List<Users> users = new ArrayList<Users>();
		String sql = "SELECT * FROM USERS";
		
		List<Map<String,Object>> userList = jdbcTemplate.queryForList(sql);
		
		for(Map user : userList) {
			Users u = new Users();
			u.setUsername((String)user.get("username"));
			u.setEmail((String)user.get("email"));
			users.add(u);
		}
		return users;*/
		
		String sql = "SELECT * FROM USERS";
		
		List<Users> users = jdbcTemplate.query(sql, new RowMapper<Users>(){

			@Override
			public Users mapRow(ResultSet rs, int arg) throws SQLException {
				Users user = new Users();
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				return user;
			}});
		return users;
	}
	
	
	
	
	
	
	
}

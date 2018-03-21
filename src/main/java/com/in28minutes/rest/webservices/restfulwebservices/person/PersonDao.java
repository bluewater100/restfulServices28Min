package com.in28minutes.rest.webservices.restfulwebservices.person;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public List<Person> findAll(){
		
		// This default mapper can be used when the bean matches the table.  
		return jdbcTemplate.query("select * from PERSON", new BeanPropertyRowMapper<Person>(Person.class));
		
	}
	
	public Person findById(int id){
		
		return jdbcTemplate.queryForObject
				("select * from person where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Person>(Person.class));
	}

	
	public Person findByNameAndLocation(String name, String location){
		
		return jdbcTemplate.queryForObject
				("select * from person where name=? and location=?", new Object[] { name, location },
				new BeanPropertyRowMapper<Person>(Person.class));
	
	}

	public int deleteById(int id) {
		return jdbcTemplate.update
				("delete from person where id=?", new Object[] { id });
	}
	
	// it returns the number of rows inserted
	public int insert(Person person) {
		return jdbcTemplate.update("insert into person (id, name, location, birth_date) " + 
				"values(?, ?, ?, ?)",
				new Object[] { person.getId(), person.getName(), person.getLocation(),
						new Timestamp(person.getBirthDate().getTime()) });
	}

	// Returns the number of rows updated
	public int update(Person person) {
		return jdbcTemplate.update("update person " + 
								" set name = ?, location = ?, birth_date = ? " + 
								" where id = ?",
				new Object[] { person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()),
						person.getId() });
	}
}

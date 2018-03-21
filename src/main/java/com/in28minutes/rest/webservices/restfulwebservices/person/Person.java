package com.in28minutes.rest.webservices.restfulwebservices.person;

import java.util.Date;



public class Person {

    private Integer id;
    private String name;
    private String location;
    private Date birthDate;

    // A no argument constructor is required for the jdbc rowMapper. If we did not specify the
    // paramaeterized constructor Java would automatically provide underneath the scenes a nor
    // arg constructor and we would not have to specifically provide this.  However, since we provided
    // the paramaeterized constructor Java does not provide a no arg constructor so we have to provide it.
    protected Person() {
    }


	public Person(Integer id, String name, String location, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", location=" + location + ", birthDate=" + birthDate + "]";
	}
    
    

  }
